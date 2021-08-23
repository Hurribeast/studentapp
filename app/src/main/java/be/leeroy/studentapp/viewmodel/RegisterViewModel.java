package be.leeroy.studentapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import be.leeroy.studentapp.dataaccess.SchoolDataAccess;
import be.leeroy.studentapp.dataaccess.UserDataAccess;
import be.leeroy.studentapp.dataaccess.dto.OptionDTO;
import be.leeroy.studentapp.dataaccess.dto.SchoolDTO;
import be.leeroy.studentapp.dataaccess.mappers.SchoolMapper;
import be.leeroy.studentapp.exceptions.NoConnectivityException;
import be.leeroy.studentapp.models.Option;
import be.leeroy.studentapp.models.School;
import be.leeroy.studentapp.models.errors.Errors;
import be.leeroy.studentapp.services.RetrofitConfigurationService;
import be.leeroy.studentapp.utils.ApiUtils;
import be.leeroy.studentapp.utils.PreferencesUtils;
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

    private final MutableLiveData<String> _token = new MutableLiveData<>();
    private LiveData<String> token = _token;

    private final SchoolDataAccess schoolDataAccess;
    private final UserDataAccess userDataAccess;

    public RegisterViewModel(@NonNull Application application) {
        super(application);

        this.schoolDataAccess = RetrofitConfigurationService.getInstance(application).schoolDataAccess();
        this.userDataAccess = RetrofitConfigurationService.getInstance(application).userDataAccess();
    }

    public void registerUser(String email, String password, String last_name, String first_name, Date birthday, String option_name, Integer option_school, Integer bloc) {
        HashMap<String, Object> bodyParams = new HashMap<>();
        bodyParams.put("email", email);
        bodyParams.put("password", password);
        bodyParams.put("lastname", last_name);
        bodyParams.put("firstname", first_name);
        bodyParams.put("birthday", birthday);
        bodyParams.put("bloc", bloc);
        bodyParams.put("optionname", option_name);
        bodyParams.put("optionschool", option_school);

        RequestBody requestBody = ApiUtils.ToRequestBody(bodyParams);

        userDataAccess.registerUser(requestBody).enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                if(response.isSuccessful()) {
                    _token.setValue(response.body());
                    _error.setValue(null);
                } else {
                    if(response.code() == 409) {
                        _error.setValue(Errors.EMAIL_EXIST);
                    } else {
                        _error.setValue(Errors.TECHNICAL_ERROR);
                    }
                }
            }

            @Override
            public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                if(t instanceof NoConnectivityException) {
                    _error.setValue(Errors.NO_CONNECTION);
                } else {
                    _error.setValue(Errors.TECHNICAL_ERROR);
                }
            }
        });
    }

    public void loadSchools() {
        schoolDataAccess.getSchools().enqueue(new Callback<SchoolDTO[]>() {
            @Override
            public void onResponse(@NotNull Call<SchoolDTO[]> call, @NonNull Response<SchoolDTO[]> response) {
                if(response.isSuccessful()) {
                    SchoolDTO [] schoolsDTO = response.body();

                    ArrayList<School> schools = new ArrayList<>();
                    for(SchoolDTO schoolDTO : schoolsDTO) {
                        schools.add(SchoolMapper.getInstance().mapToSchool(schoolDTO));
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
        bodyParams.put("schoolId", schoolID);

        RequestBody requestBody = ApiUtils.ToRequestBody(bodyParams);

        schoolDataAccess.getOptions(requestBody).enqueue(new Callback<OptionDTO[]>() {
            @Override
            public void onResponse(@NotNull Call<OptionDTO[]> call, @NotNull Response<OptionDTO[]> response) {
                if(response.isSuccessful()) {
                    OptionDTO [] optionsDTO = response.body();

                    ArrayList<Option> options = new ArrayList<>();
                    for(OptionDTO optionDTO : optionsDTO){
                        options.add(SchoolMapper.getInstance().mapToOption(optionDTO));
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

    public LiveData<Errors> getError() {
        return error;
    }

    public LiveData<String> getToken() {
        return this.token;
    }
}
