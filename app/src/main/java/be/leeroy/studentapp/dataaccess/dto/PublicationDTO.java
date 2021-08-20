package be.leeroy.studentapp.dataaccess.dto;

import com.squareup.moshi.Json;

public class PublicationDTO {

    private int id;
    private String content;
    private String date;

    @Json(name = "user")
    private String email;
    private String firstname;
    private String lastname;

    private int nblikes;
    private int nbreports;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getNblikes() {
        return nblikes;
    }

    public void setNblikes(int nblikes) {
        this.nblikes = nblikes;
    }

    public int getNbreports() {
        return nbreports;
    }

    public void setNbreports(int nbreports) {
        this.nbreports = nbreports;
    }
}
