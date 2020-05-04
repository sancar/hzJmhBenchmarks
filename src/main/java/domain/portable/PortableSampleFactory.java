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

package domain.portable;

import domain.MetadataCreator;
import domain.TweetLocationObject;
import domain.TweetObject;
import domain.TweetUserObject;

public class PortableSampleFactory {

    public static TweetObject create(MetadataCreator creator) {
        TweetLocationObject locationObject = new PortableTweetLocationObject();
        locationObject.setCity(creator.getCity());
        locationObject.setCountry(creator.getCountry());

        TweetUserObject userObject = new PortableTweetUserObject();
        userObject.setDescription(creator.getDescription());
        userObject.setId(creator.getId());
        userObject.setLocation(locationObject);
        userObject.setName(creator.getName());
        userObject.setScreenName(creator.getScreenName());
        userObject.setUrl(creator.getUrl());

        TweetObject object = new PortableTweetObject();
        object.setCreatedAt(creator.getCreatedAt());
        object.setIdStr(creator.getIdStr());
        object.setText(creator.getText());
        object.setUser(userObject);

        return object;
    }
}
