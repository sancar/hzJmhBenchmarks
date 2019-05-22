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

import java.nio.ByteBuffer;
import java.util.LinkedList;

public class NewClientMessage {

    ByteBuffer buffer;
    LinkedList<byte[]> variableFields;
    private static final boolean USE_UNSAFE = false;

    private transient boolean isRetryable;
    private transient boolean acquiresResource;
    private transient String operationName;

    public static NewClientMessage createForEncode(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new MaxMessageSizeExceeded();
        }
        return createForEncode(ByteBuffer.allocate(initialCapacity), 0);
    }

    public static NewClientMessage createForEncode(ByteBuffer buffer, int offset) {
        NewClientMessage clientMessage = new NewClientMessage();
        clientMessage.buffer = buffer;
        return clientMessage;
    }

    void addFrame(byte[] frame) {
        variableFields.add(frame);
    }

    byte[] getFrame(int index) {
        return variableFields.get(index);
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

}
