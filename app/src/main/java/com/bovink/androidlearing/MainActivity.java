package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rv_fruit)
    RecyclerView fruitRecyclerView;
    @BindView(R.id.fab_button)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        FruitAdapter fruitAdapter = new FruitAdapter(this);
        fruitRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        fruitRecyclerView.setAdapter(fruitAdapter);

        List<Fruit> fruitList = new ArrayList<>();

        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));
        fruitList.add(new Fruit("橘子", "16"));

        fruitAdapter.addData(fruitList);

        fruitRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                    fab.hide();
                } else {
                    fab.show();
                }
            }
        });
    }
}
