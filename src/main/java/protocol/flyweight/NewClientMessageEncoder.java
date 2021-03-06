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

import com.hazelcast.internal.networking.HandlerStatus;
import com.hazelcast.internal.networking.OutboundHandler;

import java.nio.ByteBuffer;
import java.util.function.Supplier;

import static com.hazelcast.internal.networking.HandlerStatus.CLEAN;
import static com.hazelcast.internal.networking.HandlerStatus.DIRTY;
import static com.hazelcast.nio.IOUtil.compactOrClear;


public class NewClientMessageEncoder extends OutboundHandler<Supplier<NewClientMessage>, ByteBuffer> {

    private NewClientMessage message;

    @Override
    public void handlerAdded() {
        initDstBuffer();
    }

    @Override
    public HandlerStatus onWrite() {
        compactOrClear(dst);
        try {
            for (; ; ) {
                if (message == null) {
                    message = src.get();

                    if (message == null) {
                        // everything is processed, so we are done
                        return CLEAN;
                    }
                }

                if (message.writeTo(dst)) {
                    // message got written, lets see if another message can be written
                    message = null;
                } else {
                    // the message didn't get written completely, so we are done.
                    return DIRTY;
                }
            }
        } finally {
            dst.flip();
        }
    }
}
