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

package serialization.avro;

import domain.MetadataCreator;
import domain.avro.AvroSampleFactory;
import domain.avro.TweetObject;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
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
import java.util.concurrent.TimeUnit;


@BenchmarkMode(Mode.Throughput)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
@Warmup(iterations = 10, time = 1)
@Measurement(iterations = 5, time = 1)
public class AvroSerializeDeserilizeThpt {

    private static GenericRecord toObject(byte[] data) throws IOException {
        DatumReader<TweetObject> datumReader = new SpecificDatumReader<>(domain.avro.TweetObject.class);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(byteArrayInputStream, null);
        return datumReader.read(null, decoder);
    }

    private static byte[] toData(domain.avro.TweetObject object) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DatumWriter<TweetObject> datumWriter = new SpecificDatumWriter<>(domain.avro.TweetObject.class);
        BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(outputStream, null);
        datumWriter.write(object, binaryEncoder);
        binaryEncoder.flush();
        return outputStream.toByteArray();
    }

    byte[] data;
    domain.avro.TweetObject tweetObject;

    @Setup
    public void prepare() throws IOException {
        MetadataCreator metadataCreator = new MetadataCreator();
        tweetObject = AvroSampleFactory.create(metadataCreator);
        data = toData(tweetObject);

    }

    @TearDown
    public void teardown() {
    }

    @Benchmark
    public byte[] testToData() throws IOException {
        return toData(tweetObject);
    }

    @Benchmark
    public Object testToObject() throws IOException {
        return toObject(data);
    }


    public static void main(String[] args) throws IOException {
        AvroSerializeDeserilizeThpt test = new AvroSerializeDeserilizeThpt();
        test.prepare();
        System.out.println("data length " + test.testToData().length);
        System.out.println(test.testToObject());
//        test();
    }

    private static void test() {
        Options opt = new OptionsBuilder()
                .include(AvroSerializeDeserilizeThpt.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}