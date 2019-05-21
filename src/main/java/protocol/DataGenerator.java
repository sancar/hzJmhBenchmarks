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

import com.hazelcast.client.impl.protocol.ClientMessage;
import com.hazelcast.client.impl.protocol.codec.MapPutCodec;
import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import com.hazelcast.nio.serialization.Data;
import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import com.hazelcast.spi.serialization.SerializationService;

import java.util.ArrayList;
import java.util.Collection;

public class DataGenerator {

    static String name = "mymap";

    static Data createData() {
        DefaultSerializationServiceBuilder serializationServiceBuilder = new DefaultSerializationServiceBuilder();
        serializationServiceBuilder.addDataSerializableFactory(1, new DataSerializableFactory() {
            public IdentifiedDataSerializable create(int typeId) {
                return new Employee();
            }
        });
        SerializationService ss = serializationServiceBuilder.build();
        Collection<Employee> list = new ArrayList<Employee>(10000);
        for (int i = 0; i < 125000; i++) {
            list.add(new Employee("sancar", "koyunlu", 27));
        }
        return ss.toData(list);
    }

    static ClientMessage createPutRequest() {
        Data data = createData();
        ClientMessage clientMessage = MapPutCodec.encodeRequest(name, data, data, 1, 1);
        return ClientMessage.createForDecode(clientMessage.buffer(), 0);

    }
}
