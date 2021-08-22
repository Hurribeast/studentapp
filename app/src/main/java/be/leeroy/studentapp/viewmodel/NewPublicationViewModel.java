package be.leeroy.studentapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import be.leeroy.studentapp.dataaccess.PublicationDataAccess;
import be.leeroy.studentapp.dataaccess.dto.PublicationDTO;
import be.leeroy.studentapp.exceptions.NoConnectivityException;
import be.leeroy.studentapp.models.errors.Errors;
import be.leeroy.studentapp.services.RetrofitConfigurationService;
import be.leeroy.studentapp.utils.ApiUtils;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPublicationViewModel extends AndroidViewModel {

    private final MutableLiveData<Boolean> _published = new MutableLiveData<>();
    private final LiveData<Boolean> published = _published;

    private final MutableLiveData<Errors> _error = new MutableLiveData<>();
    private final LiveData<Errors> error = _error;

    private final PublicationDataAccess publicationDataAccess;

    public NewPublicationViewModel(@NonNull Application application) {
        super(application);

        publicationDataAccess = RetrofitConfigurationService.getInstance(application).publicationDataAccess();
    }

    public void publishPublication(String content, String headerAuth){
        HashMap<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("content", content);
        RequestBody body = ApiUtils.ToRequestBody(bodyParams);

        publicationDataAccess.createPublication(headerAuth, body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    _published.setValue(true);
                    _error.setValue(null);
                } else {
                    _error.setValue(Errors.REQUEST_ERROR);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _error.setValue(Errors.NO_CONNECTION);
                } else {
                    _error.setValue(Errors.TECHNICAL_ERROR);
                }
            }
        });
    }

    public LiveData<Boolean> isPublished() {
        return published;
    }

    public LiveData<Errors> getError() {
        return error;
    }
}
