package com.example.sghtschedule_vkr;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BlankFragmentTeacher extends Fragment {

    String[] listTeacherName, listTeacherNameId;
    Spinner spinner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank_teacher, container, false);

        getRequests requests = new getRequests();
        String response = requests.getTeacherName();

        try {
            JSONArray jsonArray = new JSONArray(response);
            listTeacherName = new String[jsonArray.length()];
            listTeacherNameId = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.getString("Id");
                String name = jsonObject.getString("compName");

                listTeacherName[i] = name;
                listTeacherNameId[i] = id;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listTeacherName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) view.findViewById(R.id.listTeacherName);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Заголовок");

        return view;
    }
}