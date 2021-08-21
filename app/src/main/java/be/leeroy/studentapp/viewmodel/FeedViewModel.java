package be.leeroy.studentapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import be.leeroy.studentapp.dataaccess.PublicationDataAccess;
import be.leeroy.studentapp.dataaccess.UserDataAccess;
import be.leeroy.studentapp.dataaccess.dto.PublicationDTO;
import be.leeroy.studentapp.dataaccess.dto.UserDTO;
import be.leeroy.studentapp.dataaccess.mappers.PublicationMapper;
import be.leeroy.studentapp.dataaccess.mappers.UserMapper;
import be.leeroy.studentapp.exceptions.NoConnectivityException;
import be.leeroy.studentapp.models.Publication;
import be.leeroy.studentapp.models.User;
import be.leeroy.studentapp.models.errors.Errors;
import be.leeroy.studentapp.services.RetrofitConfigurationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedViewModel extends AndroidViewModel {
    private final MutableLiveData<User> _user = new MutableLiveData<>();
    private final LiveData<User> user = _user;

    private final MutableLiveData<Publication[]> _publications = new MutableLiveData<>();
    private final LiveData<Publication[]> publications = _publications;

    private final MutableLiveData<Errors> _error = new MutableLiveData<>();
    private final LiveData<Errors> error = _error;

    private final UserDataAccess userDataAccess;
    private final UserMapper userMapper;

    private final PublicationDataAccess publicationDataAccess;
    private final PublicationMapper publicationMapper;

    public FeedViewModel(@NonNull Application application){
        super(application);

        userDataAccess = RetrofitConfigurationService.getInstance(application).userDataAccess();
        userMapper = UserMapper.getInstance();

        publicationDataAccess = RetrofitConfigurationService.getInstance(application).publicationDataAccess();
        publicationMapper = PublicationMapper.getInstance();
    }

    public void loadPublications(String token) {
        publicationDataAccess.getPublications(token).enqueue(new Callback<PublicationDTO[]>() {
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

    public void loadCurrentUser(String token){
        userDataAccess.getCurrentUser(token).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(@NotNull Call<UserDTO> call, @NotNull Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    _user.setValue(userMapper.mapToUser(response.body()));
                    _error.setValue(null);
                } else {
                    _error.setValue(Errors.REQUEST_ERROR);
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserDTO> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _error.setValue(Errors.NO_CONNECTION);
                } else {
                    _error.setValue(Errors.TECHNICAL_ERROR);
                }
            }
        });
    }

    public LiveData<User> getUser() {
        return user;
    }

    public LiveData<Publication[]> getPublications() {
        return publications;
    }

    public LiveData<Errors> getError() {
        return error;
    }
}
