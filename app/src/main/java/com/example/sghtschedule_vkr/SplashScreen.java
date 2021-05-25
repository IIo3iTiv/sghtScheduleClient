package com.example.sghtschedule_vkr;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import kotlin.Suppress;

import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        SplashScreen.this.startActivity(intent);
        SplashScreen.this.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
