package com.example.sghtschedule_vkr;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BlankFragmentTeacher extends Fragment {

    String[] listTeacherName, listTeacherNameId;
    Spinner spinner;
    TextView txt;
    OkHttpClient client = new OkHttpClient();
    String data;
    Handler mHandler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank_teacher, container, false);
        txt = view.findViewById(R.id.tempId);
        spinner = view.findViewById(R.id.spinner);
        mHandler = new Handler(Looper.getMainLooper());


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
                data = Objects.requireNonNull(response.body()).string();
                mHandler.post(() -> postRequest(view, data));
            }
        });

        return view;
    }

    public void postRequest(View view, String params) {
        try {
            JSONArray jsonArray = new JSONArray(params);
            listTeacherName = new String[jsonArray.length() + 1];
            listTeacherNameId = new String[jsonArray.length() + 1];

            listTeacherName[0] = "Фамилия И.О.";
            listTeacherNameId[0] = "-1";

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("Id");
                String name = jsonObject.getString("compName");

                listTeacherName[i+1] = name;
                listTeacherNameId[i+1] = id;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        CustomAdapter adapter = new CustomAdapter(view.getContext(), listTeacherName);
        spinner.setAdapter(adapter);
    }

}