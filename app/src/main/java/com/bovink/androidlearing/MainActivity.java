package com.bovink.androidlearing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.activity.UtilityActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_main)
    void showCreateActivity() {
        Intent intent = new Intent(this, UtilityActivity.class);
        startActivity(intent);
    }
}
