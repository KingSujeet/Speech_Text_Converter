package com.fikkarnot.speechtextconverter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Locale;

public class TextToSpeechActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private Button btnSubmit;
    private EditText inputText;
    private static final String TAG = "MainActivity";

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        tts = new TextToSpeech(this,this);

        btnSubmit = findViewById(R.id.button2);

        inputText = findViewById(R.id.editText3);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                voiceOutput();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void voiceOutput() {

        CharSequence text = inputText.getText();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH,null,"id1");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onInit(int status) {

        if (status== TextToSpeech.SUCCESS){
            int result = tts.setLanguage(Locale.getDefault());
            float i=50;

            if (result== TextToSpeech.LANG_MISSING_DATA || result==TextToSpeech.LANG_NOT_SUPPORTED){

                Toast.makeText(getApplicationContext(),"language not supported",Toast.LENGTH_SHORT).show();
            }
            else{

                btnSubmit.setEnabled(true);
                voiceOutput();
            }

        } else{

            Toast.makeText(getApplicationContext(),"Initialization failed", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onDestroy() {

        if (tts!=null){

            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
