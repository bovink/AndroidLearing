package com.bovink.androidlearing.mvc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.R;

/**
 * com.bovink.androidlearing.mvc
 *
 * @author bovink
 * @since 2017/11/15
 */

public class LoginMvcActivity extends AppCompatActivity {

    private LoginMvcFragment loginMvcFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
