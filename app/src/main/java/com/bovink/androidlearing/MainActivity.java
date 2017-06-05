package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listview)
    ListView listView;

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ArrayList<String> item1 = new ArrayList<>();
        ArrayList<String> item2 = new ArrayList<>();
        ArrayList<String> item3 = new ArrayList<>();

        item1.add("apple");
        item1.add("tomato");
        item1.add("apple");

        item2.add("java");
        item2.add("c++");
        item2.add("javascript");
        item2.add("python");

        item3.add("China");
        item3.add("USA");
        item3.add("Russia");

        ArrayList<List<String>> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);


        ArrayList<String> titleList = new ArrayList<>();
        titleList.add("fruit");
        titleList.add("language");
        titleList.add("country");

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, itemList, titleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
