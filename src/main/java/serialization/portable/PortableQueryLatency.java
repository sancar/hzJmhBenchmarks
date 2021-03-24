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

package serialization.portable;

import com.hazelcast.internal.serialization.Data;
import com.hazelcast.internal.serialization.InternalSerializationService;
import com.hazelcast.internal.serialization.impl.GenericRecordQueryReader;
import com.hazelcast.internal.serialization.impl.InternalGenericRecord;
import com.hazelcast.internal.serialization.impl.portable.DefaultPortableReader;
import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import com.hazelcast.nio.serialization.PortableReader;
import domain.MetadataCreator;
import domain.TweetObject;
import domain.portable.PortableObjectFactory;
import domain.portable.PortableSampleFactory;
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
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 5, time = 1)
public class PortableQueryLatency {

    private InternalSerializationService serializationService;
    private Data data;

    @Setup
    public void prepare() {
        serializationService = new DefaultSerializationServiceBuilder().addPortableFactory(PortableObjectFactory.FACTORY_ID, new PortableObjectFactory()).build();
        MetadataCreator metadataCreator = new MetadataCreator();
        TweetObject tweetObject = PortableSampleFactory.create(metadataCreator);
        data = serializationService.toData(tweetObject);
    }

    @TearDown
    public void teardown() {
    }

    @Benchmark
    public Object testQueryUser_location_city() throws IOException {
        InternalGenericRecord internalGenericRecord = serializationService.readAsInternalGenericRecord(data);
        GenericRecordQueryReader reader = new GenericRecordQueryReader(internalGenericRecord);
        return reader.read("user.location.city");
    }

    @Benchmark
    public Object testQueryCreatedAt() throws IOException {
        InternalGenericRecord internalGenericRecord = serializationService.readAsInternalGenericRecord(data);
        GenericRecordQueryReader reader = new GenericRecordQueryReader(internalGenericRecord);
        return reader.read("createdAt");
    }


    public static void main(String[] args) throws IOException {
        PortableQueryLatency portableQueryLatency = new PortableQueryLatency();
        portableQueryLatency.prepare();
        System.out.println("Data length " + portableQueryLatency.data.toByteArray().length);
        System.out.println(portableQueryLatency.testQueryUser_location_city());
        System.out.println(portableQueryLatency.testQueryCreatedAt());
        test();
    }

    private static void test() {
        Options opt = new OptionsBuilder()
                .include(PortableQueryLatency.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}