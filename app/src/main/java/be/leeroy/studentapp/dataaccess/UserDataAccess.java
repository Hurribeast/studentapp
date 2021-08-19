package be.leeroy.studentapp.dataaccess;

import be.leeroy.studentapp.dataaccess.dto.UserDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserDataAccess {

    @GET("user/my")
    public Call<UserDTO> getCurrentUser(@Header("Authorization") String token);

}