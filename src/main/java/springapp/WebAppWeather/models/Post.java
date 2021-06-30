package springapp.WebAppWeather.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Post {
    @Id
    private long id;
    private String full_text;

    public Post(int id, String full_text) {
        this.id = id;
        this.full_text = full_text;
    }

    public Post(String full_text) {
        this.full_text = full_text;
    }

    public Post() {
    }

    public long getId() {
        return id;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }
}
