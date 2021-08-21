package be.leeroy.studentapp.dataaccess;

import be.leeroy.studentapp.dataaccess.dto.OptionDTO;
import be.leeroy.studentapp.dataaccess.dto.SchoolDTO;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SchoolDataAccess {
    @GET("school")
    Call<SchoolDTO[]> getSchools();

    @POST("school/options")
    Call<OptionDTO[]> getOptions(@Body RequestBody requestBody);
}
