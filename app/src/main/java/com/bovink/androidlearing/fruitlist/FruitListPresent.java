package com.bovink.androidlearing.fruitlist;

import android.content.Context;
import android.widget.Toast;

import com.bovink.androidlearing.model.Fruit;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * com.bovink.androidlearing.fruitlist
 *
 * @author bovink
 * @since 2017/9/18
 */

@Singleton
public class FruitListPresent implements FruitListContract.Presenter {
    private FruitListContract.View mView;
    private Context mContext;

    @Inject
    public FruitListPresent(Context context) {

        mContext = context;
    }

    @Override
    public void setView(FruitListContract.View view) {

        mView = view;
        loadFruits();
    }

    @Override
    public void clearFruits() {
        List<Fruit> fruitList = new ArrayList<>();

        fruitList.add(new Fruit("苹果", "1元"));
        fruitList.add(new Fruit("梨子", "1元"));

        mView.showFruits(fruitList);
        Toast.makeText(mContext, "加载完毕", Toast.LENGTH_LONG).show();
    }


    @Override
    public void loadFruits() {

        List<Fruit> fruitList = new ArrayList<>();

        fruitList.add(new Fruit("苹果", "12元"));
        fruitList.add(new Fruit("梨子", "13元"));
        fruitList.add(new Fruit("香蕉", "14元"));
        fruitList.add(new Fruit("菠萝", "5元"));
        fruitList.add(new Fruit("橙子", "6元"));
        fruitList.add(new Fruit("橘子", "62元"));

        mView.showFruits(fruitList);
        Toast.makeText(mContext, "加载完毕", Toast.LENGTH_LONG).show();
    }
}
