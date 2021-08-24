package be.leeroy.studentapp.dataaccess.dto;

import com.squareup.moshi.Json;

public class UserDTO {

    private String email;

    private String lastname;
    private String firstname;
    private String birthday;

    private Integer schoolid = null;
    private String schoolname;
    private String schooladdress;
    private String schoolphonenumber;

    private String optionname;
    private Integer optionnbyears;

    private Integer bloc;

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(Integer schoolid) {
        this.schoolid = schoolid;
    }

    public String getSchoolname() {
        return schoolname;
    }

    public void setSchoolname(String schoolname) {
        this.schoolname = schoolname;
    }

    public String getSchooladdress() {
        return schooladdress;
    }

    public void setSchooladdress(String schooladdress) {
        this.schooladdress = schooladdress;
    }

    public String getSchoolphonenumber() {
        return schoolphonenumber;
    }

    public void setSchoolphonenumber(String schoolphonenumber) {
        this.schoolphonenumber = schoolphonenumber;
    }

    public String getOptionname() {
        return optionname;
    }

    public void setOptionname(String optionname) {
        this.optionname = optionname;
    }

    public Integer getOptionnbyears() {
        return optionnbyears;
    }

    public void setOptionnbyears(Integer optionnbyears) {
        this.optionnbyears = optionnbyears;
    }

    public Integer getBloc() {
        return bloc;
    }

    public void setBloc(Integer bloc) {
        this.bloc = bloc;
    }
}
