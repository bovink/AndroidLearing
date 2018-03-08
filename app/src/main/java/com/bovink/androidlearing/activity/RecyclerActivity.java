package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.databinding.RecyclerBind;

/**
 * @author fox
 * @since 2018/03/08
 */

public class RecyclerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RecyclerBind bind = RecyclerBind.inflate(getLayoutInflater());
        bind.setView(this);
        setContentView(bind.getRoot());
    }
}
