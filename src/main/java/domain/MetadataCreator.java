package domain;

import java.util.Date;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextLong;

public class MetadataCreator {

    private static final String[] COUNTRIES = new String[]{
            "UK", "US", "TR", "CZ", "FR"
    };

    private static final String[] CITIES = new String[]{
            "Gotham", "Metropolis", "Duckburg", "District X", "Riverdale", "Smallville", "Star City",
            "Stepford", "Emerald City", "Derry", "Zion"
    };

//    private Random random = new Random();

    public String getCreatedAt() {
        return new Date(nextLong(1 << 12, 1L << 41)).toString();
    }

    public String getIdStr() {
        return "" + 1;
    }

    public String getText() {
        return randomAlphanumeric(130);
    }

    public int getId() {
        return 1;
    }

    public String getName() {
        return randomAlphanumeric(6);
    }

    public String getScreenName() {
        return randomAlphanumeric(8);
    }

    public String getCountry() {
        return COUNTRIES[0];
    }

    public String getCity() {
        return CITIES[0];
    }

    public String getUrl() {
        return "www." + randomAlphanumeric(8) + ".com";
    }

    public String getDescription() {
        return randomAlphanumeric(50);
    }
}
