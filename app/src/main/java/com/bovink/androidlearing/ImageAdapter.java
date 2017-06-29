package com.bovink.androidlearing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/6/29
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
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

