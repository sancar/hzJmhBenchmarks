/*
 * Copyright (c) 2008-2020, Hazelcast, Inc. All Rights Reserved.
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

import com.hazelcast.internal.serialization.Data;
import com.hazelcast.internal.serialization.SerializationService;
import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import domain.MetadataCreator;
import domain.TweetObject;
import domain.identified.IdentifiedDataSerializableObjectFactory;
import domain.identified.IdentifiedSampleFactory;

public class Main {

    public static void main(String[] args) {



        DefaultSerializationServiceBuilder builder = new DefaultSerializationServiceBuilder();
        SerializationService ss = builder.addDataSerializableFactory(IdentifiedDataSerializableObjectFactory.FACTORY_ID,
                new IdentifiedDataSerializableObjectFactory()).build();

        MetadataCreator metadataCreator = new MetadataCreator();
        TweetObject tweetObject = IdentifiedSampleFactory.create(metadataCreator);

        Data data = ss.toData(tweetObject);
        System.out.println(data.toByteArray().length);

    }
}
