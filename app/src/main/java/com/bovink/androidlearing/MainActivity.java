package com.bovink.androidlearing;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    }

    private void initRecyclerView() {
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        imageRecyclerView.setLayoutManager(manager);
        ImageAdapter adapter = new ImageAdapter(20, this);
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

    class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
        private int count;
        private Context context;

        public ImageAdapter(int count, Context context) {
            this.count = count;
            this.context = context;
        }

        @Override
        public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_image, parent, false));
        }

        @Override
        public void onBindViewHolder(ImageViewHolder holder, int position) {

            holder.indexTextView.setText(position + "");
        }

        @Override
        public int getItemCount() {
            return count;
        }

        class ImageViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_index)
            TextView indexTextView;

            ImageViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
