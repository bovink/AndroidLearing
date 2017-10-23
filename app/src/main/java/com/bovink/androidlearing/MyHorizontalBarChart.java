package com.bovink.androidlearing;

import android.content.Context;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.HorizontalBarChart;

/**
 * @author fox
 * @since 2017/10/23
 */

public class MyHorizontalBarChart extends HorizontalBarChart {
    public MyHorizontalBarChart(Context context) {
        super(context);
    }

    public MyHorizontalBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizontalBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void init() {
        super.init();
        mLegendRenderer = new MyLegendRenderer(mViewPortHandler, mLegend);
    }
}
