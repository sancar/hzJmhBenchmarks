/*
 * Copyright (c) 2008-2020, Hazelcast, Inc. All Rights Reserved.
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

package serialization;

import com.hazelcast.config.SerializationConfig;
import com.hazelcast.internal.serialization.Data;
import com.hazelcast.internal.serialization.SerializationService;
import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableFactory;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10)
@Measurement(iterations = 5)
public class PortableSerializeDeserializeLatency {

    private SerializationService serializationService;
    private Data data;
    TestDTO testDTO = new TestDTO(231030210);

    @Setup
    public void prepare() {
        SerializationConfig config = new SerializationConfig();
//        config.setByteOrder(ByteOrder.LITTLE_ENDIAN); uncomment and compare

        config.addPortableFactory(1, new PortableFactory() {
            @Override
            public Portable create(int classId) {
                return new TestDTO();
            }
        });


        serializationService = new DefaultSerializationServiceBuilder()
                .setConfig(config).setAllowUnsafe(true).build();
        data = serializationService.toData(testDTO);
    }

    @TearDown
    public void teardown() {
    }

    @Benchmark
    public Object testToData() throws IOException {
        return serializationService.toData(testDTO);
    }

    @Benchmark
    public Object testToObject() throws IOException {
        return serializationService.toObject(data);
    }

    public static void main(String[] args) throws IOException {
        PortableSerializeDeserializeLatency test = new PortableSerializeDeserializeLatency();
        test.prepare();
        System.out.println(test.testToData());
        System.out.println(test.testToObject());
        test();
    }

    private static void test() {
        Options opt = new OptionsBuilder()
                .include(PortableSerializeDeserializeLatency.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}