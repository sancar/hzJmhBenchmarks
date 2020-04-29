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

package serialization.flatbuffers;

import com.google.flatbuffers.FlatBufferBuilder;
import domain.MetadataCreator;
import domain.flatbuffers.FlatBuffersSampleFactory;
import domain.flatbuffers.TweetObject;
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
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10)
@Measurement(iterations = 5)
public class FlatBuffersQueryLatency {

    private byte[] data;

    private static domain.flatbuffers.TweetObject toObject(byte[] data) throws IOException {
        java.nio.ByteBuffer buf = java.nio.ByteBuffer.wrap(data);
        return domain.flatbuffers.TweetObject.getRootAsTweetObject(buf);
    }

    private static byte[] toData(FlatBufferBuilder message) throws IOException {
//        message.dataBuffer();
        return message.sizedByteArray();
    }

    @Setup
    public void prepare() throws IOException {
        MetadataCreator metadataCreator = new MetadataCreator();
        FlatBufferBuilder tweetObject = FlatBuffersSampleFactory.create(metadataCreator);
        data = toData(tweetObject);
    }

    @TearDown
    public void teardown() {
    }

    @Benchmark
    public Object testQueryUser_location_city() throws IOException {
        domain.flatbuffers.TweetObject tweetObject = toObject(data);
        return tweetObject.user().location().city();
    }

    @Benchmark
    public Object testQueryCreatedAt() throws IOException {
        TweetObject tweetObject = toObject(data);
        return tweetObject.createdAt();
    }


    public static void main(String[] args) throws IOException {
        FlatBuffersQueryLatency flatBuffersQueryLatency = new FlatBuffersQueryLatency();
        flatBuffersQueryLatency.prepare();
        System.out.println("Data length " + flatBuffersQueryLatency.data.length);
        System.out.println(flatBuffersQueryLatency.testQueryUser_location_city());
        System.out.println(flatBuffersQueryLatency.testQueryCreatedAt());
        test();
    }

    private static void test() {
        Options opt = new OptionsBuilder()
                .include(FlatBuffersQueryLatency.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}