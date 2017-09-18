package com.bovink.androidlearing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.fruitlist.FruitListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_mvp)
    void showMvpActivity() {
        Intent intent = new Intent(this, FruitListActivity.class);
        startActivity(intent);
    }
}
