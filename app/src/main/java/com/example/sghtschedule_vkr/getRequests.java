package com.example.sghtschedule_vkr;

import android.util.Log;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class getRequests {

    private String data;

    public String getTeacherName() {
        OkHttpClient client = new OkHttpClient();
        String url = "http://13.51.203.240/php/get/getTeacherName/";
        data = "";

        Request request = new Request.Builder()
                .url(url)
                .build();

        return data;
    }

}
