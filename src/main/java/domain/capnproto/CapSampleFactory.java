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

package domain.capnproto;

import domain.MetadataCreator;

public class CapSampleFactory {
    public static org.capnproto.MessageBuilder create(MetadataCreator creator) {
        org.capnproto.MessageBuilder message = new org.capnproto.MessageBuilder();

        CapTweetObject.TweetObject.Builder tweetObjectBuilder = message.initRoot(CapTweetObject.TweetObject.factory);
        tweetObjectBuilder.setCreatedAt(creator.getCreatedAt());
        tweetObjectBuilder.setId(creator.getIdStr());
        tweetObjectBuilder.setText(creator.getText());

        CapTweetObject.User.Builder userbuilder = tweetObjectBuilder.initUser();
        userbuilder.setDescription(creator.getDescription());
        userbuilder.setId(creator.getId());
        userbuilder.setName(creator.getName());
        userbuilder.setScreenName(creator.getScreenName());
        userbuilder.setUrl(creator.getUrl());

        CapTweetObject.Location.Builder locationBuilder = userbuilder.initLocation();
        locationBuilder.setCity(creator.getCity());
        locationBuilder.setCountry(creator.getCountry());

        return message;

    }
}
