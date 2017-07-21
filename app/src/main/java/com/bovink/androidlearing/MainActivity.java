package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearning.gen.UserDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserDao userDao = GreenDaoManager.getInstance().getNewSession().getUserDao();

        User user = new User(null, "lilei", 16);
        userDao.insert(user);

    }
}
