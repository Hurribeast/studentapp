package be.leeroy.studentapp.models;

import java.util.GregorianCalendar;

public class Comment {

    private Integer id;
    private User user;
    private String content;
    private GregorianCalendar date;

    public Comment(Integer id, User user, String content, GregorianCalendar date) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
