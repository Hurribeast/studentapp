package be.leeroy.studentapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import be.leeroy.studentapp.dataaccess.SchoolDataAccess;
import be.leeroy.studentapp.dataaccess.dto.OptionDTO;
import be.leeroy.studentapp.dataaccess.dto.SchoolDTO;
import be.leeroy.studentapp.dataaccess.mappers.SchoolMapper;
import be.leeroy.studentapp.exceptions.NoConnectivityException;
import be.leeroy.studentapp.models.Option;
import be.leeroy.studentapp.models.School;
import be.leeroy.studentapp.models.errors.Errors;
import be.leeroy.studentapp.services.RetrofitConfigurationService;
import be.leeroy.studentapp.utils.ApiUtils;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends AndroidViewModel {
    private final MutableLiveData<ArrayList<School>> _schools = new MutableLiveData<>();
    private final LiveData<ArrayList<School>> schools = _schools;

    private final MutableLiveData<ArrayList<Option>> _options = new MutableLiveData<>();
    private final LiveData<ArrayList<Option>> options = _options;

    private final MutableLiveData<Errors> _error = new MutableLiveData<>();
    private final LiveData<Errors> error = _error;

    private final SchoolDataAccess schoolDataAccess;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        this.schoolDataAccess = RetrofitConfigurationService.getInstance(application).schoolDataAccess();


    }

    public void registerUser() {

    }

    public void loadSchools() {
        schoolDataAccess.getSchools().enqueue(new Callback<SchoolDTO[]>() {
            @Override
            public void onResponse(@NotNull Call<SchoolDTO[]> call, @NonNull Response<SchoolDTO[]> response) {
                if(response.isSuccessful()) {
                    SchoolDTO [] schoolsDTO = response.body();
                    int length = schoolsDTO.length;
                    ArrayList<School> schools = new ArrayList<>();
                    for(int i = 0; i < length; i++) {
                        schools.add(SchoolMapper.getInstance().mapToSchool(schoolsDTO[i]));
                    }
                    _schools.setValue(schools);
                    _error.setValue(null);
                } else {
                    _error.setValue(Errors.TECHNICAL_ERROR);
                }
            }

            @Override
            public void onFailure(@NotNull Call<SchoolDTO[]> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _error.setValue(Errors.NO_CONNECTION);
                } else {
                    _error.setValue(Errors.TECHNICAL_ERROR);
                }
            }
        });
    }

    public void loadOptions(int schoolID) {
        HashMap<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("schoolID", schoolID);

        RequestBody requestBody = ApiUtils.ToRequestBody(bodyParams);

        schoolDataAccess.getOptions(requestBody).enqueue(new Callback<OptionDTO[]>() {
            @Override
            public void onResponse(@NotNull Call<OptionDTO[]> call, @NotNull Response<OptionDTO[]> response) {
                if(response.isSuccessful()) {
                    OptionDTO [] optionsDTO = response.body();
                    int length = optionsDTO.length;
                    ArrayList<Option> options = new ArrayList<>();
                    for(int i = 0; i < length; i++) {
                        options.add(SchoolMapper.getInstance().mapToOption(optionsDTO[i]));
                    }
                    _options.setValue(options);
                    _error.setValue(null);
                } else {
                    _error.setValue(Errors.TECHNICAL_ERROR);
                }
            }

            @Override
            public void onFailure(@NotNull Call<OptionDTO[]> call, @NotNull Throwable t) {
                if (t instanceof NoConnectivityException) {
                    _error.setValue(Errors.NO_CONNECTION);
                } else {
                    _error.setValue(Errors.TECHNICAL_ERROR);
                }
            }
        });
    }

    public LiveData<ArrayList<School>> getSchools() {
        return this.schools;
    }
    public LiveData<ArrayList<Option>> getOptions() {
        return this.options;
    }
}
