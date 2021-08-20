package be.leeroy.studentapp.dataaccess.dto;

public class UserDTO {

    private String email;

    private String lastname;
    private String firstname;
    private String birthday;

    private int schoolId;
    private String schoolName;
    private String schoolAddress;
    private String schoolPhoneNumber;

    private String optionName;
    private int optionNbYears;

    private int bloc;

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

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolAddress() {
        return schoolAddress;
    }

    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }

    public String getSchoolPhoneNumber() {
        return schoolPhoneNumber;
    }

    public void setSchoolPhoneNumber(String schoolPhoneNumber) {
        this.schoolPhoneNumber = schoolPhoneNumber;
    }

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getOptionNbYears() {
        return optionNbYears;
    }

    public void setOptionNbYears(int optionNbYears) {
        this.optionNbYears = optionNbYears;
    }

    public int getBloc() {
        return bloc;
    }

    public void setBloc(int bloc) {
        this.bloc = bloc;
    }
}
