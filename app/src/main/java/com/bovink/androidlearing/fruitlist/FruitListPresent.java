package com.bovink.androidlearing.fruitlist;

import com.bovink.androidlearing.model.Fruit;

import java.util.ArrayList;
import java.util.List;

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

        loadFruits();
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

        view.showFruits(fruitList);
    }
}
