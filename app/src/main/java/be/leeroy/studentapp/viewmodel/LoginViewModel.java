package be.leeroy.studentapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import be.leeroy.studentapp.dataaccess.UserDataAccess;
import be.leeroy.studentapp.dataaccess.bodymodels.LoginBodyModel;
import be.leeroy.studentapp.dataaccess.dto.UserDTO;
import be.leeroy.studentapp.dataaccess.mappers.UserMapper;
import be.leeroy.studentapp.models.User;
import be.leeroy.studentapp.services.RetrofitConfigurationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<User> _user = new MutableLiveData<>();
    private LiveData<User> user = _user;

    private MutableLiveData<NetworkError> _error = new MutableLiveData<>();
    private LiveData<NetworkError> error = _error;

    private UserDataAccess userDataAccess;
    private UserMapper userMapper;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        this.userDataAccess = RetrofitConfigurationService.getInstance(application).userDataAccess();
        this.userMapper = UserMapper.getInstance();
    }

    public void loginUser(String email, String password){
        userDataAccess.login(new LoginBodyModel(email, password)).enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {

            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {

            }
        });
    }
}
