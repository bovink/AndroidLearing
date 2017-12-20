package com.bovink.androidlearing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.activity.AudioIdentifyActivity;
import com.bovink.androidlearing.activity.VoiceIdentifyActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_voice_identify)
    void showVoiceIdentify() {

        Intent intent = new Intent(this, VoiceIdentifyActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_audio_identify)
    void showAudioIdentify() {

        Intent intent = new Intent(this, AudioIdentifyActivity.class);
        startActivity(intent);
    }
}
