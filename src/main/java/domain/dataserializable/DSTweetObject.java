/*
 * Copyright (c) 2008-2016, Hazelcast, Inc. All Rights Reserved.
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
package domain.dataserializable;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;
import domain.AbstractTweetObject;

import java.io.IOException;

public class DSTweetObject extends AbstractTweetObject implements DataSerializable {

    @Override
    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeUTF(createdAt);
        out.writeUTF(idStr);
        out.writeUTF(text);
        out.writeObject(user);
    }

    @Override
    public void readData(ObjectDataInput in) throws IOException {
        createdAt = in.readUTF();
        idStr = in.readUTF();
        text = in.readUTF();
        user = in.readObject();
    }
}