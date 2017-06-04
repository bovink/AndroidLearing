package com.bovink.androidlearing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/6/4
 */

public class ListViewNormalAdapter extends BaseAdapter {

    private List<String> list;
    private List<String> listtwo;

    private Context context;

    public ListViewNormalAdapter(List<String> list, List<String> listtwo, Context context) {

        this.list = list;
        this.listtwo = listtwo;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size() + listtwo.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {

            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.textView.setText(list.get(position));

        return convertView;
    }

    private class ViewHolder {
        private TextView textView;
    }
}
