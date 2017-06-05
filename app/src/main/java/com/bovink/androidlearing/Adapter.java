package com.bovink.androidlearing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/6/4
 */

public class Adapter extends GroupAdapter<String, String> {
    private Context mContext;

    public Adapter(Context context, List<List<String>> itemList, List<String> titleList) {
        super(itemList, titleList);
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == GroupAdapter.TYPE_TITLE) {

            return new TitleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listview_header, parent, false));
        } else if (viewType == GroupAdapter.TYPE_ITEM) {

            return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listview_item, parent, false));
        }
        return null;
    }


    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String title, int position) {
        TitleViewHolder viewHolder = (TitleViewHolder) holder;
        viewHolder.tv_header.setText(title);

    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, String item, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.tv_content.setText(item);
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView tv_header;

        public TitleViewHolder(View itemView) {
            super(itemView);
            tv_header = (TextView) itemView.findViewById(R.id.tv_header);
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
