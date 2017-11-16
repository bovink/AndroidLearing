package com.bovink.androidlearing.mvc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bovink.androidlearing.R;

/**
 * com.bovink.androidlearing.mvc
 *
 * @author bovink
 * @since 2017/11/15
 */

public class LoginMvcActivity extends AppCompatActivity {

    private TextView backActivityTextView;
    private TextView activityTitleTextView;

    private LoginMvcFragment loginMvcFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        backActivityTextView = (TextView) findViewById(R.id.tv_back_activity);
        backActivityTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        activityTitleTextView = (TextView) findViewById(R.id.tv_activity_title);
        activityTitleTextView.setText("登录");

        loginMvcFragment = new LoginMvcFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_login_content, loginMvcFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (!loginMvcFragment.interceptBackPressed()) {
            super.onBackPressed();
        }
    }
}
