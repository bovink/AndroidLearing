package com.bovink.androidlearing.activity;

import com.tiilii.jhcloud.base.mvp.BasePresenter;
import com.tiilii.jhcloud.base.mvp.BaseView;

/**
 * @author fox
 * @since 2017/11/20
 */

interface MessageListContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
