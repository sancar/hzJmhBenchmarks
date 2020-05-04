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

package domain.flexbuffers;

import com.google.flatbuffers.FlexBuffersBuilder;
import domain.MetadataCreator;

import java.nio.ByteBuffer;

public class FlexBuffersSampleFactory {

    public static ByteBuffer create(MetadataCreator creator) {
        FlexBuffersBuilder builder = new FlexBuffersBuilder(1024);
        int svec = builder.startVector();
        builder.putString(creator.getCreatedAt());
        builder.putString(creator.getIdStr());
        builder.putString(creator.getText());
        int userVector = builder.startVector();
        builder.putString(creator.getDescription());
        builder.putInt(creator.getId());
        int locationVector = builder.startVector();
        builder.putString(creator.getCity());
        builder.putString(creator.getCountry());
        builder.endVector("location", locationVector, false, false);
        builder.putString(creator.getName());
        builder.putString(creator.getScreenName());
        builder.putString(creator.getUrl());
        builder.endVector("user", userVector, false, false);
        builder.endVector("root", svec, false, false);
        return builder.finish();
    }
}
