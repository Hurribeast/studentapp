package be.leeroy.studentapp.dataaccess.mappers;

import be.leeroy.studentapp.dataaccess.dto.UserDTO;
import be.leeroy.studentapp.models.User;

public class UserMapper {

    private static UserMapper instance = null;

    public static UserMapper getInstance() {
        if (instance == null) instance = new UserMapper();

        return instance;
    }

    public User mapToUser(UserDTO userDTO) {
        if(userDTO == null) return null;

        return new User();
    }
}
