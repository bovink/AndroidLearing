package com.bovink.androidlearing.activity;

import javax.inject.Inject;

/**
 * @author fox
 * @since 2017/11/20
 */

public class MessageListPresenter implements MessageListContract.Presenter {

    private MessageListContract.View mView;

    @Inject
    public MessageListPresenter() {
    }

    @Override
    public void bindView(MessageListContract.View view) {

        mView = view;
    }
}
