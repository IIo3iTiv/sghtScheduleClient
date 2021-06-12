package com.example.sghtschedule_vkr.Custom;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.sghtschedule_vkr.Fragments.Week.FragmentFriday;
import com.example.sghtschedule_vkr.Fragments.Week.FragmentMonday;
import com.example.sghtschedule_vkr.Fragments.Week.FragmentSaturday;
import com.example.sghtschedule_vkr.Fragments.Week.FragmentThursday;
import com.example.sghtschedule_vkr.Fragments.Week.FragmentTuesday;
import com.example.sghtschedule_vkr.Fragments.Week.FragmentWednesday;

public class SettingsPagerAdapter extends FragmentPagerAdapter {

    int mNimOfTabs;

    public SettingsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNimOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentMonday tabMO = new FragmentMonday();
                return tabMO;
            case 1:
                FragmentTuesday tabTU = new FragmentTuesday();
                return tabTU;
            case 2:
                FragmentWednesday tabWE = new FragmentWednesday();
                return tabWE;
            case 3:
                FragmentThursday tabTH = new FragmentThursday();
                return tabTH;
            case 4:
                FragmentFriday tabFR = new FragmentFriday();
                return tabFR;
            case 5:
                FragmentSaturday tabSA = new FragmentSaturday();
                return tabSA;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNimOfTabs;
    }

}
