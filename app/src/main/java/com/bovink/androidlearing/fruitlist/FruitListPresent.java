package com.bovink.androidlearing.fruitlist;

/**
 * com.bovink.androidlearing.fruitlist
 *
 * @author bovink
 * @since 2017/9/18
 */

public class FruitListPresent implements FruitListContract.Presenter {
    private FruitListContract.View view;

    public FruitListPresent(FruitListContract.View view) {
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
