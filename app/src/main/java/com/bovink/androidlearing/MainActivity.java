package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.gen.DaoMaster;
import com.bovink.androidlearing.gen.DaoSession;
import com.bovink.androidlearing.gen.UserDao;
import com.bovink.androidlearing.orm.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "user.db", null);
        DaoMaster daoMaster = new DaoMaster(helper.getEncryptedWritableDb("123"));
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();

        User extraUser = new User(null, "jj", "44");
        userDao.insert(extraUser);

        List<User> userList = userDao.loadAll();
        for (User user: userList) {
            System.out.println("user.getId() = " + user.getId());
            System.out.println("user.getName() = " + user.getName());
            System.out.println("user.getAge() = " + user.getAge());

        }

    }
}
