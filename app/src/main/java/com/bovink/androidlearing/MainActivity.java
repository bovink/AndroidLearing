package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.gen.DaoMaster;
import com.bovink.androidlearing.gen.DaoSession;
import com.bovink.androidlearing.gen.UserDao;
import com.bovink.androidlearing.orm.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DaoMaster.DevOpenHelper helper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DaoMaster.DevOpenHelper(this, "user.db", null);
        daoMaster = new DaoMaster(helper.getEncryptedWritableDb("123"));
        daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();




        querySome();

    }

    private void querySome() {
        List<User> userList = userDao.queryBuilder()
                .where(UserDao.Properties.Name.eq("lilei"))
                .list();
        for (User user : userList) {
            System.out.println("user.getId() = " + user.getId());
            System.out.println("user.getName() = " + user.getName());
            System.out.println("user.getAge() = " + user.getAge());

        }
    }

    private void load(long id) {
        User user = userDao.load(id);

        System.out.println("user.getId() = " + user.getId());
        System.out.println("user.getName() = " + user.getName());
        System.out.println("user.getAge() = " + user.getAge());
    }

    private void insert() {
        User user = new User(null, "lilei", "23");
        userDao.insert(user);
    }

    private void showAll() {
        List<User> userList = userDao.loadAll();
        for (User user : userList) {
            System.out.println("user.getId() = " + user.getId());
            System.out.println("user.getName() = " + user.getName());
            System.out.println("user.getAge() = " + user.getAge());

        }
    }
}
