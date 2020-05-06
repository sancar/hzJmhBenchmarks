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

package serialization.proto;

import domain.MetadataCreator;
import domain.proto.ProtoSampleFactory;
import domain.proto.ProtoTweetObject;
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


@BenchmarkMode(Mode.Throughput)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 5, time = 1)
public class ProtoQueryThpt {

    private static ProtoTweetObject.TweetObject toObject(byte[] data) throws IOException {
        return ProtoTweetObject.TweetObject.parseFrom(data);
    }

    private static byte[] toData(ProtoTweetObject.TweetObject object) throws IOException {
        return object.toByteArray();
    }

    byte[] data;
    ProtoTweetObject.TweetObject tweetObject;

    @Setup
    public void prepare() throws IOException {
        MetadataCreator metadataCreator = new MetadataCreator();
        tweetObject = ProtoSampleFactory.create(metadataCreator);
        data = toData(tweetObject);

    }

    @TearDown
    public void teardown() {
    }

    @Benchmark
    public Object testQueryUser_location_city() throws IOException {
        ProtoTweetObject.TweetObject tweetObject = toObject(data);
        return tweetObject.getUser().getLocation().getCity();
    }

    @Benchmark
    public Object testQueryCreatedAt() throws IOException {
        ProtoTweetObject.TweetObject tweetObject = toObject(data);
        return tweetObject.getCreatedEt();
    }


    public static void main(String[] args) throws IOException {
        ProtoQueryThpt protoQueryThpt = new ProtoQueryThpt();
        protoQueryThpt.prepare();
        System.out.println(protoQueryThpt.testQueryUser_location_city());
        System.out.println(protoQueryThpt.testQueryCreatedAt());
        test();
    }

    private static void test() {
        Options opt = new OptionsBuilder()
                .include(ProtoQueryThpt.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}