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

import com.hazelcast.internal.serialization.impl.HeapData;
import com.hazelcast.nio.Bits;
import com.hazelcast.nio.Connection;
import com.hazelcast.nio.serialization.Data;

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
 * |     Flags  |                |               Type              |
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
 * |     Flags  |                                                  |
 * |                         String name                           |
 * ...                                                            ...
 * ...                                                            ...
 * .
 *  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                        Frame Length                           |
 * +---------------------------------------------------------------+
 * |     Flags  |                                                  |
 * |                         Data key                              |
 * ...                                                            ...
 * ...                                                            ...
 * .
 *  0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
 * +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
 * |                        Frame Length                           |
 * +---------------------------------------------------------------+
 * |     Flags  |                                                  |
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
//    private static final int FRAME_LENGTH_FIELD_OFFSET = 0;
//    private static final int FLAGS_FIELD_OFFSET = FRAME_LENGTH_FIELD_OFFSET + Bits.INT_SIZE_IN_BYTES;
    private static final int TYPE_FIELD_OFFSET = 0;
    private static final int CORRELATION_ID_FIELD_OFFSET = TYPE_FIELD_OFFSET + Bits.SHORT_SIZE_IN_BYTES;
    private static final int PARTITION_ID_FIELD_OFFSET = CORRELATION_ID_FIELD_OFFSET + Bits.LONG_SIZE_IN_BYTES;
    private static final int THREAD_ID_FIELD_OFFSET = PARTITION_ID_FIELD_OFFSET + Bits.INT_SIZE_IN_BYTES;
    private static final int TTL_ID_FIELD_OFFSET = THREAD_ID_FIELD_OFFSET + Bits.LONG_SIZE_IN_BYTES;
    private static final int HEADER_SIZE = TTL_ID_FIELD_OFFSET + Bits.LONG_SIZE_IN_BYTES;

    //Payload fields on frames
    private static final int PAYLOAD_FIELD_OFFSET = 0;

    // Vraible fields indexes in incomming byte arrays
    private static final int NAME_INDEX = 1;
    private static final int KEY_INDEX = 2;
    private static final int VALUE_INDEX = 3;

    private transient boolean isRetryable;
    private transient boolean acquiresResource;
    private transient String operationName;
    private Connection connection;
    public static final int RESPONSE_TYPE = 105;
    public static final int REQUEST_TYPE = 10;

    public MapPutCodec() {
    }

    public static NewClientMessage encodeRequest(String name, Data key, Data value, long threadId, long ttl) {
        NewClientMessage clientMessage = NewClientMessage.createForEncode(HEADER_SIZE);
        setMessageType(clientMessage);
        clientMessage.setRetryable(false);
        clientMessage.setAcquiresResource(false);
        clientMessage.setOperationName("Map.put");
        setThreadId(clientMessage, threadId);
        setTTL(clientMessage, ttl);
        clientMessage.addFrame(name.getBytes(Bits.UTF_8));
        clientMessage.addFrame(key.toByteArray());
        clientMessage.addFrame(value.toByteArray());
        return clientMessage;
    }

    private static void setMessageType(NewClientMessage clientMessage) {
        clientMessage.getHeader().putShort(TYPE_FIELD_OFFSET, (short) REQUEST_TYPE);
    }

    private static short getMessageType(NewClientMessage clientMessage) {
        return clientMessage.getHeader().getShort(TYPE_FIELD_OFFSET);
    }

    private static void setThreadId(NewClientMessage clientMessage, long value) {
        clientMessage.getHeader().putLong(THREAD_ID_FIELD_OFFSET, value);
    }

    private static long getThreadID(NewClientMessage clientMessage) {
        return clientMessage.getHeader().getLong(THREAD_ID_FIELD_OFFSET);
    }

    private static void setTTL(NewClientMessage clientMessage, long value) {
        clientMessage.getHeader().putLong(TTL_ID_FIELD_OFFSET, value);
    }

    private static long getTTL(NewClientMessage clientMessage) {
        return clientMessage.getHeader().getLong(TTL_ID_FIELD_OFFSET);
    }

    private static String getName(NewClientMessage clientMessage) {
        byte[] frame = clientMessage.getFrame(NAME_INDEX);
        return new String(frame, Bits.UTF_8);
    }

    private static Data getKey(NewClientMessage clientMessage) {
        byte[] frame = clientMessage.getFrame(KEY_INDEX);
        return new HeapData(frame);
    }

    private static Data getValue(NewClientMessage clientMessage) {
        byte[] frame = clientMessage.getFrame(VALUE_INDEX);
        return new HeapData(frame);
    }


    public static RequestParameters decodeRequest(NewClientMessage clientMessage) {
        RequestParameters parameters = new RequestParameters();
//        int length = clientMessage.headerBuffer.array().length;    long frameLength = getFrameLength(clientMessage);
//        HEADER_SIZE > (length |  frameLength)
//        3 < clientMessage.variableFields.size();

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
