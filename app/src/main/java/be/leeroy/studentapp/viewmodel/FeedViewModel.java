package be.leeroy.studentapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import be.leeroy.studentapp.dataaccess.UserDataAccess;
import be.leeroy.studentapp.dataaccess.dto.UserDTO;
import be.leeroy.studentapp.dataaccess.mappers.UserMapper;
import be.leeroy.studentapp.exceptions.NoConnectivityException;
import be.leeroy.studentapp.models.User;
import be.leeroy.studentapp.models.errors.Errors;
import be.leeroy.studentapp.services.RetrofitConfigurationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedViewModel extends AndroidViewModel {
    private final MutableLiveData<User> _user = new MutableLiveData<>();
    private final LiveData<User> user = _user;

    private final MutableLiveData<Errors> _error = new MutableLiveData<>();
    private final LiveData<Errors> error = _error;

    private final UserDataAccess userDataAccess;
    private final UserMapper userMapper;

    public FeedViewModel(@NonNull Application application){
        super(application);

        userDataAccess = RetrofitConfigurationService.getInstance(application).userDataAccess();
        userMapper = UserMapper.getInstance();
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

    public LiveData<Errors> getError() {
        return error;
    }
}
