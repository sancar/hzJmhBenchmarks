package domain.identified;

import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import domain.dataserializable.DSTweetLocationObject;

public class IDSTweetLocationObject extends DSTweetLocationObject implements IdentifiedDataSerializable {

    static final int CLASS_ID = 3;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public int getFactoryId() {
        return IdentifiedDataSerializableObjectFactory.FACTORY_ID;
    }
}
