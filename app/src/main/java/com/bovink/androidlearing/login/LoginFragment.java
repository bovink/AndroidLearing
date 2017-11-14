package com.bovink.androidlearing.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bovink.androidlearing.R;

/**
 * @author fox
 * @since 2017/11/14
 */

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    public LoginFragment(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mPresenter.bindView(this);
    }
}
