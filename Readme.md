# With BigEndian
| Benchmark                                        | Mode | Cnt | Score | Error   | Units |
|--------------------------------------------------|------|-----|-------|---------|-------|
| PortableSerializeDeserializeLatency.testToData   | avgt | 5   | 0.200 | ± 0.003 | us/op |
| PortableSerializeDeserializeLatency.testToObject | avgt | 5   | 0.142 | ± 0.001 | us/op |

# With LittleEndian(therefore Unsafe.putLong)

|Benchmark                                    |     Mode|  Cnt|  Score|   Error|  Units
|--------------------------------------------------|------|-----|-------|---------|-------
|PortableSerializeDeserializeLatency.testToData   | avgt   | 5|  0.184 |± 0.001  |us/op
|PortableSerializeDeserializeLatency.testToObject | avgt  |  5|  0.122 |± 0.006 | us/op
