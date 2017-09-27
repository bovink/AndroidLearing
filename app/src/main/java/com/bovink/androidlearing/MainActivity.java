package com.bovink.androidlearing;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private Context context;
    @BindView(R.id.dl_slide)
    DrawerLayout slideDrawerLayout;

    @BindView(R.id.btn_slide)
    Button slideButton;

    @BindView(R.id.lv_menu)
    ListView menuListView;

    private List<String> strings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        context = this;
        strings.add("one");
        strings.add("two");
        strings.add("three");
        menuListView.setAdapter(new MyMenuAdapter());
    }

    @OnClick(R.id.btn_slide)
    void slde() {
        slideDrawerLayout.openDrawer(menuListView);
    }

    private void select(String s) {
        slideButton.setText(s);
        slideDrawerLayout.closeDrawer(menuListView);
    }

    public class MyMenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return strings.size();
        }

        @Override
        public Object getItem(int position) {
            return strings.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_drawer_list, null);

                viewHolder = new ViewHolder();

                viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_text);
                viewHolder.chooseButton = (Button) convertView.findViewById(R.id.btn_choose);

                convertView.setTag(viewHolder);
            } else {

                viewHolder = (ViewHolder) convertView.getTag();

            }

            viewHolder.textView.setText(strings.get(position));

            viewHolder.chooseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    select(strings.get(position));
                }
            });

            return convertView;
        }

        public class ViewHolder {
            private TextView textView;
            private Button chooseButton;
        }
    }

}
