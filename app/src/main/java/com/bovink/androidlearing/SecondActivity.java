package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/6/29
 */

public class SecondActivity extends AppCompatActivity {
    @BindView(R.id.rv_image)
    RecyclerView imageRecyclerView;

    @BindView(R.id.nsv_main)
    NestedScrollView scrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        imageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageRecyclerView.setNestedScrollingEnabled(false);
        ImageAdapter adapter = new ImageAdapter(20, this);
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();


            }
        });
        imageRecyclerView.setAdapter(adapter);
    }
}
