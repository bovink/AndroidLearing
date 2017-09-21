package com.bovink.androidlearing.fruitlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.R;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

/**
 * com.bovink.androidlearing.fruitlist
 *
 * @author bovink
 * @since 2017/9/18
 */

public class FruitListActivity extends AppCompatActivity {

    @Inject
    FruitListFragment fruitListFragment;

    @Inject
    FruitListPresent fruitListPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_fragment, fruitListFragment)
                .commit();



    }

    @OnClick(R.id.btn_list)
    void listNew() {
        fruitListPresent.clearFruits();
    }
}
