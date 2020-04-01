 # Query Latency Results
 
Avro Fast Query Enabled was giving worst results.
These results for fast query disabled.  
 
Benchmark                       Mode  Cnt  Score   Error  Units
PortableQueryLatency.testQuery  avgt    5  0.701 ± 0.011  us/op
AvroQueryLatency.testQuery      avgt    5  1.585 ± 0.061  us/op

 # Query Throughput Results
 
Benchmark                     Mode  Cnt        Score       Error  Units
PortableQueryThpt.testQuery  thrpt    5  1197845.771 ± 48324.441  ops/s
AvroQueryThpt.testQuery      thrpt    5   608788.068 ± 9930.381   ops/s
