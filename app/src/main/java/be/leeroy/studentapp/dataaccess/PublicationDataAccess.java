package be.leeroy.studentapp.dataaccess;

import be.leeroy.studentapp.dataaccess.dto.PublicationDTO;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PublicationDataAccess {

    @GET("publication")
    Call<PublicationDTO[]> getPublications(@Header("Authorization") String headerAuth, @Query("user") String userEmail);

    @POST("publication")
    Call<ResponseBody> createPublication(@Header("Authorization") String headerAuth, @Body RequestBody body);
}
