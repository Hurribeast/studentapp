package be.leeroy.studentapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import be.leeroy.studentapp.dataaccess.LoginDataAccess;
import be.leeroy.studentapp.exceptions.NoConnectivityException;
import be.leeroy.studentapp.models.NetworkError;
import be.leeroy.studentapp.services.RetrofitConfigurationService;
import be.leeroy.studentapp.utils.ApiUtils;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<String> _token = new MutableLiveData<>();
    private LiveData<String> token = _token;

    private MutableLiveData<NetworkError> _error = new MutableLiveData<>();
    private LiveData<NetworkError> error = _error;

    private LoginDataAccess loginDataAccess;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        this.loginDataAccess = RetrofitConfigurationService.getInstance(application).loginDataAccess();
    }

    public void loginUser(String email, String password){
        HashMap<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("email", email);
        bodyParams.put("password", password);

        RequestBody requestBody = ApiUtils.ToRequestBody(bodyParams);

        loginDataAccess.login(requestBody).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                if (response.isSuccessful()) {
                    _token.setValue(response.body());
                    _error.setValue(null);
                } else {
                    _error.setValue(NetworkError.REQUEST_ERROR);
                }
            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _error.setValue(NetworkError.NO_CONNECTION);
                } else {
                    _error.setValue(NetworkError.TECHNICAL_ERROR);
                }
            }
        });
    }

    public LiveData<String> getToken() {
        return token;
    }

    public LiveData<NetworkError> getError() {
        return error;
    }
}
