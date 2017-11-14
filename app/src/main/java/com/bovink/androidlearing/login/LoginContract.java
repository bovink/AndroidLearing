package com.bovink.androidlearing.login;

import com.bovink.androidlearing.BasePresenter;
import com.bovink.androidlearing.BaseView;

/**
 * @author fox
 * @since 2017/11/14
 */

interface LoginContract {

    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
