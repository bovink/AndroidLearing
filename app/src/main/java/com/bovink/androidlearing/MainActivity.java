package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    User userA;
    User userB;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<String> ids = new ArrayList<>();
        ids.add("dudao_single");

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

        new Thread() {
            @Override
            public void run() {

                try {
                    Socket socket = new Socket("59.175.213.77", 30161);
                    if (socket.isConnected()) {
                        System.out.println("connected");

                        OutputStream outputStream = socket.getOutputStream();
                        String msg = gson.toJson(userA) + "\n";
                        outputStream.write(msg.getBytes("utf-8"));
                        outputStream.flush();
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @OnClick(R.id.btn_login_b)
    void loginB() {
        new Thread() {
            @Override
            public void run() {

                try {
                    Socket socket = new Socket("59.175.213.77", 30161);
                    if (socket.isConnected()) {
                        System.out.println("connected");

                        OutputStream outputStream = socket.getOutputStream();
                        String msg = gson.toJson(userB) + "\n";
                        outputStream.write(msg.getBytes("utf-8"));
                        outputStream.flush();
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
