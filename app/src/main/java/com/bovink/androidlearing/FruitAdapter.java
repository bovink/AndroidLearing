package com.bovink.androidlearing;

import android.content.Context;
import android.support.design.widget.Snackbar;
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
 * @since 2017/9/25
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.FruitItemHolder> {


    private Context mContext;

    private List<Fruit> fruitList = new ArrayList<>();

    public FruitAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public FruitItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FruitItemHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recycler_fruit, parent, false));
    }

    @Override
    public void onBindViewHolder(FruitItemHolder holder, int position) {
        holder.nameTextView.setText(fruitList.get(position).getName());

        holder.priceTextView.setText(fruitList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }

    public void addData(List<Fruit> data) {
        fruitList.addAll(data);
        notifyDataSetChanged();
    }

    public void clearData() {
        fruitList.clear();
        notifyDataSetChanged();
    }

    public class FruitItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView nameTextView;

        @BindView(R.id.tv_price)
        TextView priceTextView;

        public FruitItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Hello,World!", Snackbar.LENGTH_SHORT).show();
                }
            });
        }
    }
}
