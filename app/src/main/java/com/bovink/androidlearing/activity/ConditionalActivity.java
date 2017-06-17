package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.R;

import butterknife.OnClick;

/**
 * com.bovink.androidlearing.activity
 *
 * @author bovink
 * @since 2017/6/17
 */

public class ConditionalActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditional);
    }

    @OnClick(R.id.btn_conditional)
    void clickConditional() {

    }
}
