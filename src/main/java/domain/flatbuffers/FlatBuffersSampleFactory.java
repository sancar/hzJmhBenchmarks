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

package domain.flatbuffers;

import com.google.flatbuffers.FlatBufferBuilder;
import domain.MetadataCreator;

public class FlatBuffersSampleFactory {

    public static FlatBufferBuilder create(MetadataCreator creator) {
        FlatBufferBuilder builder = new FlatBufferBuilder(1024);

        int city = builder.createString(creator.getCity());
        int country = builder.createString(creator.getCountry());

        int location = Location.createLocation(builder, city, country);

        int description = builder.createString(creator.getDescription());
        int name = builder.createString(creator.getName());
        int screenName = builder.createString(creator.getScreenName());
        int url = builder.createString(creator.getUrl());
        int user = User.createUser(builder, description, creator.getId(), location, name, screenName, url);

        int createdAt = builder.createString(creator.getCreatedAt());
        int id = builder.createString(creator.getIdStr());
        int text = builder.createString(creator.getText());

        TweetObject.startTweetObject(builder);
        TweetObject.addCreatedAt(builder, createdAt);
        TweetObject.addId(builder, id);
        TweetObject.addText(builder, text);
        TweetObject.addUser(builder, user);
        int root = TweetObject.endTweetObject(builder);

        builder.finish(root); // You could also call `Monster.finishMonsterBuffer(builder, orc);`.
        return builder;


    }
}
