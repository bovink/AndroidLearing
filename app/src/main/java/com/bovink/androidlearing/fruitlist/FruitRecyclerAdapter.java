package com.bovink.androidlearing.fruitlist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * com.bovink.androidlearing.fruitlist
 *
 * @author bovink
 * @since 2017/9/18
 */
public class FruitRecyclerAdapter extends RecyclerView.Adapter<FruitRecyclerAdapter.FruitHolder> {

    @Override
    public FruitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(FruitHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class FruitHolder extends RecyclerView.ViewHolder {

        public FruitHolder(View itemView) {
            super(itemView);
        }
    }
}
