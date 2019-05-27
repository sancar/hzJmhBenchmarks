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

package protocol;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
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
@Warmup(iterations = 10)
@Measurement(iterations = 5)
public class PutPerformanceLatencyBenchmark {

    private IMap<Object, Object> map;
    //    private HazelcastInstance hazelcastInstance;
    private HazelcastInstance client;

    @Setup
    public void prepare() throws InterruptedException {

//        hazelcastInstance = Hazelcast.newHazelcastInstance();
        ClientConfig config = new ClientConfig();
        config.setGroupConfig(new GroupConfig().setName("sancar"));

        config.getNetworkConfig().addAddress("10.212.1.118");

        client = HazelcastClient.newHazelcastClient(config);
        String name = "mymap";
        map = client.getMap(name);

    }

    @TearDown
    public void teardown() throws InterruptedException {
        client.shutdown();
//        hazelcastInstance.shutdown();
    }


    @Benchmark
    public Object testPut() throws IOException {
        return map.put(1, new byte[1024]);
    }


    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(PutPerformanceLatencyBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}