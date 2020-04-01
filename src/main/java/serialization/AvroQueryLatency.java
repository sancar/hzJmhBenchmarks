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

package serialization;

import domain.MetadataCreator;
import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryDecoder;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
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


@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 10)
@Measurement(iterations = 5)
public class AvroQueryLatency {

    private static GenericRecord toObject(Schema schema, Schema partialSchema, byte[] buf) throws IOException {
//        DatumReader<GenericRecord> datumReader = GenericData.get().setFastReaderEnabled(true).createDatumReader(schema, partialSchema);
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema, partialSchema);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);
        BinaryDecoder decoder = DecoderFactory.get().binaryDecoder(byteArrayInputStream, null);
        return datumReader.read(null, decoder);
    }

    private static byte[] toData(Schema schema, GenericRecord user) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(outputStream, null);
        datumWriter.write(user, binaryEncoder);
        binaryEncoder.flush();
        return outputStream.toByteArray();
    }

    Schema schema;
    byte[] data;
    Schema partialSchemaForUser_Location_city = SchemaBuilder
            .record("TweetObject")
            .fields()
            .name("user").type().optional()
            .record("user")
            .fields()
            .name("location").type().optional()
            .record("location")
            .fields()
            .name("city").type().optional().stringType()
            .endRecord()
            .endRecord()
            .endRecord();

    Schema partialSchemaForCreatedAt = SchemaBuilder
            .record("TweetObject")
            .fields()
            .name("createdAt").type().optional().stringType()
            .endRecord();

    @Setup
    public void prepare() throws IOException {
        MetadataCreator metadataCreator = new MetadataCreator();
        schema = SchemaBuilder
                .record("TweetObject")
                .fields()
                .name("createdAt").type().optional().stringType()
                .name("idStr").type().optional().stringType()
                .name("text").type().optional().stringType()
                .name("user").type().optional()
                .record("userRecord")
                .fields()
                .name("description").type().optional().stringType()
                .name("id").type().optional().intType()
                .name("location").type().optional()
                .record("location")
                .fields()
                .name("city").type().optional().stringType()
                .name("country").type().optional().stringType()
                .endRecord()
                .name("name").type().optional().stringType()
                .name("screenName").type().optional().stringType()
                .name("url").type().optional().stringType()
                .endRecord()
                .endRecord();

        GenericRecord root = new GenericData.Record(schema);
        root.put("createdAt", metadataCreator.getCreatedAt());
        root.put("idStr", metadataCreator.getIdStr());
        root.put("text", metadataCreator.getText());

        Schema userFieldSchema = schema.getField("user").schema().getTypes().get(1);
        GenericData.Record userFieldRecord = new GenericData.Record(userFieldSchema);
        userFieldRecord.put("description", metadataCreator.getDescription());
        userFieldRecord.put("id", metadataCreator.getId());
        GenericData.Record locationFieldRecord = new GenericData.Record(userFieldSchema.getField("location").schema().getTypes().get(1));
        locationFieldRecord.put("city", metadataCreator.getCity());
        locationFieldRecord.put("country", metadataCreator.getCountry());
        userFieldRecord.put("location", locationFieldRecord);
        userFieldRecord.put("name", metadataCreator.getName());
        userFieldRecord.put("screenName", metadataCreator.getScreenName());
        userFieldRecord.put("url", metadataCreator.getUrl());
        root.put("user", userFieldRecord);
        data = toData(schema, root);

    }

    @TearDown
    public void teardown() {
    }


    @Benchmark
    public Object testQueryUser_location_city() throws IOException {
        GenericRecord tweetObject = toObject(schema, partialSchemaForUser_Location_city, data);
        GenericRecord user = (GenericRecord) tweetObject.get("user");
        GenericRecord location = (GenericRecord) user.get("location");
        return location.get("city");
    }

    @Benchmark
    public Object testQueryCreatedAt() throws IOException {
        GenericRecord tweetObject = toObject(schema, partialSchemaForCreatedAt, data);
        return tweetObject.get("createdAt");
    }

    public static void main(String[] args) throws IOException {
        AvroQueryLatency avroQueryLatency = new AvroQueryLatency();
        avroQueryLatency.prepare();
        System.out.println(avroQueryLatency.testQueryUser_location_city());
        System.out.println(avroQueryLatency.testQueryCreatedAt());
        test();
    }

    private static void test() {
        Options opt = new OptionsBuilder()
                .include(AvroQueryLatency.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}