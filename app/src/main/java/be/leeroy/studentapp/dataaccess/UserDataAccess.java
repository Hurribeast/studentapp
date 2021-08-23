package be.leeroy.studentapp.dataaccess;

import be.leeroy.studentapp.dataaccess.dto.UserDTO;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface UserDataAccess {

    @GET("user/my")
    Call<UserDTO> getCurrentUser(@Header("Authorization") String token);

    @POST("user/profile")
    Call<UserDTO> getUser(@Header("Authorization") String token, @Body RequestBody body);

    @POST("user")
    Call<String> registerUser(@Body RequestBody body);

    @PATCH("user/password")
    Call<ResponseBody> changePassword(@Header("Authorization") String token, @Body RequestBody body);
}