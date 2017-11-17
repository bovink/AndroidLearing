package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.gen.UserDao;
import com.bovink.androidlearing.orm.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        UserDao userDao = GreenDaoManager.getInstance(getApplicationContext()).getNewSession().getUserDao();

//        User user = new User(null, "jane", "13");
//        userDao.insert(user);
        List<User> userList = userDao.loadAll();
        for (User user: userList) {
            System.out.println("user.getId() = " + user.getId());
            System.out.println("user.getName() = " + user.getName());
            System.out.println("user.getAge() = " + user.getAge());
        }
    }
}
