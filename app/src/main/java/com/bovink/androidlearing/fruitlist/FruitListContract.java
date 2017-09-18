package com.bovink.androidlearing.fruitlist;

import com.bovink.androidlearing.BasePresenter;
import com.bovink.androidlearing.BaseView;

/**
 * com.bovink.androidlearing.fruitlist
 *
 * @author bovink
 * @since 2017/9/18
 */

public interface FruitListContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

    }
}
