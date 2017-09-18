package com.bovink.androidlearing.fruitlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bovink.androidlearing.R;

import butterknife.ButterKnife;

/**
 * com.bovink.androidlearing.fruitlist
 *
 * @author bovink
 * @since 2017/9/18
 */

public class FruitListFragment extends Fragment implements FruitListContract.View {
    private FruitListContract.Presenter presenter;

    public static FruitListFragment newInstance() {
        FruitListFragment fragment = new FruitListFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_fruit_list, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

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
