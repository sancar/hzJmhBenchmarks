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
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 5, time = 1)
public class FlexBuffersQueryLatency {

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
    public Object testQueryUser_location_city() throws IOException {
        FlexBuffers.Vector rootVector = FlexBuffers.getRoot(data).asVector();
        FlexBuffers.Vector userVector = rootVector.get(3).asVector();
        FlexBuffers.Vector locationVector = userVector.get(2).asVector();
        return locationVector.get(0).asString();
    }

    @Benchmark
    public Object testQueryCreatedAt() throws IOException {
        FlexBuffers.Vector rootVector = FlexBuffers.getRoot(data).asVector();
        return rootVector.get(0);
    }

    public static void main(String[] args) throws IOException {
        FlexBuffersQueryLatency flexBuffersQueryLatency = new FlexBuffersQueryLatency();
        flexBuffersQueryLatency.prepare();
        System.out.println(flexBuffersQueryLatency.testQueryUser_location_city());
        System.out.println(flexBuffersQueryLatency.testQueryCreatedAt());
        test();
    }

    private static void test() {
        Options opt = new OptionsBuilder()
                .include(FlexBuffersQueryLatency.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}