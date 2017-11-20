package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tiilii.jhcloud.R;
import com.tiilii.jhcloud.base.BaseFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author fox
 * @since 2017/11/20
 */

public class MessageListFragment extends BaseFragment implements MessageListContract.View {

    @Inject
    MessageListContract.Presenter mPresenter;

    @Inject
    public MessageListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_message_list, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mPresenter.bindView(this);
    }
}
