package com.bovink.androidlearing.fruitlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bovink.androidlearing.R;
import com.bovink.androidlearing.model.Fruit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * com.bovink.androidlearing.fruitlist
 *
 * @author bovink
 * @since 2017/9/18
 */
public class FruitRecyclerAdapter extends RecyclerView.Adapter<FruitRecyclerAdapter.FruitHolder> {

    private Context context;

    private List<Fruit> fruitList = new ArrayList<>();

    public FruitRecyclerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public FruitHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FruitHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_fruit, parent, false));
    }

    @Override
    public void onBindViewHolder(FruitHolder holder, int position) {
        holder.fruitNameTextView.setText(fruitList.get(position).getName());

        holder.fruitPriceTextView.setText(fruitList.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }

    public void addDatas(List<Fruit> datas) {
        fruitList.addAll(datas);
        notifyDataSetChanged();
    }

    public void clearDatas() {
        fruitList.clear();
        notifyDataSetChanged();
    }

    public class FruitHolder extends RecyclerView.ViewHolder {
        /**
         * 水果名
         */
        @BindView(R.id.tv_fruit_name)
        TextView fruitNameTextView;
        /**
         * 水果价格
         */
        @BindView(R.id.tv_fruit_price)
        TextView fruitPriceTextView;


        public FruitHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
