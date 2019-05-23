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

import com.hazelcast.client.impl.protocol.exception.MaxMessageSizeExceeded;
import com.hazelcast.nio.Bits;

import java.nio.ByteBuffer;
import java.util.LinkedList;

public class NewClientMessage {

    private static final int FRAME_LENGTH_FIELD_OFFSET = 0;
    private static final int FLAGS_FIELD_OFFSET = FRAME_LENGTH_FIELD_OFFSET + Bits.INT_SIZE_IN_BYTES;
    public static final byte NEXT_FLAG = (byte) 0x80;

    LinkedList<byte[]> frames = new LinkedList<>();

    private transient boolean isRetryable;
    private transient boolean acquiresResource;
    private transient String operationName;

    private transient int writeIndex;
    private transient int writeOffset = -1; //-1 means length is not written yet
    private transient int readIndex;
    private transient int readOffset = -1;

    public static NewClientMessage createForEncode(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new MaxMessageSizeExceeded();
        }
        return createForEncode(ByteBuffer.allocate(initialCapacity), 0);
    }

    public static NewClientMessage createForEncode(ByteBuffer buffer, int offset) {
        NewClientMessage clientMessage = new NewClientMessage();
        clientMessage.frames.add(buffer.array());
        return clientMessage;
    }

    public static NewClientMessage create() {
        return new NewClientMessage();
    }

    void addFrame(byte[] frame) {
        frames.add(frame);
    }

    ByteBuffer getHeader() {
        return ByteBuffer.wrap(frames.get(0));
    }

    byte[] getFrame(int index) {
        return frames.get(index);
    }

    public boolean isRetryable() {
        return isRetryable;
    }

    public boolean acquiresResource() {
        return acquiresResource;
    }

    public void setAcquiresResource(boolean acquiresResource) {
        this.acquiresResource = acquiresResource;
    }

    public void setRetryable(boolean isRetryable) {
        this.isRetryable = isRetryable;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationName() {
        return operationName;
    }

    public boolean writeTo(ByteBuffer dst) {
        for (; ; ) {
            byte[] frame = frames.get(writeIndex);
            boolean isLastFrame = writeIndex == frames.size();
            if (writeBuffer(dst, frame, isLastFrame)) {
                writeOffset = -1;
                if (isLastFrame) {
                    writeOffset = 0;
                    return true;
                }
                writeIndex++;
            } else {
                return false;
            }
        }
    }


    private boolean writeBuffer(ByteBuffer dst, byte[] frame, boolean isLastFrame) {
        int size = frame.length;

        // the number of bytes that can be written to the bb
        int bytesWritable = dst.remaining();

        //if write offset is -1 put the length and flags byte first
        if (writeOffset == -1 && bytesWritable >= Bits.INT_SIZE_IN_BYTES + Bits.BYTE_SIZE_IN_BYTES) {
            Bits.writeIntL(dst.array(), dst.position(), frame.length);
            dst.position(dst.position() + Bits.INT_SIZE_IN_BYTES);
            if (!isLastFrame) {
                short newFlag = (short) (getFlags() | NEXT_FLAG);
                dst.put(dst.position(), (byte) newFlag);
            }
            dst.position(dst.position() + Bits.BYTE_SIZE_IN_BYTES);
            writeOffset = 0;
        } else {
            return false;
        }

        // the number of bytes that need to be written
        int bytesNeeded = size - writeOffset;

        int bytesWrite;
        boolean done;
        if (bytesWritable >= bytesNeeded) {
            // all bytes for the value are available
            bytesWrite = bytesNeeded;
            done = true;
        } else {
            // not all bytes for the value are available. Write as much as is available
            bytesWrite = bytesWritable;
            done = false;
        }

        dst.put(frame, writeOffset, bytesWrite);
        writeOffset += bytesWrite;

        return done;
    }

    public boolean isFlagSet(byte flag) {
        int i = getFlags() & flag;
        return i == flag;
    }

    /**
     * Returns the flags field value.
     *
     * @return The flags field value.
     */
    public byte getFlags() {
        return getHeader().get(FLAGS_FIELD_OFFSET);
    }

    /**
     * Sets the flags field value.
     *
     * @param flags The value to set in the flags field.
     * @return The ClientMessage with the new flags field value.
     */
    public NewClientMessage addFlag(final byte flags) {
        ByteBuffer header = getHeader();
        header.put(FLAGS_FIELD_OFFSET, (byte) (getFlags() | flags));
        return this;
    }

    public boolean readFrom(ByteBuffer src) {
        for (; ; ) {
            if (readFrame(src)) {
                if (!isFlagSet(frames.get(readIndex)[FLAGS_FIELD_OFFSET], NEXT_FLAG)) {
                    return true;
                }
                readIndex++;
                readOffset = 0;
            } else {
                return false;
            }

        }
    }

    private boolean readFrame(ByteBuffer src) {
        // init internal buffer
        final int remaining = src.remaining();
        if (remaining < Bits.INT_SIZE_IN_BYTES + Bits.BYTE_SIZE_IN_BYTES) {
            // we don't have even the frame length ready
            return false;
        }
        if (readOffset == -1) {
            int frameLength = Bits.readIntL(src);
            // we need to restore the position; as if we didn't read the frame-length
            src.position(src.position() + Bits.INT_SIZE_IN_BYTES);
            src.position(src.position() + Bits.BYTE_SIZE_IN_BYTES);

            readOffset = 0;
            byte[] bytes = new byte[frameLength];
            frames.add(bytes);
        }

        byte[] frame = frames.get(readIndex);
        return accumulate(src, frame, frame.length - readOffset);
    }

    private boolean isFlagSet(byte[] frame, byte flag) {
        byte flags = frame[FLAGS_FIELD_OFFSET];
        int i = flags & flag;
        return i == flag;
    }

    private boolean isFlagSet(byte flags, byte flag) {
        int i = flags & flag;
        return i == flag;
    }


    private boolean accumulate(ByteBuffer src, byte[] dest, int length) {
        int remaining = src.remaining();
        int readLength = remaining < length ? remaining : length;
        if (readLength > 0) {
            src.get(dest, readOffset, readLength);
            readOffset += readLength;
            return readLength == length;
        }
        return false;
    }

}
