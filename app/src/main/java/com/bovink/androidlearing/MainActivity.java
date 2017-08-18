package com.bovink.androidlearing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_picture)
    void picture() {
        Intent intent = new Intent(this, PictureActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_video)
    void video() {
        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }

}
