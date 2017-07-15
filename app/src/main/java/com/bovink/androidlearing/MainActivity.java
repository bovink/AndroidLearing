package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    User userA;
    User userB;
    Gson gson = new Gson();
    Socket socketa;
    Socket socketb;

    @BindView(R.id.et_text_a)
    EditText et_text_a;

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

        connectA();
        heartbeatA();
    }

    @OnClick(R.id.btn_login_b)
    void loginB() {
        connectB();
        heartbeatB();
    }

    private void connectA() {

        new Thread() {
            @Override
            public void run() {

                try {
                    socketa = new Socket("59.175.213.77", 30161);
                    retryA = false;
                    if (socketa.isConnected()) {
                        System.out.println("connected");

                        OutputStream outputStream = socketa.getOutputStream();
                        outputStream.write(userA.toString().getBytes("utf-8"));
                        outputStream.flush();
                    }
                } catch (SocketException e) {
                    retryA = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void connectB() {
        new Thread() {
            @Override
            public void run() {

                try {
                    socketb = new Socket("59.175.213.77", 30161);
                    retryB = false;
                    if (socketb.isConnected()) {
                        System.out.println("connected");
                        listenB();

                        OutputStream outputStream = socketb.getOutputStream();
                        String msg = gson.toJson(userB) + "\n";
                        outputStream.write(msg.getBytes("utf-8"));
                        outputStream.flush();
                    }
                } catch (SocketException e) {
                    retryB = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private boolean retryA = false;
    private boolean retryB = false;


    private void heartbeatA() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (retryA) {
                        connectA();

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }.start();
    }

    private void heartbeatB() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (retryB) {
                        connectB();

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }.start();
    }

    private void listenB() {

        new Thread() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = socketb.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    while (true) {
                        String s = URLDecoder.decode(bufferedReader.readLine(), "utf-8");
                        System.out.println("s = " + s);
                    }
                } catch (SocketException e) {
                    retryB = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @OnClick(R.id.btn_send_a)
    void sendA() {
        new Thread() {
            @Override
            public void run() {
                MessageSender msg = new MessageSender();
                msg.setGroupId("dudao_single");
                msg.setMessNote(et_text_a.getText().toString());
                msg.setMessType("1");
                msg.setReceiverUid("126");

                try {
                    OutputStream outputStream = socketa.getOutputStream();
                    outputStream.write(msg.toString().getBytes("utf-8"));
                    outputStream.flush();
                } catch (SocketException e) {
                    retryA = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @OnClick(R.id.btn_send_b)
    void sendB() {
        new Thread() {
            @Override
            public void run() {
                try {
                    InputStream inputStream = socketb.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    System.out.println(bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
