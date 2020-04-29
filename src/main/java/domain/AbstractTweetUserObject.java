package domain;

import java.io.Serializable;
import java.util.Objects;

public class AbstractTweetUserObject implements TweetUserObject, Serializable {

    protected int id;
    protected String name;
    protected String screenName;
    protected TweetLocationObject location;
    protected String url;
    protected String description;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getScreenName() {
        return screenName;
    }

    @Override
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    @Override
    public TweetLocationObject getLocation() {
        return location;
    }

    @Override
    public void setLocation(TweetLocationObject location) {
        this.location = location;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractTweetUserObject that = (AbstractTweetUserObject) o;

        if (id != that.id) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(screenName, that.screenName)) return false;
        if (!Objects.equals(location, that.location)) return false;
        if (!Objects.equals(url, that.url)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (screenName != null ? screenName.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractTweetUserObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", screenName='" + screenName + '\'' +
                ", location=" + location +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
