package be.leeroy.studentapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.GregorianCalendar;

@Entity
public class Publication {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String content;
    private GregorianCalendar date;
    private String user;

    public Publication(Integer id, String content, GregorianCalendar date, String user) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.user = user;
    }

    public Publication() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
