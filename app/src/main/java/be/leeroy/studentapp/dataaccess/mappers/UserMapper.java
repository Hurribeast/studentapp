package be.leeroy.studentapp.dataaccess.mappers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import be.leeroy.studentapp.dataaccess.dto.UserDTO;
import be.leeroy.studentapp.models.Option;
import be.leeroy.studentapp.models.School;
import be.leeroy.studentapp.models.User;

public class UserMapper {

    private static UserMapper instance = null;

    public static UserMapper getInstance() {
        if (instance == null) instance = new UserMapper();

        return instance;
    }

    public User mapToUser(UserDTO userDTO) {
        if(userDTO == null) return null;

        School school = null;
        Option option = null;

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.FRENCH);
        Date date = null;

        try {
            date = df.parse(userDTO.getBirthday());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar birthday = new GregorianCalendar();

        if (date != null) {
            birthday.setTime(date);
        }

        if(userDTO.getSchoolId() != 0) {
            school = new School(userDTO.getSchoolId(), userDTO.getSchoolName(), userDTO.getSchoolAddress(), userDTO.getSchoolPhoneNumber());
            option = new Option(school, userDTO.getOptionName(), userDTO.getOptionNbYears());
        }

        return new User(userDTO.getEmail(), userDTO.getLastname(), userDTO.getFirstname(), birthday, userDTO.getBloc(), option);
    }
}
