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

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

import java.io.IOException;
import java.io.Serializable;

public class Employee implements IdentifiedDataSerializable, Serializable {
    private String name;
    private String surname;
    private long age;

    public Employee() {
    }


    public Employee(String name, String surname, long age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public int getFactoryId() {
        return 1;
    }

    public int getId() {
        return 1;
    }

    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(name);
        out.writeUTF(surname);
        out.writeLong(age);
    }

    public void readData(ObjectDataInput in) throws IOException {
        name = in.readUTF();
        surname = in.readUTF();
        age = in.readLong();
    }
}