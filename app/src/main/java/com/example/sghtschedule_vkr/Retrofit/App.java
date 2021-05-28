package com.example.sghtschedule_vkr.Retrofit;

import android.app.Application;

import com.example.sghtschedule_vkr.Retrofit.ServerAPI;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static final String BASE_URL = "http://13.51.203.240/";
    private static ServerAPI server;

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        server = retrofit.create(ServerAPI.class);
    }

    public static ServerAPI getApi() {
        return server;
    }
}
