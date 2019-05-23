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

package protocol;

import com.hazelcast.client.impl.protocol.ClientMessage;
import com.hazelcast.client.impl.protocol.util.ClientMessageDecoder;
import com.hazelcast.client.impl.protocol.util.ClientMessageEncoder;
import com.hazelcast.internal.util.counters.SwCounter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.LinkedBlockingQueue;

public class BufferedTcpServer {

    private static final LinkedBlockingQueue<ClientMessage> writeQueue = new LinkedBlockingQueue<>();
    private static final ByteBuffer sendBuffer = ByteBuffer.allocate(128 * 1024);
    private static final ClientMessageEncoder encoder = new ClientMessageEncoder();

    static {
        sendBuffer.limit(0);
        encoder.dst(sendBuffer);
        encoder.src(() -> {
            try {
                return writeQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        });
    }


    static void createTcpServer(int port) {
        try {
            createTcpServer0(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createTcpServer0(int port) throws IOException {

        ByteBuffer receiveBuffer = ByteBuffer.allocate(128 * 1024);

        ClientMessageDecoder clientMessageDecoder = new ClientMessageDecoder(null, writeQueue::offer);
        clientMessageDecoder.setNormalPacketsRead(SwCounter.newSwCounter());
        clientMessageDecoder.setPriorityPacketsRead(SwCounter.newSwCounter());
        clientMessageDecoder.src(receiveBuffer);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        System.out.println("Server is started at " + serverSocketChannel.socket());

        System.out.println("Server is waiting for client connection ...");
        SocketChannel socketChannel = serverSocketChannel.accept();
        System.out.println("Client connection established. " + socketChannel);

        new Thread(() -> {
            while (true) {
                encoder.onWrite();
                try {
                    socketChannel.write(sendBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while (true) {
            socketChannel.read(receiveBuffer);
            clientMessageDecoder.onRead();
        }

    }

    public static void main(String[] args) {
        BufferedTcpServer.createTcpServer(5701);
    }
}
