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

package serialization.compact;

import com.hazelcast.config.GlobalSerializerConfig;
import com.hazelcast.config.SerializationConfig;
import com.hazelcast.internal.serialization.Data;
import com.hazelcast.internal.serialization.InternalSerializationService;
import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import com.hazelcast.nio.serialization.compact.CompactStreamSerializer;
import domain.MetadataCreator;
import domain.compact.CompactSampleFactory;
import domain.compact.CompactTweetObject;
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
public class CompactSerializeDeserilizeThpt {

    static MetadataCreator metadataCreator = new MetadataCreator();
    private InternalSerializationService serializationService;
    private Data data;

    @Setup
    public void prepare() {
        SerializationConfig serializationConfig = new SerializationConfig();
        GlobalSerializerConfig globalSerializerConfig = new GlobalSerializerConfig();
        CompactStreamSerializer compactStreamSerializer = new CompactStreamSerializer();
        globalSerializerConfig.setImplementation(compactStreamSerializer);
        globalSerializerConfig.setOverrideJavaSerialization(true);
        serializationConfig.setGlobalSerializerConfig(globalSerializerConfig);
        serializationService = new DefaultSerializationServiceBuilder().setConfig(serializationConfig).build();

        MetadataCreator metadataCreator = new MetadataCreator();
        CompactTweetObject tweetObject = CompactSampleFactory.create(metadataCreator);
        data = serializationService.toData(tweetObject);
    }

    @TearDown
    public void teardown() {
    }

    @Benchmark
    public Data testToData() throws IOException {
        CompactTweetObject tweetObject = CompactSampleFactory.create(metadataCreator);
        return serializationService.toData(tweetObject);
    }

    @Benchmark
    public CompactTweetObject testToObject() throws IOException {
        return serializationService.toObject(data);
    }

    public static void main(String[] args) throws IOException {
        CompactSerializeDeserilizeThpt test = new CompactSerializeDeserilizeThpt();
        test.prepare();
        System.out.println("bytes " + test.testToData());
        System.out.println("bytes.length " + test.testToData().toByteArray().length);
        System.out.println(test.testToObject());
        test();
    }

    private static void test() {
        Options opt = new OptionsBuilder()
                .include(CompactSerializeDeserilizeThpt.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}