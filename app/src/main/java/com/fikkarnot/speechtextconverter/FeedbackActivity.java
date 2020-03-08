package com.fikkarnot.speechtextconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class FeedbackActivity extends AppCompatActivity {

    EditText regarding,message;
    Button send;
    final String email="fikkarknot@gmail.com";
    private static final String TAG = "MainActivity";

    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        regarding = findViewById(R.id.editText);
        message = findViewById(R.id.editText2);
        send = findViewById(R.id.button);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String regard=regarding.getText().toString();
                String meg=message.getText().toString();

                Intent email1 = new Intent(Intent.ACTION_SEND);
                email1.putExtra(Intent.EXTRA_EMAIL,new String[]{email});
                email1.putExtra(Intent.EXTRA_SUBJECT,regard);
                email1.putExtra(Intent.EXTRA_TEXT,meg);
                email1.setType("message/rfc822");
                startActivity(Intent.createChooser(email1,"Choose an Email client"));
            }
        });
    }
}
