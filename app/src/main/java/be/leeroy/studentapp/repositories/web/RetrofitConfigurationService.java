package be.leeroy.studentapp.repositories.web;

import android.content.Context;

import com.squareup.moshi.Moshi;
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory;

import be.leeroy.studentapp.utils.ConnectivityCheckInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitConfigurationService {
    private static final String BASE_URL = "http://192.168.0.10:3001/";

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
}
