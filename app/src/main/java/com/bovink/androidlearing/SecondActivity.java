package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    ImageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);

        imageRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        imageRecyclerView.setNestedScrollingEnabled(false);
        adapter = new ImageAdapter(this);

        imageRecyclerView.setAdapter(adapter);

        imageRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                System.out.println("SecondActivity.onGlobalLayout");
                scrollView.scrollTo(0, scrollView.getChildAt(0).getMeasuredHeight() - scrollView.getMeasuredHeight());
            }
        });

    }

    @OnClick(R.id.btn_last)
    void last() {

        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            stringList.add("one :" + i);
        }
        adapter.addData(stringList);
        adapter.notifyDataSetChanged();
    }
}
