package be.leeroy.studentapp.models;

import java.util.GregorianCalendar;

public class User {

    private String email;
    private String lastname;
    private String firstname;
    private GregorianCalendar birthday;
    private int bloc;
    private Option option;

    public User(String email, String lastname, String firstname, GregorianCalendar birthday, Integer bloc, Option option) {
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthday = birthday;
        this.bloc = bloc;
        this.option = option;
    }

    public User(String email, String lastname, String firstname) {
        this.email = email;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public GregorianCalendar getBirthday() {
        return birthday;
    }

    public void setBirthday(GregorianCalendar birthday) {
        this.birthday = birthday;
    }

    public int getBloc() {
        return bloc;
    }

    public void setBloc(int bloc) {
        this.bloc = bloc;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }


}
