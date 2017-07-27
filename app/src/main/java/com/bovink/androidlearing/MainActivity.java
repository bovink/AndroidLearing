package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.bovink.androidlearing.utils.InputUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_show)
    Button showButton;
    @BindView(R.id.btn_hide)
    Button hideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_show)
    void show() {
        InputUtils.showSoftInput(this);
    }

    @OnClick(R.id.btn_hide)
    void hide() {
        InputUtils.hideSoftInput(this, showButton);
    }
}

