package com.example.sghtschedule_vkr;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class getRequests {

    OkHttpClient client = new OkHttpClient();
    String data = null;

    public String getTeacherName() {

        String myURL = "http://13.51.203.240/php/get/getTeacherName/";
        Request request = new Request.Builder()
                .url(myURL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                data = response.body().string();
            }
        });

        return data;
    }
}
