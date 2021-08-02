package be.leeroy.studentapp.dataaccess;

import be.leeroy.studentapp.dataaccess.bodymodels.LoginBodyModel;
import be.leeroy.studentapp.dataaccess.dto.UserDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserDataAccess {

    @POST("login")
    Call<UserDTO> login(@Body LoginBodyModel body);
}