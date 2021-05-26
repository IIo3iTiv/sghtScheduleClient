package com.example.sghtschedule_vkr;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

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
