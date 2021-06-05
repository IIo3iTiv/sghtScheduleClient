package com.example.sghtschedule_vkr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.sghtschedule_vkr.Custom.ViewPagerAdapter;
import com.example.sghtschedule_vkr.Custom.MainPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class SettingsActivity extends AppCompatActivity {

    Window window;
    Toolbar toolbar;
    TextView titleToolbar;
    TabLayout tabLayout;
    ViewPagerAdapter viewPager;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        window = getWindow();
        window.setBackgroundDrawableResource(R.drawable.linear_gradient);

        setContentView(R.layout.activity_settings);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            titleToolbar = findViewById(R.id.toolbarTitle);
            titleToolbar.setText(getString(R.string.settings));
            toolbar.setNavigationIcon(R.drawable.outline_arrow_back_white_36);
            toolbar.setNavigationOnClickListener(view -> onBackPressed());
        }

        viewPager = findViewById(R.id.simpleViewPager);
        tabLayout = findViewById(R.id.simpleTabLayout);

        TabLayout.Tab tabStudent = tabLayout.newTab();
        tabStudent.setIcon(R.drawable.outline_person_outline_white_48);
        tabLayout.addTab(tabStudent);

        TabLayout.Tab tabTeacher = tabLayout.newTab();
        tabTeacher.setIcon(R.drawable.outline_school_white_48);
        tabLayout.addTab(tabTeacher);

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.disableScroll(true);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        viewPager.setOnTouchListener((view, motionEvent) -> true);

    }
}
