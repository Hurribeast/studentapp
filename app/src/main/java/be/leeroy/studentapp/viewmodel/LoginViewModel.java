package be.leeroy.studentapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONObject;

import java.util.HashMap;

import be.leeroy.studentapp.dataaccess.UserDataAccess;
import be.leeroy.studentapp.dataaccess.dto.UserDTO;
import be.leeroy.studentapp.dataaccess.mappers.UserMapper;
import be.leeroy.studentapp.exceptions.NoConnectivityException;
import be.leeroy.studentapp.models.NetworkError;
import be.leeroy.studentapp.models.User;
import be.leeroy.studentapp.services.RetrofitConfigurationService;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<User> _user = new MutableLiveData<>();
    private LiveData<User> user = _user;

    private MutableLiveData<String> _test = new MutableLiveData<>();
    private LiveData<String> test = _test;

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
        HashMap<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("email", email);
        bodyParams.put("password", password);

        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),(new JSONObject(bodyParams)).toString());

        userDataAccess.login(requestBody).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    _test.setValue(response.body());
                    _error.setValue(null);
                } else {
                    _error.setValue(NetworkError.REQUEST_ERROR);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _error.setValue(NetworkError.NO_CONNECTION);
                } else {
                    _error.setValue(NetworkError.TECHNICAL_ERROR);
                }
            }
        });
    }

    public LiveData<User> getUser() {
        return user;
    }

    public LiveData<String> getTest() {
        return test;
    }

    public LiveData<NetworkError> getError() {
        return error;
    }
}
