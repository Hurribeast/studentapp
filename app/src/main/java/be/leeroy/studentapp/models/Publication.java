package be.leeroy.studentapp.models;

import java.util.GregorianCalendar;

public class Publication {

    private int id;
    private User user;
    private String content;
    private GregorianCalendar date;
    private int nbLikes;
    private int nbReports;
    private int nbComments;

    public Publication(int id, User user, String content, GregorianCalendar date, int nbLikes, int nbReports, int nbComments) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.date = date;
        this.nbComments = nbComments;
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

    public int getNbComments() {
        return nbComments;
    }

    public void setNbComments(int nbComments) {
        this.nbComments = nbComments;
    }
}
