package be.leeroy.studentapp.dataaccess;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginDataAccess {

    @POST("login")
    Call<String> login(@Body RequestBody body);
}
