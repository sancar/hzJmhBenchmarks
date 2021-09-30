package serialization.compact;

import com.hazelcast.config.SerializationConfig;
import com.hazelcast.internal.serialization.InternalSerializationService;
import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import com.hazelcast.internal.serialization.impl.compact.CompactTestUtil;

public final class HazelcastUtils {
    private HazelcastUtils() {

    }

    public static InternalSerializationService newCompactEnabledSerializationService() {
        SerializationConfig serializationConfig = new SerializationConfig();
        serializationConfig.getCompactSerializationConfig().setEnabled(true);
        return new DefaultSerializationServiceBuilder()
                .setConfig(serializationConfig)
                .setSchemaService(CompactTestUtil.createInMemorySchemaService())
                .build();
    }
}
