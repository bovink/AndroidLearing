package com.bovink.androidlearing;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/6/4
 */

public abstract class GroupAdapter<I, T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * 内容表
     */
    private List<List<I>> itemList;
    /**
     * 总内容表
     */
    private List<I> itemTotalList;
    /**
     * 标题表
     */
    private List<T> titleList;

    /**
     * 标题类型
     */
    public static final int TYPE_TITLE = 0;
    /**
     * 内容类型
     */
    public static final int TYPE_ITEM = 1;

    /**
     * 当前内容表的索引
     */
    private int curIndex = 0;

    public GroupAdapter(List<List<I>> itemList, List<T> titleList) {
        this.itemList = itemList;
        this.titleList = titleList;

        itemTotalList = new ArrayList<>();

        for (int i = 0; i < itemList.size(); i++) {
            List<I> list = itemList.get(i);
            for (int j = 0; j < list.size(); j++) {
                itemTotalList.add(list.get(j));
            }
        }
    }


    public List<I> getItemTotalList() {
        return itemTotalList;
    }


    /**
     * 绑定标题栏数据
     *
     * @param holder
     * @param position
     */
    public abstract void onBindTitleViewHolder(RecyclerView.ViewHolder holder, T title, int position);

    /**
     * 绑定内容栏数据
     *
     * @param holder
     * @param position
     */
    public abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, I item, int position);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewTypeInner(position);
        if (type == TYPE_ITEM) {

            onBindItemViewHolder(holder, itemTotalList.get(position - curIndex - 1), position - curIndex - 1);

        } else if (type == TYPE_TITLE) {

            onBindTitleViewHolder(holder, titleList.get(curIndex), curIndex);
        }

    }

    @Override
    public int getItemCount() {
        return itemTotalList.size() + titleList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewTypeInner(position);
    }

    private int getItemViewTypeInner(int position) {
        // 当前内容表的索引
        int index = 0;
        // 当前内容表标题头部的位置
        int titlePosition = 0;

        while (position != titlePosition) {
            // 查询的表头位置已经超过实际位置
            if (position < titlePosition || index == itemList.size()) {
                curIndex = index - 1;
                return TYPE_ITEM;
            }
            titlePosition += itemList.get(index).size() + 1;
            index++;
        }

        curIndex = index;
        return TYPE_TITLE;
    }
}
