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

        ArrayList<List<String>> lists = new ArrayList<>();
        ArrayList<String> strings1 = new ArrayList<>();
        ArrayList<String> strings2 = new ArrayList<>();
        ArrayList<String> strings3 = new ArrayList<>();

        strings1.add("one");
        strings1.add("two");
        lists.add(strings1);

        strings2.add("three");
        strings2.add("four");
        strings2.add("five");
        lists.add(strings2);

        strings3.add("six");
        strings3.add("seven");
        lists.add(strings3);

//        ListViewAdapter<String> adapter = new ListViewAdapter<>(lists, this);
//
//
//        listView.setAdapter(adapter);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(strings2, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }
}
