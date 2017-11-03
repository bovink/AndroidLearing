package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    FirstFragment firstFragment;
    SecondFragment secondFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        firstFragment = new FirstFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_main, firstFragment)
                .show(firstFragment)
                .commit();

    }

    @OnClick(R.id.btn_one)
    void showOne() {

        getSupportFragmentManager().beginTransaction()
                .show(firstFragment)
                .commit();
    }

    @OnClick(R.id.btn_two)
    void showTwo() {
        if (secondFragment == null) {
            secondFragment = new SecondFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl_main, secondFragment)
                    .commit();
        }

        getSupportFragmentManager().beginTransaction()
                .show(secondFragment)
                .commit();
    }
}
