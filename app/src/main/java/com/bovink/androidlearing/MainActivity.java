package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearning.gen.UserDao;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserDao userDao = GreenDaoManager.getInstance().getNewSession().getUserDao();


        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance(this, "setting");
        sharedPreferencesUtils
                .put("one", "one")
                .put("sex", "male")
                .put("age", 16);

        Map<String, ?> map =  sharedPreferencesUtils.getAll();

        System.out.println("map.get(\"one\") = " + map.get("one"));
        System.out.println("map.get(\"age\") = " + map.get("age"));

    }
}
