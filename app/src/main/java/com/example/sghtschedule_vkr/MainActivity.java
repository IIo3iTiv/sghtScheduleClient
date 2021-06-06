package com.example.sghtschedule_vkr;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import com.example.sghtschedule_vkr.Custom.SettingsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.setBackgroundDrawableResource(R.drawable.linear_gradient);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        viewPager = findViewById(R.id.simpleViewPager);
        tabLayout = findViewById(R.id.simpleTabLayout);

        TabLayout.Tab monday = tabLayout.newTab();
        monday.setText(getApplicationContext().getResources().getString(R.string.monday));
        tabLayout.addTab(monday);

        TabLayout.Tab tuesday = tabLayout.newTab();
        tuesday.setText(getApplicationContext().getResources().getString(R.string.tuesday));
        tabLayout.addTab(tuesday);

        TabLayout.Tab wednesday = tabLayout.newTab();
        wednesday.setText(getApplicationContext().getResources().getString(R.string.wednesday));
        tabLayout.addTab(wednesday);

        TabLayout.Tab thursday = tabLayout.newTab();
        thursday.setText(getApplicationContext().getResources().getString(R.string.thursday));
        tabLayout.addTab(thursday);

        TabLayout.Tab friday = tabLayout.newTab();
        friday.setText(getApplicationContext().getResources().getString(R.string.friday));
        tabLayout.addTab(friday);

        TabLayout.Tab saturday = tabLayout.newTab();
        saturday.setText(getApplicationContext().getResources().getString(R.string.saturday));
        tabLayout.addTab(saturday);

        SettingsPagerAdapter adapter = new SettingsPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            finish();

        }
        return true;
    }



}