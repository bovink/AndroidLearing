package com.bovink.androidlearing.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.bovink.androidlearing.MainActivity;
import com.bovink.androidlearing.R;

/**
 * @author fox
 * @since 2017/11/14
 */

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;
    private Context mContext;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private ProgressBar loginingProgressBar;

    public static LoginFragment newInstance(LoginContract.Presenter presenter){
        LoginFragment fragment = new LoginFragment();
        fragment.mPresenter = presenter;

        return fragment;
    }

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

                showLoading();

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                mPresenter.login(username, password);
            }
        });

        usernameEditText = (EditText) root.findViewById(R.id.et_username);
        passwordEditText = (EditText) root.findViewById(R.id.et_password);
        loginingProgressBar = (ProgressBar) root.findViewById(R.id.pb_login_ing);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mPresenter.bindView(this);
    }

    @Override
    public void showLoading() {
        loginingProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loginingProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMainActivity() {
        Intent intent = new Intent(mContext, MainActivity.class);
        startActivity(intent);
    }
}
