package com.bovink.androidlearing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_click)
    void onClick() {
        try {
            HelloClient client = new HelloClient();
            client.connect("59.175.213.77", 30161);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
