package com.bovink.androidlearing.login;

/**
 * @author fox
 * @since 2017/11/14
 */

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    @Override
    public void bindView(LoginContract.View view) {

        mView = view;
    }
}
