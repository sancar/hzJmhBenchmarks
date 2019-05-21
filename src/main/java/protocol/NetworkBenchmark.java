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
import com.hazelcast.client.impl.protocol.codec.MapPutCodec;
import com.hazelcast.client.impl.protocol.util.SafeBuffer;
import com.hazelcast.nio.serialization.Data;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 10)
@Measurement(iterations = 5)
public class NetworkBenchmark {

    private Data data;
    private String name = "mymap";
    private ClientMessage request;

    OutputStream outputStream;
    InputStream inputStream;

    @Setup
    public void prepare() throws InterruptedException {
        data = DataGenerator.createData();
        System.out.println("Data(key or value) size in bytes " + data.dataSize());
        request = DataGenerator.createPutRequest();
        System.out.println("Message size in bytes " + request.buffer().byteArray().length);

        int port = 5701;

        new Thread(() -> TcpServer.createTcpServer(port, request.buffer().byteArray().length)).start();
        Thread.sleep(1000);

        try {
            //Socket socket = new Socket("10.216.1.18", 5701);
            Socket socket = new Socket("localhost", port);
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Benchmark
    public void testPutEncodeDecodeRequest_network() throws IOException {
        ClientMessage clientMessage = DataGenerator.createPutRequest();
        byte[] b = clientMessage.buffer().byteArray();
        int length = b.length;
        outputStream.write(b);
        byte[] incoming = new byte[length];

        int read = 0;
        while (read != length) {
            read += inputStream.read(incoming, read, length - read);
        }
        SafeBuffer safeBuffer = new SafeBuffer(incoming);
        MapPutCodec.decodeRequest(ClientMessage.createForDecode(safeBuffer, 0));
    }


    public static void main(String[] args) {


        Options opt = new OptionsBuilder()
                .include(NetworkBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}
