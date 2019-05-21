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

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10)
@Measurement(iterations = 5)
public class EncodeDecodeBenchmark {

    private Data data;
    private String name = "mymap";
    private ClientMessage request;

    @Setup
    public void prepare() {
        data = DataGenerator.createData();
        System.out.println("Data(key or value) size in bytes " + data.dataSize());
        request = DataGenerator.createPutRequest(name);
        System.out.println("Message size in bytes " + request.getFrameLength());
    }

    @Benchmark
    public void testPutEncodeDecodeRequest() {
        ClientMessage clientMessage = MapPutCodec.encodeRequest(name, data, data, 1, 1);
        MapPutCodec.decodeRequest(ClientMessage.createForDecode(clientMessage.buffer(), 0));
    }

    @Benchmark
    public void testPutEncodeDecodeRequest_and_Response() {
        ClientMessage clientMessage = MapPutCodec.encodeRequest(name, data, data, 1, 1);
        MapPutCodec.decodeRequest(ClientMessage.createForDecode(clientMessage.buffer(), 0));
        ClientMessage response = MapPutCodec.encodeResponse(data);
        MapPutCodec.decodeResponse(ClientMessage.createForDecode(response.buffer(), 0));
    }

    @Benchmark
    public void testPutEncode() {
        MapPutCodec.encodeRequest(name, data, data, 1, 1);
    }

    @Benchmark
    public void testPutDecode() {
        MapPutCodec.decodeRequest(ClientMessage.createForDecode(request.buffer(), 0));
    }

    public static void main(String[] args) {


        Options opt = new OptionsBuilder()
                .include(EncodeDecodeBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}
