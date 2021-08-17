package be.leeroy.studentapp.models;

import java.util.GregorianCalendar;

public class Publication {

    private int id;
    private User user;
    private String content;
    private GregorianCalendar date;
    private Comment[] comments;
    private User[] likes;
    private User[] reports;

    public Publication(int id, User user, String content, GregorianCalendar date, Comment[] comments, User[] likes, User[] reports) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.date = date;
        this.comments = comments;
        this.likes = likes;
        this.reports = reports;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User[] getLikes() {
        return likes;
    }

    public void setLikes(User[] likes) {
        this.likes = likes;
    }

    public User[] getReports() {
        return reports;
    }

    public void setReports(User[] reports) {
        this.reports = reports;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }
}
