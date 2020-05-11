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

package serialization.object;

import domain.MetadataCreator;
import domain.TweetLocationObject;
import domain.TweetObject;
import domain.TweetUserObject;
import domain.object.ObjectSampleFactory;
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
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.Throughput)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 5, time = 1)
public class ObjectSerializeDeserilizeThpt {

    static MetadataCreator metadataCreator = new MetadataCreator();

    @Setup
    public void prepare() throws IOException {
    }

    @TearDown
    public void teardown() {
    }

    @Benchmark
    public TweetObject testToData() throws IOException {
        return ObjectSampleFactory.create(metadataCreator);
    }

    @Benchmark
    public TweetObject testToObject(Blackhole blackhole) throws IOException {
        TweetObject tweetObject = ObjectSampleFactory.create(metadataCreator);
        blackhole.consume(tweetObject.getCreatedAt());
        blackhole.consume(tweetObject.getIdStr());
        blackhole.consume(tweetObject.getText());
        TweetUserObject user = tweetObject.getUser();
        blackhole.consume(user.getDescription());
        blackhole.consume(user.getId());
        blackhole.consume(user.getName());
        blackhole.consume(user.getScreenName());
        blackhole.consume(user.getUrl());

        TweetLocationObject location = user.getLocation();
        blackhole.consume(location.getCity());
        blackhole.consume(location.getCountry());
        return tweetObject;
    }


    public static void main(String[] args) throws IOException {
        ObjectSerializeDeserilizeThpt test = new ObjectSerializeDeserilizeThpt();
        test.prepare();
        test();
    }

    private static void test() {
        Options opt = new OptionsBuilder()
                .include(ObjectSerializeDeserilizeThpt.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}