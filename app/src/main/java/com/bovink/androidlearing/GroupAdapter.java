package com.bovink.androidlearing;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

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

//    private Context context;

    /**
     * 标题类型
     */
    private final int TYPE_TITLE = 0;
    /**
     * 内容类型
     */
    private final int TYPE_ITEM = 1;

    /**
     * 当前内容表的索引
     */
    private int curIndex = 0;

//    public GroupAdapter(List<List<I>> itemList, List<T> titleList, Context context) {
//        this.itemList = itemList;
//        this.titleList = titleList;
//        this.context = context;
//
//        itemTotalList = new ArrayList<>();
//
//        for (int i = 0; i < itemList.size(); i++) {
//            List<I> list = itemList.get(i);
//            for (int j = 0; j < list.size(); j++) {
//                itemTotalList.add(list.get(j));
//            }
//        }
//    }


    public void setItemList(List<List<I>> itemList) {
        this.itemList = itemList;

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

    public void setTitleList(List<T> titleList) {
        this.titleList = titleList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TITLE) {
            return onCreateTitleViewHolder(parent, viewType);
        } else if (viewType == TYPE_ITEM) {
            return onCreateItemViewHolder(parent, viewType);
        }
        return null;
    }

    /**
     * 创建标题栏
     * @param parent
     * @param viewType
     * @return
     */
    public abstract RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent, int viewType);

    /**
     * 创建内容栏
     * @param parent
     * @param viewType
     * @return
     */
    public abstract RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType);

    /**
     * 绑定标题栏数据
     * @param holder
     * @param position
     */
    public abstract void onBindTitleViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * 绑定内容栏数据
     * @param holder
     * @param position
     */
    public abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewTypeInner(position);
        if (type == TYPE_ITEM) {
//            holder.tv_content.setText((String) itemTotalList.get(position - curIndex - 1));
            System.out.println("position = " + position);
            System.out.println("curIndex = " + curIndex);
            onBindItemViewHolder(holder, position - curIndex - 1);

        } else if (type == TYPE_TITLE) {
//            holder.tv_content.setText((String) titleList.get(curIndex));
            onBindTitleViewHolder(holder, curIndex);
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
