package com.example.sghtschedule_vkr.Fragments.Week;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.sghtschedule_vkr.R;

public class FragmentMonday extends Fragment {

    View view;
    Spinner spinner;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_monday, container, false);
        spinner = view.findViewById(R.id.spinner);
        return view;
    }

}
