package be.leeroy.studentapp.dataaccess;

import be.leeroy.studentapp.dataaccess.dto.PublicationDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface PublicationDataAccess {

    @GET("publication")
    Call<PublicationDTO[]> getPublications(@Header("Authorization") String token);

}
