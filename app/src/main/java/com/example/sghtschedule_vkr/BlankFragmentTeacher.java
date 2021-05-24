package com.example.sghtschedule_vkr;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

public class BlankFragmentTeacher extends Fragment {

    public static final String APP_PREFERENCES = "ScheduleSettings";
    public static final String KEY_USER = "USER";
    public static final String VALUE_USER = "teacher";
    public static final String KEY_TEACHER_NAME = "TEACHER_NAME";
    public static final String KEY_TEACHER_INDEX = "TEACHER_INDEX";
    public static final String KEY_POSITION = "POSITION";

    String[] listTeacherName, listTeacherNameId;
    Spinner spinner;
    TextView txt;
    OkHttpClient client = new OkHttpClient();
    String data, position, index;
    Handler mHandler;
    SharedPreferences sharedPreferences;
    Button btnApply;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank_teacher, container, false);
        txt = view.findViewById(R.id.tempId);
        spinner = view.findViewById(R.id.spinner);
        mHandler = new Handler(Looper.getMainLooper());
        sharedPreferences = view.getContext().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        btnApply = view.findViewById(R.id.btnApply);

        getTeacherInfo(view);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                data = spinner.getSelectedItem().toString();
                position = Integer.toString(i);
                index = listTeacherNameId[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        btnApply.setOnClickListener(view1 -> {
            if (position.equals("0")) {
                Toast.makeText(view1.getContext(), R.string.select_options, Toast.LENGTH_LONG).show();
            } else {
                saveSettings(position, index, data);
            }
        });

        return view;
    }

    public void getTeacherInfo(View view) {
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

        if (settingsExists(view)) { getSavedSettings(); }
    }

    public void saveSettings(String position, String id, String teacherName) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(KEY_USER, VALUE_USER);
        editor.putString(KEY_POSITION, position);
        editor.putString(KEY_TEACHER_INDEX, id);
        editor.putString(KEY_TEACHER_NAME, teacherName);
        editor.apply();
    }

    public boolean settingsExists(View view) {
        String user;
        boolean ret;
        String packageName = view.getContext().getApplicationInfo().dataDir;
        String settingsPath = packageName + "/shared_prefs/" + APP_PREFERENCES + ".xml";
        File settingsFile = new File(settingsPath);
        user = sharedPreferences.getString(KEY_USER, "");
        ret = settingsFile.exists() && sharedPreferences.contains(KEY_USER) &&  user.equals(VALUE_USER);
        return ret;
    }

    public void getSavedSettings() {
        String saveTeacherName, saveTeacherNameId, savePosition, positionTeacherName, positionTeacherId;
        savePosition = sharedPreferences.getString(KEY_POSITION, "");
        saveTeacherNameId = sharedPreferences.getString(KEY_TEACHER_INDEX, "");
        saveTeacherName = sharedPreferences.getString(KEY_TEACHER_NAME, "");

        positionTeacherName = listTeacherName[Integer.parseInt(savePosition)];
        positionTeacherId = listTeacherNameId[Integer.parseInt(savePosition)];

        if (saveTeacherName.equals(positionTeacherName) && saveTeacherNameId.equals(positionTeacherId))
            spinner.setSelection(Integer.parseInt(savePosition));
    }

}