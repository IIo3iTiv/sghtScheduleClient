package com.example.sghtschedule_vkr.Fragments.Settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.sghtschedule_vkr.MainActivity;
import com.example.sghtschedule_vkr.Retrofit.App;
import com.example.sghtschedule_vkr.Custom.SpinnerAdapter;
import com.example.sghtschedule_vkr.POJO.DatumTeacher;
import com.example.sghtschedule_vkr.POJO.Teacher;
import com.example.sghtschedule_vkr.R;
import java.io.File;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static android.content.Context.MODE_PRIVATE;

public class FragmentTeacher extends Fragment {

    public static final String APP_PREFERENCES = "ScheduleSettings";
    public static final String KEY_USER = "USER";
    public static final String VALUE_USER = "teacher";
    public static final String KEY_TEACHER_NAME = "TEACHER_NAME";
    public static final String KEY_TEACHER_INDEX = "TEACHER_INDEX";
    public static final String KEY_POSITION = "POSITION";

    Button btnApply;
    SharedPreferences sharedPreferences;
    TextView txt;
    Spinner spinner;
    String[] listTeacherName = {"Фамилия И.О."}, listTeacherId = {"-1"};
    String position, data, index;
    View view;
    MainActivity main;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_teacher, container, false);
        txt = view.findViewById(R.id.tempId);
        spinner = view.findViewById(R.id.spinner);
        sharedPreferences = view.getContext().getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        btnApply = view.findViewById(R.id.btnApply);
        getTeacherInfo();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                data = spinner.getSelectedItem().toString();
                position = Integer.toString(i);
                index = listTeacherId[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        btnApply.setOnClickListener(view1 -> {
            if (position.equals("0")) { Toast.makeText(view1.getContext(), R.string.select_options, Toast.LENGTH_LONG).show(); }
            else {
                saveSettings(position, index, data);
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        return view;
    }

    public void getTeacherInfo() {
        App.getApi().getTeacherName().enqueue(new Callback<Teacher>() {
            @Override
            public void onResponse(@NonNull Call<Teacher> call, @NonNull Response<Teacher> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    List<DatumTeacher> listDatum = response.body().getData();
                    listTeacherName = new String[listDatum.size() + 1];
                    listTeacherId = new String[listDatum.size() + 1];
                    listTeacherName[0] = "Фамилия И.О.";
                    listTeacherId[0] = "-1";

                    for (int i = 0; i < listDatum.size(); i++) {
                        listTeacherName[i+1] = listDatum.get(i).getCompName();
                        listTeacherId[i+1] = listDatum.get(i).getId();
                    }

                    SpinnerAdapter adapter = new SpinnerAdapter(view.getContext(), listTeacherName);
                    spinner.setAdapter(adapter);

                    if (settingsExists()) { getSavedSettings(); }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Teacher> call, @NonNull Throwable t) {
                Toast.makeText(view.getContext(), R.string.errorMessage, Toast.LENGTH_LONG).show();
                listTeacherName = new String[1];
                listTeacherId = new String[1];
                listTeacherName[0] = view.getContext().getResources().getString(R.string.teacherName);
                listTeacherId[0] = view.getContext().getResources().getString(R.string.teacherIndex);
                SpinnerAdapter adapter = new SpinnerAdapter(view.getContext(), listTeacherName);
                spinner.setAdapter(adapter);
            }
        });
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

    public boolean settingsExists() {
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
        positionTeacherId = listTeacherId[Integer.parseInt(savePosition)];

        if (saveTeacherName.equals(positionTeacherName) && saveTeacherNameId.equals(positionTeacherId))
            spinner.setSelection(Integer.parseInt(savePosition));
    }
}