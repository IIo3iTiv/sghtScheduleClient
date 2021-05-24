package com.example.sghtschedule_vkr;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
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

public class BlankFragmentStudent extends Fragment {

    public static final String APP_PREFERENCES = "ScheduleSettings";
    public static final String KEY_USER = "USER";
    public static final String VALUE_USER = "student";
    public static final String KEY_COURSE_POSITION = "COURSE_POSITION";
    public static final String KEY_COURSE_DATA = "COURSE_DATA";
    public static final String KEY_GROUP_POSITION = "GROUP_POSITION";
    public static final String KEY_GROUP_INDEX = "GROUP_INDEX";
    public static final String KEY_GROUP_DATA = "GROUP_DATA";
    public static final String KEY_SUBGROUP_POSITION = "SUBGROUP_POSITION";
    public static final String KEY_SUBGROUP_DATA = "SUBGROUP_DATA";
    public static final String KEY_FOREIGN_POSITION = "FOREIGN_POSITION";
    public static final String KEY_FOREIGN_DATA = "FOREIGN_DATA";

    String[] listGroupName, listGroupNameId, listCourseStudy, listSubGroup, listEngOrGer;
    String[] course = new String[] {"1", "2", "3", "4"};
    Spinner spinnerGroupName, spinnerCourseStudy, spinnerSubgroup, spinnerEngOrGer;
    CustomAdapter adapterCourseStudy, adapterGroupName, adapterSubgroup, adapterEngOrGer;
    TextView txt;
    OkHttpClient client = new OkHttpClient();
    SharedPreferences sharedPreferences;
    String data;
    String coursePosition = "0", courseData;
    String groupPosition = "0", groupIndex, groupData;
    String subGroupPosition = "0", subGroupData;
    String foreignPosition = "0", foreignData;
    Handler mHandler;
    Button btnApply;
    boolean settingsFileExists;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank_student, container, false);
        txt = view.findViewById(R.id.tempId);
        listSubGroup = getResources().getStringArray(R.array.listSubGroup);
        listEngOrGer = getResources().getStringArray(R.array.listEngOrGer);
        listCourseStudy = getResources().getStringArray(R.array.listCourseStudy);
        mHandler = new Handler(Looper.getMainLooper());
        sharedPreferences = view.getContext().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        btnApply = view.findViewById(R.id.btnApply);

        spinnerGroupName = view.findViewById(R.id.spinnerGroupName);
        spinnerCourseStudy = view.findViewById(R.id.spinnerCourseStudy);
        spinnerSubgroup = view.findViewById(R.id.spinnerSubgroup);
        spinnerEngOrGer = view.findViewById(R.id.spinnerEngOrGer);

        adapterCourseStudy = new CustomAdapter(view.getContext(), listCourseStudy);
        spinnerCourseStudy.setAdapter(adapterCourseStudy);
        adapterSubgroup = new CustomAdapter(view.getContext(), listSubGroup);
        spinnerSubgroup.setAdapter(adapterSubgroup);
        adapterEngOrGer = new CustomAdapter(view.getContext(), listEngOrGer);
        spinnerEngOrGer.setAdapter(adapterEngOrGer);

        settingsFileExists = settingsExists(view);
        if (settingsFileExists) {
            if (getCourseStudy()) {
                getGroupInfo(view, Integer.parseInt(course[Integer.parseInt(coursePosition) - 1]));
            }
        } else {
            spinnerGroupName.setVisibility(View.GONE);
            spinnerSubgroup.setVisibility(View.GONE);
            spinnerEngOrGer.setVisibility(View.GONE);
            spinnerSubgroup.setSelection(0);
            spinnerEngOrGer.setSelection(0);
        }

        spinnerCourseStudy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    getGroupInfo(view, i - 1);
                    coursePosition = Integer.toString(i);
                    courseData = listCourseStudy[i];
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        spinnerGroupName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                groupPosition = Integer.toString(i);
                groupIndex = listGroupNameId[i];
                groupData = listGroupName[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        spinnerSubgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subGroupPosition = Integer.toString(i);
                subGroupData = listSubGroup[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        spinnerEngOrGer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                foreignPosition = Integer.toString(i);
                foreignData = listEngOrGer[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        btnApply.setOnClickListener(view1 -> {
            if (coursePosition.equals("0") || groupPosition.equals("0") || subGroupPosition.equals("0") || foreignPosition.equals("0")) {
                Toast.makeText(view1.getContext(), R.string.select_options, Toast.LENGTH_LONG).show();
            } else {
                saveSettings(coursePosition, courseData, groupPosition, groupIndex, groupData, subGroupPosition, subGroupData, foreignPosition, foreignData);
            }
        });

        return view;
    }

    public void getGroupInfo(View view, Integer params) {

        String myURL = "http://13.51.203.240/php/get/getGroupInfo/?course=" + course[params];
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
            listGroupName = new String[jsonArray.length() + 1];
            listGroupNameId = new String[jsonArray.length() + 1];

            listGroupName[0] = "Группа";
            listGroupNameId[0] = "-1";

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("Id");
                String shortName = jsonObject.getString("shortName");

                listGroupName[i+1] = shortName;
                listGroupNameId[i+1] = id;
            }

            adapterGroupName = new CustomAdapter(view.getContext(), listGroupName);
            spinnerGroupName.setAdapter(adapterGroupName);
            spinnerGroupName.setVisibility(View.VISIBLE);
            spinnerSubgroup.setVisibility(View.VISIBLE);
            spinnerEngOrGer.setVisibility(View.VISIBLE);

            if (settingsFileExists) {
                getSavedSettings();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void saveSettings(String coursePosition, String courseData, String groupPosition, String groupIndex, String groupData, String subGroupPosition, String subGroupData, String foreignPosition, String foreignData) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.putString(KEY_USER, VALUE_USER);
        editor.putString(KEY_COURSE_POSITION, coursePosition);
        editor.putString(KEY_COURSE_DATA, courseData);
        editor.putString(KEY_GROUP_POSITION, groupPosition);
        editor.putString(KEY_GROUP_INDEX, groupIndex);
        editor.putString(KEY_GROUP_DATA, groupData);
        editor.putString(KEY_SUBGROUP_POSITION, subGroupPosition);
        editor.putString(KEY_SUBGROUP_DATA, subGroupData);
        editor.putString(KEY_FOREIGN_POSITION, foreignPosition);
        editor.putString(KEY_FOREIGN_DATA, foreignData);
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
        String saveGroupData, saveGroupIndex, saveSubGroupData, saveForeignData;
        String saveGroupPosition, saveSubGroupPosition, saveForeignPosition;

        saveGroupPosition = sharedPreferences.getString(KEY_GROUP_POSITION, "");
        saveSubGroupPosition = sharedPreferences.getString(KEY_SUBGROUP_POSITION, "");
        saveForeignPosition = sharedPreferences.getString(KEY_FOREIGN_POSITION, "");

        saveGroupData = sharedPreferences.getString(KEY_GROUP_DATA, "");
        saveGroupIndex = sharedPreferences.getString(KEY_GROUP_INDEX, "");
        saveSubGroupData = sharedPreferences.getString(KEY_SUBGROUP_DATA, "");
        saveForeignData = sharedPreferences.getString(KEY_FOREIGN_DATA, "");

        groupData = listGroupName[Integer.parseInt(saveGroupPosition)];
        groupIndex = listGroupNameId[Integer.parseInt(saveGroupPosition)];
        subGroupData = listSubGroup[Integer.parseInt(saveSubGroupPosition)];
        foreignData = listEngOrGer[Integer.parseInt(saveForeignPosition)];

        if (saveGroupData.equals(groupData) && saveGroupIndex.equals(groupIndex) && saveSubGroupData.equals(subGroupData) && saveForeignData.equals(foreignData)) {
            spinnerGroupName.setSelection(Integer.parseInt(saveGroupPosition));
            spinnerSubgroup.setSelection(Integer.parseInt(saveSubGroupPosition));
            spinnerEngOrGer.setSelection(Integer.parseInt(saveForeignPosition));
        }
    }

    public boolean getCourseStudy() {
        String saveCourseData;
        boolean ret;
        coursePosition = sharedPreferences.getString(KEY_COURSE_POSITION, "");
        saveCourseData = sharedPreferences.getString(KEY_COURSE_DATA, "");
        courseData = listCourseStudy[Integer.parseInt(coursePosition)];
        ret = saveCourseData.equals(courseData);
        if (ret)
            spinnerCourseStudy.setSelection(Integer.parseInt(coursePosition));
        return ret;
    }

}