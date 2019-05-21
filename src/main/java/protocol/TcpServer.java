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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class TcpServer {


    static void createTcpServer(int port, int frameLength) {
        byte[] messageBuffer = new byte[frameLength];

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is started at " + serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (true) {
            Socket clientSocket = null;
            try {
                System.out.println("Server is waiting for client connection ...");
                clientSocket = serverSocket.accept();
                System.out.println("Client connection established. " + clientSocket);
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();


                int offset = 0;
                int numBytesRead;
                while ((numBytesRead = inputStream.read(messageBuffer, offset, frameLength - offset)) != -1) {
                    offset += numBytesRead;
                    if (offset == frameLength) {
                        outputStream.write(messageBuffer);
                        offset = 0;
                    }
                }
                clientSocket.close();
                System.out.println("Client disconnected. " + clientSocket);

            } catch (IOException e) {
                if (clientSocket != null) {
                    System.out.println("Client disconnected " + clientSocket + " with exception:" + e);
                    try {
                        clientSocket.close();
                    } catch (IOException ignored) {
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        ClientMessage request = DataGenerator.createPutRequest();
        TcpServer.createTcpServer(5701, request.buffer().byteArray().length);
    }
}
