JSON representation of actual data.
 {
   "createdAt": "Fri Jan 27 15:34:26 EET 1984",
   "idStr": "1",
   "text": "K1yFCxu9ilOs11YC2NJ2PzhPgaG5siI01TqZbPUy9yj4gBiXfQ7TSDCJTt8Hf2zVvKsqTclZiYNm4U7lu69O7zERWWScawEa8ahIrYKH9ctDAkbHBeR9xIkFhHaomN7PRg",
   "user": {
     "id": 0,
     "name": "ODNOIt",
     "screenName": "FmWAzQ0R",
     "location": {
       "country": "UK",
       "city": "Gotham"
     },
     "url": "www.3wItS3CV.com",
     "description": "ansnCHRXEcgF9sfoFgFeDhIJVeVAiN8fNC8dtPqhclA50nwPnl"
   }
 }

 
 # Data Sizes
 Json 388
 CapnProto 310
 Avro 270
 Portable 525
 FlatBuffers 408
 Proto 272
 
# Query Latency Results
 
Benchmark                                          Mode  Cnt  Score   Error  Units
CapnProtoQueryLatency.testQueryCreatedAt           avgt    5  3.195 ± 0.064  us/op
CapnProtoQueryLatency.testQueryUser_location_city  avgt    5  3.238 ± 0.165  us/op

Benchmark                                     Mode  Cnt  Score   Error  Units
AvroQueryLatency.testQueryCreatedAt           avgt    5  1.554 ± 0.058  us/op
AvroQueryLatency.testQueryUser_location_city  avgt    5  1.732 ± 0.049  us/op

Benchmark                                         Mode  Cnt  Score   Error  Units
PortableQueryLatency.testQueryCreatedAt           avgt    5  0.170 ± 0.003  us/op
PortableQueryLatency.testQueryUser_location_city  avgt    5  0.378 ± 0.005  us/op

Benchmark                                            Mode  Cnt  Score   Error  Units
FlatBuffersQueryLatency.testQueryCreatedAt           avgt    5  0.089 ± 0.001  us/op
FlatBuffersQueryLatency.testQueryUser_location_city  avgt    5  0.105 ± 0.002  us/op

# Query Throughput Results

Benchmark                                        Mode  Cnt       Score       Error  Units
CapnProtoQueryThpt.testQueryCreatedAt           thrpt    5  324,495.281 ±  4416.239  ops/s
CapnProtoQueryThpt.testQueryUser_location_city  thrpt    5  321,969.504 ± 14983.215  ops/s

Benchmark                                   Mode  Cnt       Score       Error  Units
AvroQueryThpt.testQueryCreatedAt           thrpt    5  666,914.577 ±  5668.769  ops/s
AvroQueryThpt.testQueryUser_location_city  thrpt    5  593,631.194 ± 10121.795  ops/s

Benchmark                                       Mode  Cnt        Score        Error  Units
PortableQueryThpt.testQueryCreatedAt           thrpt    5  5,595,587.266 ± 106659.600  ops/s
PortableQueryThpt.testQueryUser_location_city  thrpt    5  1,944,215.669 ±  46301.717  ops/s

Benchmark                                          Mode  Cnt         Score        Error  Units
FlatBuffersQueryThpt.testQueryCreatedAt           thrpt    5  11,763,810.945 ± 226808.513  ops/s
FlatBuffersQueryThpt.testQueryUser_location_city  thrpt    5   9,903,232.534 ± 252658.395  ops/s

# Serialize Deserialize Latency Results

Benchmark                                      Mode  Cnt  Score   Error  Units
ProtoSerializeDeserializeLatency.testToData    avgt    5  0.340 ± 0.009  us/op
ProtoSerializeDeserializeLatency.testToObject  avgt    5  0.350 ± 0.006  us/op

Benchmark                                         Mode  Cnt  Score   Error  Units
PortableSerializeDeserializeLatency.testToData    avgt    5  1.766 ± 0.049  us/op
PortableSerializeDeserializeLatency.testToObject  avgt    5  1.463 ± 0.039  us/op

Benchmark                                     Mode  Cnt  Score   Error  Units
AvroSerializeDeserializeLatency.testToData    avgt    5  3.141 ± 0.054  us/op
AvroSerializeDeserializeLatency.testToObject  avgt    5  2.231 ± 0.023  us/op

Benchmark                                          Mode  Cnt  Score   Error  Units
CapnProtoSerializeDeserializeLatency.testToData    avgt    5  2.552 ± 0.056  us/op
CapnProtoSerializeDeserializeLatency.testToObject  avgt    5  3.453 ± 2.940  us/op

Benchmark                                            Mode  Cnt  Score    Error  Units
FlatBuffersSerializeDeserializeLatency.testToData    avgt    5  0.866 ±  0.012  us/op
FlatBuffersSerializeDeserializeLatency.testToObject  avgt    5  0.035 ±  0.001  us/op
