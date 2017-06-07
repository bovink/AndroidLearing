package com.bovink.androidlearing.create;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.bovink.androidlearing.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * com.bovink.androidlearing.create
 *
 * @author bovink
 * @since 2017/6/7
 */

public class CreateActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_create)
    void clickBtn(Button button) {
       button.setText("clicked button");

    }
}
