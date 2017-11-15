package com.bovink.androidlearing.mvc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bovink.androidlearing.MainActivity;
import com.bovink.androidlearing.R;

/**
 * com.bovink.androidlearing.mvc
 *
 * @author bovink
 * @since 2017/11/15
 */

public class LoginMvcFragment extends Fragment {

    private Context mContext;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private ProgressBar loginingProgressBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        Button button = (Button) root.findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginingProgressBar.setVisibility(View.VISIBLE);

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                Message msg = new Message();
                msg.obj = username + "@" + password;
                fakeHttpHandler.sendMessageDelayed(msg, 3000);
            }
        });

        usernameEditText = (EditText) root.findViewById(R.id.et_username);
        passwordEditText = (EditText) root.findViewById(R.id.et_password);
        loginingProgressBar = (ProgressBar) root.findViewById(R.id.pb_login_ing);
        return root;
    }

    public boolean interceptBackPressed() {
        // 如果正在显示ProgressBar则拦截返回按键事件，并隐藏ProgressBar
        if (loginingProgressBar.getVisibility() == View.VISIBLE) {
            loginingProgressBar.setVisibility(View.GONE);
            return true;
        }
        return false;
    }


    /**
     * 模拟网络请求
     */
    private Handler fakeHttpHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            String[] userInfo = ((String) msg.obj).split("@");
            String username = userInfo[0];
            String password = userInfo[1];

            loginingProgressBar.setVisibility(View.GONE);
            if (username.equals("lilei") && password.equals("123456")) {

                Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
            } else {

                Toast.makeText(mContext, "登录失败", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
