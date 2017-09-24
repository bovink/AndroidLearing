package com.bovink.androidlearing;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp_content)
    ViewPager contentViewPager;

    @BindView(R.id.tl_indicator)
    TabLayout indicatorTabLayout;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragmentList.add(new FirstFragment());
        fragmentList.add(new SecondFragment());
        titleList.add("一");
        titleList.add("二");

        contentViewPager.setAdapter(new MyFragmentStatePagerAdapter(getSupportFragmentManager()));
        // 设置指示器的颜色
        indicatorTabLayout.setSelectedTabIndicatorColor(Color.parseColor("#000000"));
        // 设置指示器的高度
        indicatorTabLayout.setSelectedTabIndicatorHeight(10);
        // 设置字的普通状态和选中状态
        indicatorTabLayout.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#66CCFF"));
        // 默认MODE_FIXED。当Tab的总宽度大于屏幕的宽度时 ，可以选择MODE_SCROLLABLE，让Tab滚动，
        indicatorTabLayout.setTabMode(TabLayout.MODE_FIXED);
        // 默认GRAVITY_FILL，当Tab的的总宽度小于屏幕的宽度时，可以选择GRAVITY_CENTER，让Tab居中
        indicatorTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
    }

    private class MyFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

        public MyFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
