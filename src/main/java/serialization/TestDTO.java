package serialization;

import com.hazelcast.nio.serialization.Portable;
import com.hazelcast.nio.serialization.PortableReader;
import com.hazelcast.nio.serialization.PortableWriter;

import java.io.IOException;

public class TestDTO implements Portable {

    private long id;

    public TestDTO() {
    }

    public TestDTO(long id) {
        this.id = id;
    }

    @Override
    public int getFactoryId() {
        return 1;
    }

    @Override
    public int getClassId() {
        return 1;
    }

    @Override
    public void writePortable(PortableWriter writer) throws IOException {
        writer.writeLong("i", id);
    }

    @Override
    public void readPortable(PortableReader reader) throws IOException {
        id = reader.readLong("i");
    }
}
