package com.bovink.androidlearing.login;

import android.os.Handler;
import android.os.Message;

/**
 * @author fox
 * @since 2017/11/14
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    /**
     * 模拟网络请求
     */
    private Handler fakeHttpHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            String[] userInfo = ((String) msg.obj).split("@");
            String username = userInfo[0];
            String password = userInfo[1];

            mView.hideLoading();
            if (username.equals("lilei") && password.equals("123456")) {

                mView.showToast("登录成功");
                mView.showMainActivity();
            } else {

                mView.showToast("登录失败");
            }
        }
    };

    @Override
    public void bindView(LoginContract.View view) {

        mView = view;
    }

    @Override
    public void login(String username, String password) {
        Message msg = new Message();
        msg.obj = username + "@" + password;
        fakeHttpHandler.sendMessageDelayed(msg, 3000);
    }
}
