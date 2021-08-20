package be.leeroy.studentapp.models;

import java.util.GregorianCalendar;

public class Publication {

    private int id;
    private User user;
    private String content;
    private GregorianCalendar date;
    private Comment[] comments;
    private int nbLikes;
    private int nbReports;

    public Publication(int id, User user, String content, GregorianCalendar date, Comment[] comments, int nbLikes, int nbReports) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.date = date;
        this.comments = comments;
        this.nbLikes = nbLikes;
        this.nbReports = nbReports;
    }

    public Publication(int id, User user, String content, GregorianCalendar date, int nbLikes, int nbReports) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.date = date;
        this.nbLikes = nbLikes;
        this.nbReports = nbReports;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbLikes() {
        return nbLikes;
    }

    public void setNbLikes(int nbLikes) {
        this.nbLikes = nbLikes;
    }

    public int getNbReports() {
        return nbReports;
    }

    public void setNbReports(int nbReports) {
        this.nbReports = nbReports;
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
