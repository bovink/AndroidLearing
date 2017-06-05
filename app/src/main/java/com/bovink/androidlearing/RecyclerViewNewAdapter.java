package com.bovink.androidlearing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/6/4
 */

public abstract class RecyclerViewNewAdapter<I, T>
        extends RecyclerView.Adapter<RecyclerViewNewAdapter<I, T>.MyRecyclerViewHolder> {
    /**
     * 内容表
     */
    private List<List<I>> itemList;
    /**
     * 标题表
     */
    private List<T> titleList;
    /**
     * 总内容表
     */
    private List<I> itemTotalList;

    private Context context;

    private final int TYPE_TITLE = 0;

    private final int TYPE_ITEM = 1;

    /**
     * 当前内容表的索引
     */
    private int curIndex = 0;

    public RecyclerViewNewAdapter(List<List<I>> itemList, List<T> titleList, Context context) {
        this.itemList = itemList;
        this.titleList = titleList;
        this.context = context;

        itemTotalList = new ArrayList<>();

        for (int i = 0; i < itemList.size(); i++) {
            List<I> list = itemList.get(i);
            for (int j = 0; j < list.size(); j++) {
                itemTotalList.add(list.get(j));
            }
        }
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("==============================");
        System.out.println("RecyclerViewAdapter.onCreateViewHolder");
        System.out.println("viewType = " + viewType);
        System.out.println("curIndex = " + curIndex);
        System.out.println("==============================");
        return new MyRecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
        int type = getItemViewTypeInner(position);
        if (type == TYPE_ITEM) {
            holder.tv_content.setText((String) itemTotalList.get(position - curIndex - 1));

        } else if (type == TYPE_TITLE) {
            holder.tv_content.setText((String) titleList.get(curIndex));
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
                return TYPE_ITEM;
            }
            titlePosition += itemList.get(index).size() + 1;
            index++;
        }

        curIndex = index;
        return TYPE_TITLE;
    }

    public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tv_content;

        public MyRecyclerViewHolder(View itemView) {
            super(itemView);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
