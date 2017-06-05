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

public class RecyclerViewAdapter extends RecyclerViewGroupAdapter<String, String> {
    private Context mContext;
    /**
     * 内容表
     */
    private List<List<String>> mItemList;
    /**
     * 总内容表
     */
    private List<String> mItemTotalList;
    /**
     * 标题表
     */
    private List<String> mTitleList;

    public RecyclerViewAdapter(Context context, List<List<String>> itemList, List<String> titleList) {
        setItemList(itemList);
        setTitleList(titleList);
        mContext = context;
        mItemList = itemList;
        mItemTotalList = getItemTotalList();
        mTitleList = titleList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent, int viewType) {
        return new TitleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listview_header, parent, false));
    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.listview_item, parent, false));
    }

    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, int position) {
        TitleViewHolder viewHolder = (TitleViewHolder) holder;
        viewHolder.tv_header.setText(mTitleList.get(position));

    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.tv_content.setText(mItemTotalList.get(position));
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
