package be.leeroy.studentapp.services;

import android.content.Context;

import com.squareup.moshi.Moshi;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;

import be.leeroy.studentapp.dataaccess.LoginDataAccess;
import be.leeroy.studentapp.dataaccess.PublicationDataAccess;
import be.leeroy.studentapp.dataaccess.SchoolDataAccess;
import be.leeroy.studentapp.dataaccess.UserDataAccess;
import be.leeroy.studentapp.utils.ConnectivityCheckInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitConfigurationService {
    private static final String BASE_URL = "https://neat-moth-88.loca.lt/";

    // DataAccess
    private UserDataAccess userDataAccess = null;
    private LoginDataAccess loginDataAccess = null;
    private PublicationDataAccess publicationDataAccess = null;
    private SchoolDataAccess schoolDataAccess = null;

    // Retrofit client creation
    private Retrofit retrofitClient;

    private RetrofitConfigurationService(Context context) {
        initializeRetrofit(context);
    }

    private void initializeRetrofit(Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ConnectivityCheckInterceptor(context))
                .build();

        Moshi moshiConverter = new Moshi.Builder()
                .add(new KotlinJsonAdapterFactory())
                .build();

        this.retrofitClient = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshiConverter))
                .baseUrl(BASE_URL)
                .build();
    }

    public static RetrofitConfigurationService getInstance(Context context) {
        return new RetrofitConfigurationService(context);
    }

    public UserDataAccess userDataAccess(){
        if (userDataAccess == null){
            userDataAccess = retrofitClient.create(UserDataAccess.class);
        }

        return userDataAccess;
    }

    public LoginDataAccess loginDataAccess(){
        if(loginDataAccess == null){
            loginDataAccess = retrofitClient.create(LoginDataAccess.class);
        }

        return loginDataAccess;
    }

    public PublicationDataAccess publicationDataAccess(){
        if(publicationDataAccess == null){
            publicationDataAccess = retrofitClient.create(PublicationDataAccess.class);
        }

        return publicationDataAccess;
    }

    public SchoolDataAccess schoolDataAccess() {
        if(schoolDataAccess == null) {
            schoolDataAccess = retrofitClient.create(SchoolDataAccess.class);
        }

        return schoolDataAccess;
    }
}
