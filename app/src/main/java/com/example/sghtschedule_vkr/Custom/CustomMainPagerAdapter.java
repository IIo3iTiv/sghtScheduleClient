package com.example.sghtschedule_vkr.Custom;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.sghtschedule_vkr.Fragments.Settings.FragmentStudent;
import com.example.sghtschedule_vkr.Fragments.Settings.FragmentTeacher;
import com.example.sghtschedule_vkr.Fragments.Week.FragmentFriday;
import com.example.sghtschedule_vkr.Fragments.Week.FragmentMonday;
import com.example.sghtschedule_vkr.Fragments.Week.FragmentSaturday;
import com.example.sghtschedule_vkr.Fragments.Week.FragmentThursday;
import com.example.sghtschedule_vkr.Fragments.Week.FragmentTuesday;
import com.example.sghtschedule_vkr.Fragments.Week.FragmentWednesday;

public class CustomMainPagerAdapter extends FragmentPagerAdapter {

    int mNimOfTabs;

    public CustomMainPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNimOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentStudent tabStudent = new FragmentStudent();
                return tabStudent;
            case 1:
                FragmentTeacher tabTeacher = new FragmentTeacher();
                return tabTeacher;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNimOfTabs;
    }

}