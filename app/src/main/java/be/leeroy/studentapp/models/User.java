package be.leeroy.studentapp.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.GregorianCalendar;

@Entity
public class User {
    @PrimaryKey
    private String email;
    private String password;
    private String lastname;
    private GregorianCalendar birthday;
    private Integer bloc;
    private String role;
    private String optionName;
    private Integer optionSchool;
    private String token;

    public User(String email, String password, String lastname, GregorianCalendar birthday, Integer bloc, String role, String optionName, Integer optionSchool, String token) {
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.birthday = birthday;
        this.bloc = bloc;
        this.role = role;
        this.optionName = optionName;
        this.optionSchool = optionSchool;
        this.token = token;
    }

    public User() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public GregorianCalendar getBirthday() {
        return birthday;
    }

    public void setBirthday(GregorianCalendar birthday) {
        this.birthday = birthday;
    }

    public Integer getBloc() {
        return bloc;
    }

    public void setBloc(Integer bloc) {
        this.bloc = bloc;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Integer getOptionSchool() {
        return optionSchool;
    }

    public void setOptionSchool(Integer optionSchool) {
        this.optionSchool = optionSchool;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
