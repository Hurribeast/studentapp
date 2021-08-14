package be.leeroy.studentapp.dataaccess;

import be.leeroy.studentapp.dataaccess.dto.UserDTO;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserDataAccess {

    @POST("login")
    Call<String> login(@Body RequestBody body);
}