package com.bovink.androidlearing.fruitlist;

import com.bovink.androidlearing.BasePresenter;
import com.bovink.androidlearing.BaseView;
import com.bovink.androidlearing.model.Fruit;

import java.util.List;

/**
 * com.bovink.androidlearing.fruitlist
 *
 * @author bovink
 * @since 2017/9/18
 */

public interface FruitListContract {

    interface View extends BaseView {

        void showFruits(List<Fruit> fruits);

    }

    interface Presenter extends BasePresenter<View> {

        void loadFruits();

        void setView(View view);

        void clearFruits();
    }
}
