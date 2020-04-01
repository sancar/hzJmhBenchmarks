 # Query Latency Results
 
Avro Fast Query Enabled was giving worst results.
These results for fast query disabled.  
 
 
 Benchmark                                         Mode  Cnt  Score   Error  Units
 PortableQueryLatency.testQueryCreatedAt           avgt    5  0.196 ± 0.031  us/op
 AvroQueryLatency.testQueryCreatedAt               avgt    5  1.427 ± 0.013  us/op
 PortableQueryLatency.testQueryUser_location_city  avgt    5  0.840 ± 0.128  us/op
 AvroQueryLatency.testQueryUser_location_city      avgt    5  1.626 ± 0.113  us/op

Benchmark                       Mode  Cnt  Score   Error  Units
PortableQueryLatency.testQuery  avgt    5  0.701 ± 0.011  us/op
AvroQueryLatency.testQuery      avgt    5  1.585 ± 0.061  us/op

 # Query Throughput Results
 
Benchmark                                      Mode   Cnt        Score       Error  Units
PortableQueryThpt.testQueryCreatedAt           thrpt    5  4948343.917 ± 975196.333  ops/s
AvroQueryThpt.testQueryCreatedAt               thrpt    5   649053.728 ± 163380.438  ops/s
PortableQueryThpt.testQueryUser_location_city  thrpt    5  1230383.869 ±  45130.861  ops/s
AvroQueryThpt.testQueryUser_location_city      thrpt    5   496449.031 ± 239255.988  ops/s