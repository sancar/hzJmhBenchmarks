package domain.identified;

import com.hazelcast.nio.serialization.IdentifiedDataSerializable;
import domain.dataserializable.DSTweetUserObject;

public class IDSTweetUserObject extends DSTweetUserObject implements IdentifiedDataSerializable {

    static final int CLASS_ID = 2;

    @Override
    public int getClassId() {
        return CLASS_ID;
    }

    @Override
    public int getFactoryId() {
        return IdentifiedDataSerializableObjectFactory.FACTORY_ID;
    }
}
