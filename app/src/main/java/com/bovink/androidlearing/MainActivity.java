package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewstub)
    ViewStub viewStub;

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_text)
    void showViewStub() {

        if (view == null) {

            view = viewStub.inflate();
        } else {
            if (view.getVisibility() == View.VISIBLE) {

                view.setVisibility(View.INVISIBLE);
            }else {

                view.setVisibility(View.VISIBLE);
            }

        }
    }
}
