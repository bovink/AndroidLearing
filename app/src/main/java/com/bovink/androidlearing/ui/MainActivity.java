package com.bovink.androidlearing.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.R;
import com.bovink.androidlearing.model.Person;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject
    Person lilei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lilei.print();
    }
}
