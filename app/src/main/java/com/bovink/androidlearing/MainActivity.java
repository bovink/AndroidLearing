package com.bovink.androidlearing;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private List<String> nameList = new ArrayList<>();
    @BindView(R.id.rv_test)
    RecyclerView testRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;

        nameList.add("hello");
        nameList.add("hello");

        testRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        testRecyclerView.setAdapter(new TestAdapter());
    }

    public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {

        @Override
        public TestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new TestHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_test, parent, false));
        }

        @Override
        public void onBindViewHolder(TestHolder holder, int position) {

            holder.nameTextView.setText(nameList.get(position));
        }

        @Override
        public int getItemCount() {
            return nameList.size();
        }

        public class TestHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_name)
            TextView nameTextView;

            public TestHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
