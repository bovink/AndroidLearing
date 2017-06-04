package com.bovink.androidlearing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/6/2
 */

public class ListViewAdapter<T> extends BaseAdapter {
    /**
     * 表分开的表
     * 用来获取表头
     */
    private List<List<T>> listList;
    /**
     * 表集合的表
     */
    private List<T> lists;
    private Context context;

    public ListViewAdapter(List<List<T>> listList, Context context) {
        this.context = context;
        this.listList = listList;

        lists = new ArrayList<>();
        for (int i = 0; i < listList.size(); i++) {
            List<T> list = listList.get(i);
            for (int j = 0; j < list.size(); j++) {
                lists.add(list.get(j));
            }
        }
        for (int i = 0; i < listList.size(); i++) {

        }
    }

    private int curIndex = 0;



    /**
     * 实际count应该只有lists.size()，不过要另外加上表头
     *
     * @return
     */
    private int getCountInner() {
        return lists.size() + listList.size();
    }

    private T getItemInner(int position) {
        // 如果不是项目
        if (getItemViewType(position) != listList.size()) {
            return null;
        }
        return lists.get(position - curIndex - 1);
    }

    private long getItemIdInner(int position) {
        if (getItemViewType(position) != listList.size()) {
            return -1;
        }
        return position - curIndex - 1;
    }

    @Override
    public int getCount() {
        return getCountInner();
    }

    @Override
    public T getItem(int position) {
        return getItemInner(position);
    }

    @Override
    public long getItemId(int position) {
        return getItemIdInner(position);
    }

    @Override
    public int getItemViewType(int position) {
        // 第几个表
        int index = 0;
        // 表头的位置
        int headerPosition = 0;

        while (position != headerPosition) {
            // 查询的表头位置已经超过实际位置
            if (position < headerPosition || index == listList.size()) {
                index = listList.size();
                break;
            }
            headerPosition += listList.get(index).size() + 1;
            index++;
        }

        System.out.println("index = " + index);
        if (index != listList.size()) {
            curIndex = index;
        }
        return index;
    }

    @Override
    public int getViewTypeCount() {
        return listList.size() + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            if (getItemViewType(position) != listList.size()) {
                convertView = LayoutInflater.from(context).inflate(R.layout.listview_header, null);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_header);
                convertView.setTag(viewHolder);
            } else {
                convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_content);
                convertView.setTag(viewHolder);

            }
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (getItemViewType(position) != listList.size()) {
            viewHolder.textView.setText("头部");

        } else {
            viewHolder.textView.setText((String) getItemInner(position));


        }

        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}
