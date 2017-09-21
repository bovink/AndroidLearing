package com.bovink.androidlearing.fruitlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.R;

import dagger.android.AndroidInjection;

/**
 * com.bovink.androidlearing.fruitlist
 *
 * @author bovink
 * @since 2017/9/18
 */

public class FruitListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        FruitListFragment fruitListFragment = FruitListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_fragment, fruitListFragment)
                .commit();

        FruitListPresent fruitListPresent = new FruitListPresent(fruitListFragment);


    }
}
