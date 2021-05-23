package com.example.sghtschedule_vkr;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BlankFragmentStudent extends Fragment {

    String[] listGroupName, listGroupNameId, listCourseStudy, listSubGroup, listEngOrGer;
    Spinner spinnerGroupName, spinnerCourseStudy, spinnerSubgroup, spinnerEngOrGer;
    CustomAdapter adapterCourseStudy, adapterGroupName, adapterSubgroup, adapterEngOrGer;
    TextView txt;
    OkHttpClient client = new OkHttpClient();
    String data;
    Handler mHandler;
    Space spaceOne, spaceTwo, spaceThree, spaceFour, spaceFive;
    Button btnApply;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank_student, container, false);
        txt = view.findViewById(R.id.tempId);
        listSubGroup = getResources().getStringArray(R.array.listSubGroup);
        listEngOrGer = getResources().getStringArray(R.array.listEngOrGer);
        listCourseStudy = getResources().getStringArray(R.array.listCourseStudy);
        spaceOne = view.findViewById(R.id.studentSpaceOne);
        spaceTwo = view.findViewById(R.id.studentSpaceTwo);
        spaceThree = view.findViewById(R.id.studentSpaceThree);
        spaceFour = view.findViewById(R.id.studentSpaceFour);
        spaceFive = view.findViewById(R.id.studentSpaceFive);
        mHandler = new Handler(Looper.getMainLooper());
        btnApply = view.findViewById(R.id.btnApply);

        spaceThree.setVisibility(View.GONE);
        spaceFour.setVisibility(View.GONE);
        spaceFive.setVisibility(View.GONE);

        spinnerGroupName = view.findViewById(R.id.spinnerGroupName);
        spinnerGroupName.setVisibility(View.GONE);

        spinnerCourseStudy = view.findViewById(R.id.spinnerCourseStudy);
        adapterCourseStudy = new CustomAdapter(view.getContext(), listCourseStudy);
        spinnerCourseStudy.setAdapter(adapterCourseStudy);

        spinnerSubgroup = view.findViewById(R.id.spinnerSubgroup);
        adapterSubgroup = new CustomAdapter(view.getContext(), listSubGroup);
        spinnerSubgroup.setAdapter(adapterSubgroup);
        spinnerSubgroup.setVisibility(View.GONE);

        spinnerEngOrGer = view.findViewById(R.id.spinnerEngOrGer);
        adapterEngOrGer = new CustomAdapter(view.getContext(), listEngOrGer);
        spinnerEngOrGer.setAdapter(adapterEngOrGer);
        spinnerEngOrGer.setVisibility(View.GONE);

        spinnerCourseStudy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    getGroupInfo(view, i - 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnApply.setOnClickListener(this::Apply);

        return view;
    }

    public void getGroupInfo(View view, Integer params) {
        String[] course = new String[] {"1", "2", "3", "4"};

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
            spaceThree.setVisibility(View.VISIBLE);

            spinnerSubgroup.setVisibility(View.VISIBLE);
            spaceFour.setVisibility(View.VISIBLE);

            spinnerEngOrGer.setVisibility(View.VISIBLE);
            spaceFive.setVisibility(View.VISIBLE);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void Apply(View view) {

    }

}