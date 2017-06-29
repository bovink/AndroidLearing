package com.bovink.androidlearing;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_image)
    RecyclerView imageRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initRecyclerView();
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    private void initRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        imageRecyclerView.setLayoutManager(manager);
        ImageAdapter adapter = new ImageAdapter(this);
        imageRecyclerView.addItemDecoration(new ImageDecoration(4, 5));
        imageRecyclerView.setAdapter(adapter);


    }

    class ImageDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int space;

        public ImageDecoration(int spanCount, int space) {
            this.spanCount = spanCount;
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int index = parent.getChildAdapterPosition(view);

            outRect.top = space;
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            if (index < spanCount) {
                outRect.top = space * 2;
            }
            if (index % spanCount == 0) {
                outRect.left = space * 2;
            }
            if (index % spanCount == spanCount - 1) {
                outRect.right = space * 2;
            }
        }
    }

}
