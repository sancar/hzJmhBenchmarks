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

package serialization.capnproto;

import domain.MetadataCreator;
import domain.capnproto.CapSampleFactory;
import domain.capnproto.CapTweetObject;
import org.capnproto.MessageBuilder;
import org.capnproto.MessageReader;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.Throughput)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 5, time = 1)
public class CapnProtoQueryThpt {

    byte[] data;

    private static org.capnproto.MessageReader toObject(byte[] data) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ReadableByteChannel readableByteChannel = Channels.newChannel(byteArrayInputStream);
//        return org.capnproto.SerializePacked.readFromUnbuffered(readableByteChannel);
        return org.capnproto.Serialize.read(readableByteChannel);
    }

    private static byte[] toData(org.capnproto.MessageBuilder message) throws IOException {
        ByteArrayOutputStream buff = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(buff);

//        org.capnproto.SerializePacked.writeToUnbuffered(wbc, message);
        org.capnproto.Serialize.write(wbc, message);
        return buff.toByteArray();
    }



    @Setup
    public void prepare() throws IOException {
        MetadataCreator metadataCreator = new MetadataCreator();
        MessageBuilder tweetObject = CapSampleFactory.create(metadataCreator);
        data = toData(tweetObject);
    }

    @TearDown
    public void teardown() {
    }

    @Benchmark
    public Object testQueryUser_location_city() throws IOException {
        MessageReader message = toObject(data);
        CapTweetObject.TweetObject.Reader tweetObject = message.getRoot(CapTweetObject.TweetObject.factory);
        return tweetObject.getUser().getLocation().getCity();
    }

    @Benchmark
    public Object testQueryCreatedAt() throws IOException {
        MessageReader message = toObject(data);
        CapTweetObject.TweetObject.Reader tweetObject = message.getRoot(CapTweetObject.TweetObject.factory);
        return tweetObject.getCreatedAt();
    }


    public static void main(String[] args) throws IOException {
        CapnProtoQueryThpt capnProtoQueryThpt = new CapnProtoQueryThpt();
        capnProtoQueryThpt.prepare();
        System.out.println(capnProtoQueryThpt.testQueryUser_location_city());
        System.out.println(capnProtoQueryThpt.testQueryCreatedAt());
        test();
    }

    private static void test() {
        Options opt = new OptionsBuilder()
                .include(CapnProtoQueryThpt.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}