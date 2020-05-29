package domain.compact;


import java.util.Objects;

public class CompactTweetUserObject {

    protected int id;
    protected String name;
    protected String screenName;
    protected CompactTweetLocationObject location;
    protected String url;
    protected String description;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getScreenName() {
        return screenName;
    }


    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }


    public CompactTweetLocationObject getLocation() {
        return location;
    }


    public void setLocation(CompactTweetLocationObject location) {
        this.location = location;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompactTweetUserObject that = (CompactTweetUserObject) o;

        if (id != that.id) {
            return false;
        }
        if (!Objects.equals(name, that.name)) {
            return false;
        }
        if (!Objects.equals(screenName, that.screenName)) {
            return false;
        }
        if (!Objects.equals(location, that.location)) {
            return false;
        }
        if (!Objects.equals(url, that.url)) {
            return false;
        }
        return Objects.equals(description, that.description);
    }


    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (screenName != null ? screenName.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }


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
