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

package serialization.flexbuffers;

import com.google.flatbuffers.FlexBuffers;
import domain.MetadataCreator;
import domain.flexbuffers.FlexBuffersSampleFactory;
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
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.Throughput)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 5, time = 1)
public class FlexBuffersSerializeDeserilizeThpt {

    static MetadataCreator metadataCreator = new MetadataCreator();

    ByteBuffer data;

    @Setup
    public void prepare() {
        data = FlexBuffersSampleFactory.create(metadataCreator);
    }

    @TearDown
    public void teardown() {
    }

    @Benchmark
    public ByteBuffer testToData() {
        return FlexBuffersSampleFactory.create(metadataCreator);
    }

    @Benchmark
    public ByteBuffer testToObject(Blackhole blackhole) throws IOException {
        FlexBuffers.Vector rootVector = FlexBuffers.getRoot(data).asVector();
        blackhole.consume(rootVector.get(0).asString());
        blackhole.consume(rootVector.get(1).asString());
        blackhole.consume(rootVector.get(2).asString());
        FlexBuffers.Vector userVector = rootVector.get(3).asVector();
        blackhole.consume(userVector.get(0).asString());
        blackhole.consume(userVector.get(1).asInt());
        FlexBuffers.Vector locationVector = userVector.get(2).asVector();
        blackhole.consume(locationVector.get(0).asString());
        blackhole.consume(locationVector.get(1).asString());
        blackhole.consume(userVector.get(3).asString());
        blackhole.consume(userVector.get(4).asString());
        blackhole.consume(userVector.get(5).asString());
        return data;
    }


    public static void main(String[] args) throws IOException {
        FlexBuffersSerializeDeserilizeThpt test = new FlexBuffersSerializeDeserilizeThpt();
        test.prepare();
        System.out.println("data length " + test.data.limit());
        FlexBuffers.Vector rootVector = FlexBuffers.getRoot(test.data).asVector();
        System.out.println(rootVector.get(0).asString());
        System.out.println(rootVector.get(1).asString());
        System.out.println(rootVector.get(2).asString());
        FlexBuffers.Vector userVector = rootVector.get(3).asVector();
        System.out.println(userVector.get(0).asString());
        System.out.println(userVector.get(1).asInt());
        FlexBuffers.Vector locationVector = userVector.get(2).asVector();
        System.out.println(locationVector.get(0).asString());
        System.out.println(locationVector.get(1).asString());
        System.out.println(userVector.get(3).asString());
        System.out.println(userVector.get(4).asString());
        System.out.println(userVector.get(5).asString());
        test();
    }

    private static void test() {
        Options opt = new OptionsBuilder()
                .include(FlexBuffersSerializeDeserilizeThpt.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}