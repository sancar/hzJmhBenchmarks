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
 CapnProto(packed) 310
 CapnProto 408
 Avro 270
 Portable 525
 FlatBuffers 408
 Proto 272
 
# Query Latency Results
 
Benchmark(Packed)                                  Mode  Cnt  Score   Error  Units
CapnProtoQueryLatency.testQueryCreatedAt           avgt    5  3.195 ± 0.064  us/op
CapnProtoQueryLatency.testQueryUser_location_city  avgt    5  3.238 ± 0.165  us/op

Benchmark                                     Mode  Cnt  Score   Error  Units
AvroQueryLatency.testQueryCreatedAt           avgt    5  1.554 ± 0.058  us/op
AvroQueryLatency.testQueryUser_location_city  avgt    5  1.732 ± 0.049  us/op

Benchmark                                          Mode  Cnt  Score   Error  Units
CapnProtoQueryLatency.testQueryCreatedAt           avgt    5  0.471 ± 0.007  us/op
CapnProtoQueryLatency.testQueryUser_location_city  avgt    5  0.472 ± 0.010  us/op

Benchmark                                         Mode  Cnt  Score   Error  Units
PortableQueryLatency.testQueryCreatedAt           avgt    5  0.170 ± 0.003  us/op
PortableQueryLatency.testQueryUser_location_city  avgt    5  0.378 ± 0.005  us/op

Benchmark                                            Mode  Cnt  Score   Error  Units
FlatBuffersQueryLatency.testQueryCreatedAt           avgt    5  0.073 ± 0.002  us/op
FlatBuffersQueryLatency.testQueryUser_location_city  avgt    5  0.091 ± 0.003  us/op

Benchmark                                            Mode  Cnt  Score    Error  Units
FlexBuffersQueryLatency.testQueryCreatedAt           avgt    5  0.020 ±  0.001  us/op
FlexBuffersQueryLatency.testQueryUser_location_city  avgt    5  0.069 ±  0.003  us/op

# Query Throughput Results

Benchmark(Packed)                               Mode  Cnt       Score       Error  Units
CapnProtoQueryThpt.testQueryCreatedAt           thrpt    5  324,495.281 ±  4416.239  ops/s
CapnProtoQueryThpt.testQueryUser_location_city  thrpt    5  321,969.504 ± 14983.215  ops/s

Benchmark                                   Mode  Cnt       Score       Error  Units
AvroQueryThpt.testQueryCreatedAt           thrpt    5  666,914.577 ±  5668.769  ops/s
AvroQueryThpt.testQueryUser_location_city  thrpt    5  593,631.194 ± 10121.795  ops/s

Benchmark                                        Mode  Cnt        Score       Error  Units
CapnProtoQueryThpt.testQueryCreatedAt           thrpt    5  2,268,551.299 ± 49593.543  ops/s
CapnProtoQueryThpt.testQueryUser_location_city  thrpt    5  2,171,765.108 ± 33404.552  ops/s

ProtoQueryThpt.testQueryCreatedAt                   thrpt    5  2,254,962.269 ±  53314.745  ops/s
ProtoQueryThpt.testQueryUser_location_city          thrpt    5  2,320,943.325 ± 549808.431  ops/s

Benchmark                                       Mode  Cnt        Score        Error  Units
PortableQueryThpt.testQueryCreatedAt           thrpt    5  5,595,587.266 ± 106659.600  ops/s
PortableQueryThpt.testQueryUser_location_city  thrpt    5  1,944,215.669 ±  46301.717  ops/s

Benchmark                                          Mode  Cnt         Score        Error  Units
FlatBuffersQueryThpt.testQueryCreatedAt           thrpt    5  13,772,732.169 ±  65406.764  ops/s
FlatBuffersQueryThpt.testQueryUser_location_city  thrpt    5  11,088,965.264 ± 244265.470  ops/s

Benchmark                                          Mode  Cnt         Score        Error  Units
FlexBuffersQueryThpt.testQueryCreatedAt           thrpt    5  49,689,900.173 ± 348630.321  ops/s
FlexBuffersQueryThpt.testQueryUser_location_city  thrpt    5  14,530,749.254 ± 696543.873  ops/s

# Serialize Deserialize Latency Results

Benchmark                                     Mode  Cnt  Score   Error  Units
AvroSerializeDeserializeLatency.testToData    avgt    5  3.141 ± 0.054  us/op
AvroSerializeDeserializeLatency.testToObject  avgt    5  2.231 ± 0.023  us/op

Benchmark(Packed)                                  Mode  Cnt  Score   Error  Units
CapnProtoSerializeDeserializeLatency.testToData    avgt    5  2.552 ± 0.056  us/op
CapnProtoSerializeDeserializeLatency.testToObject  avgt    5  3.453 ± 2.940  us/op

Benchmark                                         Mode  Cnt  Score   Error  Units
PortableSerializeDeserializeLatency.testToData    avgt    5  1.782 ± 0.070  us/op
PortableSerializeDeserializeLatency.testToObject  avgt    5  1.461 ± 0.021  us/op

Benchmark                                            Mode  Cnt  Score   Error  Units
FlexBuffersSerializeDeserializeLatency.testToData    avgt    5  1.526 ± 0.024  us/op
FlexBuffersSerializeDeserializeLatency.testToObject  avgt    5  0.537 ± 0.008  us/op

Benchmark                                          Mode  Cnt  Score   Error  Units
CapnProtoSerializeDeserializeLatency.testToData    avgt    5  2.162 ± 0.028  us/op
CapnProtoSerializeDeserializeLatency.testToObject  avgt    5  0.593 ± 0.010  us/op

Benchmark                                      Mode  Cnt  Score   Error  Units
ProtoSerializeDeserializeLatency.testToData    avgt    5  0.340 ± 0.009  us/op
ProtoSerializeDeserializeLatency.testToObject  avgt    5  0.350 ± 0.006  us/op

Benchmark                                            Mode  Cnt  Score   Error  Units
FlatBuffersSerializeDeserializeLatency.testToData    avgt    5  0.925 ± 0.051  us/op
FlatBuffersSerializeDeserializeLatency.testToObject  avgt    5  0.545 ± 0.006  us/op 