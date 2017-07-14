package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    User userA;
    User userB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<String> ids = new ArrayList<>();
        ids.add("jhoa_single");

        userA = new User();
        userA.setAppId("dudao");
        userA.setGroupIds(ids);
        userA.setUserName("hetianjiao");
        userA.setUserUid("117");


        userB = new User();
        userB.setAppId("dudao");
        userB.setGroupIds(ids);
        userB.setUserName("fox");
        userB.setUserUid("126");
    }

    @OnClick(R.id.btn_login_a)
    void loginA() {
        try {
            HelloClient client = new HelloClient(userA);
            client.connect("59.175.213.77", 30161);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_login_b)
    void loginB() {
        try {
            HelloClient client = new HelloClient(userB);
            client.connect("59.175.213.77", 30161);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
