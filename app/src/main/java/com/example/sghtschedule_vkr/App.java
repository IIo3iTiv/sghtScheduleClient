package com.example.sghtschedule_vkr;

import android.app.Application;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static final String BASE_URL = "http://13.51.203.240/";
    private static ServerAPI server;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        server = retrofit.create(ServerAPI.class);
    }

    public static ServerAPI getApi() {
        return server;
    }
}
