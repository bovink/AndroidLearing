package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirstFragment firstFragment = new FirstFragment();
        SecondFragment secondFragment = new SecondFragment();

        showFragment(firstFragment, R.id.fl_first);
        showFragment(secondFragment, R.id.fl_second);
    }

    private RxBus rxBus = null;

    public RxBus getRxBus() {
        if (rxBus == null) {
            rxBus = new RxBus();
        }
        return rxBus;
    }

    private void showFragment(Fragment fragment, int layoutId) {

        getSupportFragmentManager()
                .beginTransaction()
                .add(layoutId, fragment)
                .commit();
    }

    public static class TapEvent {

    }
}
