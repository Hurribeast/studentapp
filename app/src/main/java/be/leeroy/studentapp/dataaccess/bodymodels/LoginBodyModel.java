package be.leeroy.studentapp.dataaccess.bodymodels;

public class LoginBodyModel {
    private String email;
    private String password;

    public LoginBodyModel(String email, String password){
        this.email = email;
        this.password = password;
    }
}
