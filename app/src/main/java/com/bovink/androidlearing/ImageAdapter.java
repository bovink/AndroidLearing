package com.bovink.androidlearing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/6/29
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context context;

    private List<String> strings = new ArrayList<>();

    public ImageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_image, parent, false));
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

        holder.indexTextView.setText(strings.get(position));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public void addData(List<String> addStrings) {
        strings.addAll(addStrings);
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

