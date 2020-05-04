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

package serialization.flatbuffers;

import com.google.flatbuffers.FlatBufferBuilder;
import domain.MetadataCreator;
import domain.flatbuffers.FlatBuffersSampleFactory;
import domain.flatbuffers.Location;
import domain.flatbuffers.TweetObject;
import domain.flatbuffers.User;
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


@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 5, time = 1)
public class FlatBuffersSerializeDeserializeLatency {

    static MetadataCreator metadataCreator = new MetadataCreator();

    private static TweetObject toObject(byte[] data) throws IOException {
        ByteBuffer buf = ByteBuffer.wrap(data);
        return TweetObject.getRootAsTweetObject(buf);
    }

    private static ByteBuffer toByteBuffer(FlatBufferBuilder message) throws IOException {
        return message.dataBuffer();
    }

    private static byte[] toByteArray(FlatBufferBuilder message) throws IOException {
        return message.sizedByteArray();
    }


    byte[] data;
    FlatBufferBuilder tweetObject;

    @Setup
    public void prepare() throws IOException {
        tweetObject = FlatBuffersSampleFactory.create(metadataCreator);
        data = toByteArray(tweetObject);

    }

    @TearDown
    public void teardown() {
    }

    @Benchmark
    public ByteBuffer testToData() throws IOException {
        FlatBufferBuilder tweetObject = FlatBuffersSampleFactory.create(metadataCreator);
        return toByteBuffer(tweetObject);
    }

    @Benchmark
    public TweetObject testToObject(Blackhole blackhole) throws IOException {
        TweetObject tweetObject = TweetObject.getRootAsTweetObject(ByteBuffer.wrap(data));
        blackhole.consume(tweetObject.createdAt());
        blackhole.consume(tweetObject.id());
        blackhole.consume(tweetObject.text());
        User user = tweetObject.user();
        blackhole.consume(user.destription());
        blackhole.consume(user.id());
        Location location = user.location();
        blackhole.consume(location.city());
        blackhole.consume(location.country());
        blackhole.consume(user.name());
        blackhole.consume(user.screenName());
        return tweetObject;
    }


    public static void main(String[] args) throws IOException {
        FlatBuffersSerializeDeserializeLatency test = new FlatBuffersSerializeDeserializeLatency();
        test.prepare();
        System.out.println("data length " + FlatBuffersSampleFactory.create(metadataCreator).sizedByteArray().length);
        TweetObject tweetObject = toObject(test.data);
        System.out.println(tweetObject.createdAt());
        System.out.println(tweetObject.id());
        System.out.println(tweetObject.text());
        User user = tweetObject.user();
        System.out.println(user.destription());
        System.out.println(user.id());
        System.out.println(user.name());
        System.out.println(user.screenName());
        System.out.println(user.url());

        Location location = user.location();
        System.out.println(location.city());
        System.out.println(location.country());
        test();
    }

    private static void test() {
        Options opt = new OptionsBuilder()
                .include(FlatBuffersSerializeDeserializeLatency.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}