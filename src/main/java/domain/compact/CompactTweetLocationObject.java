package domain.compact;

import java.util.Objects;

public class CompactTweetLocationObject {
    protected String country;
    protected String city;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompactTweetLocationObject that = (CompactTweetLocationObject) o;

        if (!Objects.equals(country, that.country)) {
            return false;
        }
        return Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractTweetLocationObject{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
