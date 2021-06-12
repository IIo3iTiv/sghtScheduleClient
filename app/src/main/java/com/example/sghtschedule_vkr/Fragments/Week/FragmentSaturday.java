package com.example.sghtschedule_vkr.Fragments.Week;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sghtschedule_vkr.Custom.RecyclerViewAdapter;
import com.example.sghtschedule_vkr.POJO.DatumPair;
import com.example.sghtschedule_vkr.POJO.Pair;
import com.example.sghtschedule_vkr.R;
import com.example.sghtschedule_vkr.Retrofit.App;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class FragmentSaturday extends Fragment {

    public static final String APP_PREFERENCES = "ScheduleSettings";
    public static final String KEY_USER = "USER";
    public static final String VALUE_USER_STUDENT = "student";
    public static final String VALUE_USER_TEACHER = "teacher";

    public static final String KEY_GROUP_INDEX = "GROUP_INDEX";
    public static final String KEY_SUBGROUP_INDEX = "SUBGROUP_INDEX";
    public static final String KEY_FOREIGN_INDEX = "FOREIGN_INDEX";

    public static final String KEY_TEACHER_INDEX = "TEACHER_INDEX";

    View view;
    SharedPreferences sharedPreferences;
    String user, packageName, settingsPath, date, teacherIndex, groupIndex, subGroupIndex, foreignIndex;
    File settingsFile;
    RecyclerView recyclerView;
    LinearLayout settingsMissing, scheduleMissing;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weekday, container, false);
        settingsMissing = view.findViewById(R.id.settings_missing);
        scheduleMissing = view.findViewById(R.id.schedule_missing);
        recyclerView = view.findViewById(R.id.recyclerView);
        settingsMissing.setVisibility(View.GONE);
        scheduleMissing.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);

        sharedPreferences = view.getContext().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        packageName = view.getContext().getApplicationInfo().dataDir;
        settingsPath = packageName + "/shared_prefs/" + APP_PREFERENCES + ".xml";
        settingsFile = new File(settingsPath);
        if (settingsFile.exists()) {
            user = sharedPreferences.getString(KEY_USER, "");
            date = getDate();

            switch (user) {
                case VALUE_USER_STUDENT:
                    groupIndex = sharedPreferences.getString(KEY_GROUP_INDEX, "");
                    subGroupIndex = sharedPreferences.getString(KEY_SUBGROUP_INDEX, "");
                    foreignIndex = sharedPreferences.getString(KEY_FOREIGN_INDEX, "");
                    getStudentPair(date, groupIndex, subGroupIndex, foreignIndex);
                    break;

                case VALUE_USER_TEACHER:
                    teacherIndex = sharedPreferences.getString(KEY_TEACHER_INDEX, "");
                    getTeacherPair(date, teacherIndex);
                    break;
            }

            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
        } else {
            settingsMissing.setVisibility(View.VISIBLE);
        }


        return view;
    }

    public void getStudentPair(String date, String group, String subGroup, String foreign) {
        App.getApi().getStudentPair(date, group, subGroup, foreign).enqueue(new Callback<Pair>() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onResponse(@NonNull Call<Pair> call, @NonNull Response<Pair> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<DatumPair> listDatum = response.body().getData();
                    if (listDatum.isEmpty()) {
                        scheduleMissing.setVisibility(View.VISIBLE);
                    } else {
                        recyclerView.setVisibility(View.VISIBLE);
                        writeRecycler(view.getContext(), listDatum, "student");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Pair> call, @NonNull Throwable t) {
                Toast.makeText(view.getContext(), R.string.errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getTeacherPair(String date, String teacherIndex) {
        App.getApi().getTeacherPair(date, teacherIndex).enqueue(new Callback<Pair>() {
            @Override
            public void onResponse(@NonNull Call<Pair> call, @NonNull Response<Pair> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<DatumPair> listDatum = response.body().getData();
                    if (listDatum.isEmpty()) {
                        scheduleMissing.setVisibility(View.VISIBLE);
                    } else {
                        recyclerView.setVisibility(View.VISIBLE);
                        writeRecycler(view.getContext(), listDatum, "teacher");
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Pair> call, @NonNull Throwable t) {
                Toast.makeText(view.getContext(), R.string.errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("SimpleDateFormat")
    public String getDate() {
        String dateStr;
        dateStr = getResources().getString(R.string.dateSaturday);
        if (dateStr.equals("0")) {
            Calendar now = Calendar.getInstance();
            now.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            Date date = now.getTime();
            dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
        }

        return dateStr;
    }

    public void writeRecycler(Context context, List<DatumPair> list, String user) {
        RecyclerViewAdapter recycler = new RecyclerViewAdapter(context, list, user);
        recyclerView.setAdapter(recycler);
    }

}