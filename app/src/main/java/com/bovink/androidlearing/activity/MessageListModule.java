package com.bovink.androidlearing.activity;

import com.tiilii.jhcloud.di.ActivityScoped;

import dagger.Module;
import dagger.Provides;

/**
 * @author fox
 * @since 2017/11/20
 */

@Module
public class MessageListModule {

    @ActivityScoped
    @Provides
    MessageListContract.Presenter providesPresenter(MessageListPresenter messageListPresenter) {
        return messageListPresenter;
    }
}
