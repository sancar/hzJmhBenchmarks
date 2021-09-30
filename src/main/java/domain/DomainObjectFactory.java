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
package domain;

import domain.dataserializable.DSTweetLocationObject;
import domain.dataserializable.DSTweetObject;
import domain.dataserializable.DSTweetUserObject;
import domain.identified.IDSTweetLocationObject;
import domain.identified.IDSTweetObject;
import domain.identified.IDSTweetUserObject;
import domain.portable.PortableTweetLocationObject;
import domain.portable.PortableTweetObject;
import domain.portable.PortableTweetUserObject;
import domain.object.SerializableTweetLocationObject;
import domain.object.SerializableTweetObject;
import domain.object.SerializableTweetUserObject;

public final class DomainObjectFactory {

    public enum Strategy {
        PORTABLE,
        SERIALIZABLE,
        DATA_SERIALIZABLE,
        IDENTIFIED_DATA_SERIALIZABLE,
        JSON
    }

    private final Strategy strategy;

    private DomainObjectFactory(Strategy strategy) {
        this.strategy = strategy;
    }

    public static DomainObjectFactory newFactory(Strategy strategy) {
        return new DomainObjectFactory(strategy);
    }

    public TweetObject newObject() {
        switch (strategy) {
            case PORTABLE:
                return new PortableTweetObject();
            case SERIALIZABLE:
                return new SerializableTweetObject();
            case DATA_SERIALIZABLE:
                return new DSTweetObject();
            case IDENTIFIED_DATA_SERIALIZABLE:
                return new IDSTweetObject();
            default:
                throw new IllegalStateException("Unknown strategy: " + strategy);
        }
    }

    public TweetUserObject newUserObject() {
        switch (strategy) {
            case PORTABLE:
                return new PortableTweetUserObject();
            case SERIALIZABLE:
                return new SerializableTweetUserObject();
            case DATA_SERIALIZABLE:
                return new DSTweetUserObject();
            case IDENTIFIED_DATA_SERIALIZABLE:
                return new IDSTweetUserObject();
            default:
                throw new IllegalStateException("Unknown strategy: " + strategy);
        }
    }

    public TweetLocationObject newLocationObject() {
        switch (strategy) {
            case PORTABLE:
                return new PortableTweetLocationObject();
            case SERIALIZABLE:
                return new SerializableTweetLocationObject();
            case DATA_SERIALIZABLE:
                return new DSTweetLocationObject();
            case IDENTIFIED_DATA_SERIALIZABLE:
                return new IDSTweetLocationObject();
            default:
                throw new IllegalStateException("Unknown strategy: " + strategy);
        }
    }
}