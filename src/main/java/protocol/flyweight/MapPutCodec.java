/*
 * Copyright (c) 2008-2019, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package protocol.flyweight;

import com.hazelcast.nio.Bits;
import com.hazelcast.nio.Connection;
import com.hazelcast.nio.serialization.Data;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * <p>
 * Client Message is the carrier framed data as defined below.
 * </p>
 * <p>
 * Any request parameter, response or event data will be carried in
 * the payload.
 * </p>
 * <p/>
 * <pre>
 *  0                   1                   2                   3
 *  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                        Frame Length                           |
 * +-----------------------------+---------------------------------+
 * |    Nullable   |  Flags      |               Type              |
 * +-----------------------------+---------------------------------+
 * |                                                               |
 * +                       CorrelationId                           +
 * |                                                               |
 * +---------------------------------------------------------------+
 * |                        PartitionId                            |
 * +---------------------------------------------------------------+
 * |                                                               |
 * +                              Thread Id                        +
 * |                                                               |
 * +---------------------------------------------------------------+
 * |                                                               |
 * |                      TTL                                      +
 * |                                                               |
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * .
 *  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                        Frame Length                           |
 * +---------------------------------------------------------------+
 * |                                                               |
 * |                         String name                           |
 * ...                                                            ...
 * ...                                                            ...
 * .
 *  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                        Frame Length                           |
 * +---------------------------------------------------------------+
 * |                                                               |
 * |                         Data key                              |
 * ...                                                            ...
 * ...                                                            ...
 * .
 *  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                        Frame Length                           |
 * +---------------------------------------------------------------+
 * |                                                               |
 * |                         Data value                            |
 * ...                                                            ...
 * ...                                                            ...
 */
public class MapPutCodec {

    /**
     * Listener Event Flag
     */
    public static final short LISTENER_EVENT_FLAG = 0x01;


    // Fixed fields offsets in header
    private static final int FRAME_LENGTH_FIELD_OFFSET = 0;
    private static final int NULLABLE_FIELD_OFFSET = FRAME_LENGTH_FIELD_OFFSET + Bits.INT_SIZE_IN_BYTES;
    private static final int FLAGS_FIELD_OFFSET = NULLABLE_FIELD_OFFSET + Bits.BYTE_SIZE_IN_BYTES;
    private static final int TYPE_FIELD_OFFSET = FLAGS_FIELD_OFFSET + Bits.BYTE_SIZE_IN_BYTES;
    private static final int CORRELATION_ID_FIELD_OFFSET = TYPE_FIELD_OFFSET + Bits.SHORT_SIZE_IN_BYTES;
    private static final int PARTITION_ID_FIELD_OFFSET = CORRELATION_ID_FIELD_OFFSET + Bits.LONG_SIZE_IN_BYTES;
    private static final int THREAD_ID_FIELD_OFFSET = PARTITION_ID_FIELD_OFFSET + Bits.INT_SIZE_IN_BYTES;
    private static final int TTL_ID_FIELD_OFFSET = THREAD_ID_FIELD_OFFSET + Bits.LONG_SIZE_IN_BYTES;
    private static final int HEADER_SIZE = TTL_ID_FIELD_OFFSET + Bits.LONG_SIZE_IN_BYTES;

    //Payload fields on frames
    private static final int PAYLOAD_FIELD_OFFSET = FRAME_LENGTH_FIELD_OFFSET + Bits.INT_SIZE_IN_BYTES;

    // Vraible fields indexes in incomming byte arrays
    private static final int NAME_INDEX = 0;
    private static final int KEY_INDEX = 1;
    private static final int VALUE_INDEX = 2;

    private transient boolean isRetryable;
    private transient boolean acquiresResource;
    private transient String operationName;
    private Connection connection;
    public static final int RESPONSE_TYPE = 105;
    public static final int REQUEST_TYPE = 10;

    public MapPutCodec() {
    }

    public static NewClientMessage encodeRequest(String name, Data key, Data value, long threadId, long ttl) {
        int requiredDataSize = com.hazelcast.client.impl.protocol.codec.MapPutCodec.RequestParameters.calculateDataSize(name, key, value, threadId, ttl);
        NewClientMessage clientMessage = NewClientMessage.createForEncode(HEADER_SIZE);
        setFrameLength(clientMessage);
        setMessageType(clientMessage);
        clientMessage.setRetryable(false);
        clientMessage.setAcquiresResource(false);
        clientMessage.setOperationName("Map.put");
        setThreadId(clientMessage, threadId);
        setTTL(clientMessage, ttl);
        clientMessage.addFrame(createNameBuffer(name).array());
        clientMessage.addFrame(createKeyBuffer(key).array());
        clientMessage.addFrame(createValueBuffer(value).array());
        return clientMessage;
    }

    private static void setFrameLength(NewClientMessage clientMessage) {
        clientMessage.buffer.putInt(FRAME_LENGTH_FIELD_OFFSET, HEADER_SIZE);
    }

    private static long getFrameLength(NewClientMessage clientMessage) {
        return clientMessage.buffer.getLong(FRAME_LENGTH_FIELD_OFFSET);
    }

    private static void setMessageType(NewClientMessage clientMessage) {
        clientMessage.buffer.putShort(TYPE_FIELD_OFFSET, (short) REQUEST_TYPE);
    }

    private static short getMessageType(NewClientMessage clientMessage) {
        return clientMessage.buffer.getShort(TYPE_FIELD_OFFSET);
    }

    private static void setThreadId(NewClientMessage clientMessage, long value) {
        clientMessage.buffer.putLong(THREAD_ID_FIELD_OFFSET, value);
    }

    private static long getThreadID(NewClientMessage clientMessage) {
        return clientMessage.buffer.getLong(THREAD_ID_FIELD_OFFSET);
    }

    private static void setTTL(NewClientMessage clientMessage, long value) {
        clientMessage.buffer.putLong(TTL_ID_FIELD_OFFSET, value);
    }

    private static long getTTL(NewClientMessage clientMessage) {
        return clientMessage.buffer.getLong(TTL_ID_FIELD_OFFSET);
    }

    private static ByteBuffer createNameBuffer(String name) {
        byte[] bytes = name.getBytes(Bits.UTF_8);
        int frameLength = bytes.length + Bits.INT_SIZE_IN_BYTES;
        ByteBuffer buffer = ByteBuffer.allocate(frameLength);
        buffer.putInt(FRAME_LENGTH_FIELD_OFFSET, frameLength);
        buffer.put(bytes, 0, PAYLOAD_FIELD_OFFSET);

        StandardCharsets.UTF_8.newEncoder();

        return buffer;
    }

    private static String getName(NewClientMessage clientMessage) {
        byte[] frame = clientMessage.getFrame(NAME_INDEX);
        ByteBuffer wrap = ByteBuffer.wrap(frame);
        int frameLength = wrap.getInt(FRAME_LENGTH_FIELD_OFFSET);
        return new String(frame, PAYLOAD_FIELD_OFFSET, frameLength - PAYLOAD_FIELD_OFFSET,   Bits.UTF_8);
    }

    private static ByteBuffer createKeyBuffer(Data key) {
        int frameLength = key.totalSize() + Bits.INT_SIZE_IN_BYTES;
        ByteBuffer buffer = ByteBuffer.allocate(frameLength);
        buffer.putInt(FRAME_LENGTH_FIELD_OFFSET, frameLength);
        key.copyTo(buffer.array(), PAYLOAD_FIELD_OFFSET);
        return buffer;
    }

    private static Data getKey(NewClientMessage clientMessage) {
        return null;
    }

    private static ByteBuffer createValueBuffer(Data value) {
        int frameLength = value.totalSize() + Bits.INT_SIZE_IN_BYTES;
        ByteBuffer buffer = ByteBuffer.allocate(frameLength);
        buffer.putInt(FRAME_LENGTH_FIELD_OFFSET, frameLength);
        value.copyTo(buffer.array(), PAYLOAD_FIELD_OFFSET);
        return buffer;
    }

    private static Data getValue(NewClientMessage clientMessage) {
        return null;
    }


    public static RequestParameters decodeRequest(NewClientMessage clientMessage) {
        RequestParameters parameters = new RequestParameters();
        int length = clientMessage.buffer.array().length;
        long frameLength = getFrameLength(clientMessage);

        parameters.name = getName(clientMessage);
        parameters.key = getKey(clientMessage);
        parameters.value = getValue(clientMessage);
        parameters.threadId = getThreadID(clientMessage);
        parameters.ttl = getTTL(clientMessage);
        return parameters;
    }

    public static class RequestParameters {
        public static final int TYPE;
        public String name;
        public Data key;
        public Data value;
        public long threadId;
        public long ttl;

        public RequestParameters() {
        }
        static {
            TYPE = MapPutCodec.REQUEST_TYPE;
        }
    }
}
