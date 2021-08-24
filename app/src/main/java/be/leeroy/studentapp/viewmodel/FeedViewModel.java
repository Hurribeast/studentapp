package be.leeroy.studentapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import be.leeroy.studentapp.dataaccess.PublicationDataAccess;
import be.leeroy.studentapp.dataaccess.dto.PublicationDTO;
import be.leeroy.studentapp.dataaccess.mappers.PublicationMapper;
import be.leeroy.studentapp.exceptions.NoConnectivityException;
import be.leeroy.studentapp.models.Publication;
import be.leeroy.studentapp.models.errors.Errors;
import be.leeroy.studentapp.services.RetrofitConfigurationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedViewModel extends AndroidViewModel {

    private final MutableLiveData<Publication[]> _publications = new MutableLiveData<>();
    private final LiveData<Publication[]> publications = _publications;

    private final MutableLiveData<Errors> _error = new MutableLiveData<>();
    private final LiveData<Errors> error = _error;

    private final PublicationDataAccess publicationDataAccess;
    private final PublicationMapper publicationMapper;

    public FeedViewModel(@NonNull Application application){
        super(application);


        publicationDataAccess = RetrofitConfigurationService.getInstance(application).publicationDataAccess();
        publicationMapper = PublicationMapper.getInstance();
    }

    public void loadPublications(String token) {
        publicationDataAccess.getPublications(token, null).enqueue(new Callback<PublicationDTO[]>() {
            @Override
            public void onResponse(@NotNull Call<PublicationDTO[]> call, @NotNull Response<PublicationDTO[]> response) {
                if (response.isSuccessful()) {
                    PublicationDTO[] publicationsDTO = response.body();
                    Publication[] publications = new Publication[publicationsDTO.length];

                    for (int i = 0; i < publicationsDTO.length; i++) {
                        publications[i] = publicationMapper.mapToPublication(publicationsDTO[i]);
                    }

                    _publications.setValue(publications);
                    _error.setValue(null);
                } else if (response.code() == 401) {
                    _error.setValue(Errors.TOKEN_EXPIRED);
                } else {
                    _error.setValue(Errors.REQUEST_ERROR);
                }
            }

            @Override
            public void onFailure(@NotNull Call<PublicationDTO[]> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _error.setValue(Errors.NO_CONNECTION);
                } else {
                    _error.setValue(Errors.TECHNICAL_ERROR);
                }
            }
        });
    }


    public LiveData<Publication[]> getPublications() {
        return publications;
    }

    public LiveData<Errors> getError() {
        return error;
    }
}
