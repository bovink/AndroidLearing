package com.bovink.androidlearing.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.bovink.androidlearing.R;

/**
 * @author fox
 * @since 2017/11/14
 */

public class LoginActivity extends AppCompatActivity {

    private TextView backActivityTextView;
    private TextView activityTitleTextView;

    private LoginFragment loginFragment;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter();

        loginFragment = LoginFragment.newInstance(loginPresenter);

        backActivityTextView = (TextView) findViewById(R.id.tv_back_activity);
        backActivityTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        activityTitleTextView = (TextView) findViewById(R.id.tv_activity_title);
        activityTitleTextView.setText("登录");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_login_content, loginFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (!loginFragment.interceptBackPressed()) {
            super.onBackPressed();
        }
    }
}
