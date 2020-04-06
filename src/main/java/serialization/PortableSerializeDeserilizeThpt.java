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
import com.hazelcast.internal.serialization.InternalSerializationService;
import com.hazelcast.internal.serialization.PortableContext;
import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableSerializer;
import com.hazelcast.nio.serialization.PortableWriter;
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
@Warmup(iterations = 10)
@Measurement(iterations = 5)
public class PortableSerializeDeserilizeThpt {

    public static class EmployeeDTO {

        private String name;
        private int age;

        public EmployeeDTO() {
        }

        public EmployeeDTO(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "EmployeeDTO{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    private InternalSerializationService serializationService;
    private Data data;
    EmployeeDTO employeeDTO = new EmployeeDTO("sancar", 30);

    @Setup
    public void prepare() {
        SerializationConfig config = new SerializationConfig();
//        config.addExternalPortableClass(EmployeeDTO.class, 1, 1, 1, new ReflectivePortableSerializer(), EmployeeDTO::new);
//        config.addExternalPortableClass(EmployeeDTO.class, 1, 1, 1, new ReflectivePortableSerializer());
//        config.addExternalPortableClass(EmployeeDTO.class, 1, 1, 1, new SlowReflectivePortableSerializer());
//        config.addExternalPortableClass(EmployeeDTO.class, 1, 1, 1, new UnsafeReflectivePortableSerializer());
//        config.addExternalPortableClass(EmployeeDTO.class, 1, 1, 1);
        config.addExternalPortableClass(EmployeeDTO.class, 1, 1, 1,
                new PortableSerializer<EmployeeDTO>() {
                    @Override
                    public void write(PortableContext.ExternalPortableContext context, PortableWriter writer, EmployeeDTO dto) throws IOException {
                        writer.writeUTF("name", dto.name);
                        writer.writeInt("age", dto.age);
                    }

                    @Override
                    public EmployeeDTO read(PortableContext.ExternalPortableContext context, PortableReader reader) throws IOException {
                        String name = reader.readUTF("name");
                        int age = reader.readInt("age");
                        return new EmployeeDTO(name, age);
                    }
                });


        serializationService = new DefaultSerializationServiceBuilder().setConfig(config).build();
//        MetadataCreator metadataCreator = new MetadataCreator();
//        DomainObjectFactory objectFactory = DomainObjectFactory.newFactory(DomainObjectFactory.Strategy.PORTABLE);
//        ObjectSampleFactory factory = new ObjectSampleFactory(objectFactory, metadataCreator);
//        tweetObject = factory.create();
        data = serializationService.toData(employeeDTO);
    }

    @TearDown
    public void teardown() {
    }

    @Benchmark
    public Object testToData() throws IOException {
        return serializationService.toData(employeeDTO);
    }

    @Benchmark
    public Object testToObject() throws IOException {
        return serializationService.toObject(data);
    }

    public static void main(String[] args) throws IOException {
        PortableSerializeDeserilizeThpt test = new PortableSerializeDeserilizeThpt();
        test.prepare();
        System.out.println(test.testToData());
        System.out.println(test.testToObject());
        test();
    }

    private static void test() {
        Options opt = new OptionsBuilder()
                .include(PortableSerializeDeserilizeThpt.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}