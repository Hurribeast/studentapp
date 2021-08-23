package be.leeroy.studentapp.dataaccess;

import be.leeroy.studentapp.dataaccess.dto.UserDTO;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserDataAccess {

    @GET("user/my")
    Call<UserDTO> getCurrentUser(@Header("Authorization") String token);

    @POST("user/profile")
    Call<UserDTO> getUser(@Header("Authorization") String token, @Body RequestBody body);

}