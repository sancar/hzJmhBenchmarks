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

import com.hazelcast.client.impl.protocol.ClientMessage;
import com.hazelcast.internal.networking.HandlerStatus;
import com.hazelcast.internal.networking.nio.InboundHandlerWithCounters;
import com.hazelcast.nio.Connection;

import java.nio.ByteBuffer;
import java.util.function.Consumer;

import static com.hazelcast.internal.networking.HandlerStatus.CLEAN;
import static com.hazelcast.nio.IOUtil.compactOrClear;


/**
 * Builds {@link ClientMessage}s from byte chunks.
 *
 * Fragmented messages are merged into single messages before processed.
 */
public class NewClientMessageDecoder extends InboundHandlerWithCounters<ByteBuffer, Consumer<NewClientMessage>> {

    private final Connection connection;
    private NewClientMessage message = NewClientMessage.create();

    public NewClientMessageDecoder(Connection connection, Consumer<NewClientMessage> dst) {
        dst(dst);
        this.connection = connection;
    }

    @Override
    public void handlerAdded() {
        initSrcBuffer();
    }

    @Override
    public HandlerStatus onRead() {
        src.flip();
        try {
            int messagesCreated = 0;
            while (src.hasRemaining()) {
                boolean complete = message.readFrom(src);
                if (!complete) {
                    normalPacketsRead.inc(messagesCreated);
                    break;
                }

                //MESSAGE IS COMPLETE HERE
                    //HANDLE-MESSAGE
                    handleMessage(message);
                    message = NewClientMessage.create();
                    messagesCreated++;
            }



            return CLEAN;
        } finally {
            compactOrClear(src);
        }
    }

    private void handleMessage(NewClientMessage message) {
//        message.index(message.getDataOffset());
//        message.setConnection(connection);
        dst.accept(message);
    }
}
