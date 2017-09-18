package com.bovink.androidlearing.fruitlist;

import android.support.v4.app.Fragment;

/**
 * com.bovink.androidlearing.fruitlist
 *
 * @author bovink
 * @since 2017/9/18
 */

public class FruitListFragment extends Fragment implements FruitListContract.View {
    private FruitListContract.Presenter presenter;

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(FruitListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}
