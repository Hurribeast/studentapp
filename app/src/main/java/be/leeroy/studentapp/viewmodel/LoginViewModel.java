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
import be.leeroy.studentapp.models.errors.Errors;
import be.leeroy.studentapp.services.RetrofitConfigurationService;
import be.leeroy.studentapp.utils.ApiUtils;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {
    private final MutableLiveData<String> _token = new MutableLiveData<>();
    private final LiveData<String> token = _token;

    private final MutableLiveData<Errors> _error = new MutableLiveData<>();
    private final LiveData<Errors> error = _error;

    private final LoginDataAccess loginDataAccess;

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
                } else if(response.code() == 404) {
                    _error.setValue(Errors.PASSWORD_INCORRECT);
                } else {
                    _error.setValue(Errors.REQUEST_ERROR);
                }
            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _error.setValue(Errors.NO_CONNECTION);
                } else {
                    _error.setValue(Errors.TECHNICAL_ERROR);
                }
            }
        });
    }

    public LiveData<String> getToken() {
        return token;
    }

    public LiveData<Errors> getError() {
        return error;
    }
}
