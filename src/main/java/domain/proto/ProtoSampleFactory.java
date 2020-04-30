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

package domain.proto;

import domain.MetadataCreator;

public class ProtoSampleFactory {

    public static ProtoTweetObject.TweetObject create(MetadataCreator creator) {
        ProtoTweetObject.TweetObject.Builder tweetObjectBuilder = ProtoTweetObject.TweetObject.newBuilder();
        ProtoTweetObject.User.Builder userbuilder = tweetObjectBuilder.getUserBuilder();

        ProtoTweetObject.Location.Builder locationBuilder = userbuilder.getLocationBuilder();
        locationBuilder.setCity(creator.getCity());
        locationBuilder.setCountry(creator.getCountry());


        userbuilder.setDescription(creator.getDescription());
        userbuilder.setId(creator.getId());
        userbuilder.setName(creator.getName());
        userbuilder.setScreenName(creator.getScreenName());
        userbuilder.setUrl(creator.getUrl());
        userbuilder.setLocation(locationBuilder.build());


        tweetObjectBuilder.setCreatedEt(creator.getCreatedAt());
        tweetObjectBuilder.setId(creator.getIdStr());
        tweetObjectBuilder.setText(creator.getText());
        tweetObjectBuilder.setUser(userbuilder.build());

        return tweetObjectBuilder.build();

    }
}
