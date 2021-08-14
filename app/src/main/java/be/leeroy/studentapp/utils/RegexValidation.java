package be.leeroy.studentapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidation {

    public static Boolean email(String email){
        Pattern emailPattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher mailMatcher = emailPattern.matcher(email);
        return mailMatcher.matches();
    }

    public static Boolean password(String password){
        Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        return passwordMatcher.matches();
    }

}
