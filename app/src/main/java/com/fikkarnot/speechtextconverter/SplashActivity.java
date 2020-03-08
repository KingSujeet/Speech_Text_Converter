package com.fikkarnot.speechtextconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

    private final android.os.Handler waitHandler = new android.os.Handler();
    private  final Runnable waitCallback = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();

        waitHandler.postDelayed(waitCallback,2000);

    }

    @Override
    protected void onDestroy() {

        waitHandler.removeCallbacks(waitCallback);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

    }
}
