package com.bovink.androidlearing;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rc_example)
    RadarChart exampleRadarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // 中点到顶点的线
        exampleRadarChart.setWebColor(Color.LTGRAY);
        // 环线
        exampleRadarChart.setWebColorInner(Color.LTGRAY);
        // 背景颜色
        exampleRadarChart.setBackgroundColor(Color.WHITE);
        // 去掉方块
        exampleRadarChart.getLegend().setEnabled(false);
        // 去掉描述
        exampleRadarChart.getDescription().setEnabled(false);

        XAxis xAxis = exampleRadarChart.getXAxis();

        // x上标签
        xAxis.setValueFormatter(new IAxisValueFormatter() {

            private String[] items = new String[]{"1", "2", "3", "4", "5", "6", "7"};

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return items[(int) value % items.length];
            }
        });

        YAxis yAxis = exampleRadarChart.getYAxis();
        // 设置有5层环
        yAxis.setLabelCount(6, true);
        yAxis.setAxisMinimum(0);
        //几等分
        yAxis.setAxisMaximum(50);
        // 设置环上是否有标签
        yAxis.setDrawLabels(true);

        setData();
    }

    private void setData() {
        ArrayList<RadarEntry> entries = new ArrayList<>();
        ArrayList<RadarEntry> entries2 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            entries.add(new RadarEntry(20));
            entries2.add(new RadarEntry(40));
        }

        RadarDataSet set = new RadarDataSet(entries, "");
        // 环颜色
        set.setColor(Color.WHITE);
        // 填充颜色
        set.setFillColor(Color.BLUE);
        // 是否填充
        set.setDrawFilled(true);

        RadarDataSet set2 = new RadarDataSet(entries2, "");
        // 环颜色
        set2.setColor(Color.WHITE);
        // 填充颜色
        set2.setFillColor(Color.RED);
        // 是否填充
        set2.setDrawFilled(true);

        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set);
        sets.add(set2);

        RadarData radarData = new RadarData(sets);
        // 环上的字
        radarData.setDrawValues(false);
        exampleRadarChart.setData(radarData);


    }
//
//    mChart = (RadarChart) findViewById(R.id.chart1);
//        mChart.setBackgroundColor(Color.rgb(60, 65, 82));
//
//        mChart.getDescription().setEnabled(false);
//
//        mChart.setWebLineWidth(1f);
//        mChart.setWebColor(Color.LTGRAY);
//        mChart.setWebLineWidthInner(1f);
//        mChart.setWebColorInner(Color.LTGRAY);
//        mChart.setWebAlpha(100);
//
//    // create a custom MarkerView (extend MarkerView) and specify the layout
//    // to use for it
//    MarkerView mv = new RadarMarkerView(this, R.layout.radar_markerview);
//        mv.setChartView(mChart); // For bounds control
//        mChart.setMarker(mv); // Set the marker to the chart
//
//    setData();
//
//        mChart.animateXY(
//                1400, 1400,
//    Easing.EasingOption.EaseInOutQuad,
//    Easing.EasingOption.EaseInOutQuad);
//
//    XAxis xAxis = mChart.getXAxis();
//        xAxis.setTypeface(mTfLight);
//        xAxis.setTextSize(9f);
//        xAxis.setYOffset(0f);
//        xAxis.setXOffset(0f);
//        xAxis.setValueFormatter(new IAxisValueFormatter() {
//
//        private String[] mActivities = new String[]{"Burger", "Steak", "Salad", "Pasta", "Pizza"};
//
//        @Override
//        public String getFormattedValue(float value, AxisBase axis) {
//            return mActivities[(int) value % mActivities.length];
//        }
//    });
//        xAxis.setTextColor(Color.WHITE);
//
//    YAxis yAxis = mChart.getYAxis();
//        yAxis.setTypeface(mTfLight);
//        yAxis.setLabelCount(5, false);
//        yAxis.setTextSize(9f);
//        yAxis.setAxisMinimum(0f);
//        yAxis.setAxisMaximum(80f);
//        yAxis.setDrawLabels(false);
//
//    Legend l = mChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setTypeface(mTfLight);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(5f);
//        l.setTextColor(Color.WHITE);
//}
//
//    public void setData() {
//
//        float mult = 80;
//        float min = 20;
//        int cnt = 5;
//
//        ArrayList<RadarEntry> entries1 = new ArrayList<RadarEntry>();
//        ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();
//
//        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
//        // the chart.
//        for (int i = 0; i < cnt; i++) {
//            float val1 = (float) (Math.random() * mult) + min;
//            entries1.add(new RadarEntry(val1));
//
//            float val2 = (float) (Math.random() * mult) + min;
//            entries2.add(new RadarEntry(val2));
//        }
//
//        RadarDataSet set1 = new RadarDataSet(entries1, "Last Week");
//        set1.setColor(Color.rgb(103, 110, 129));
//        set1.setFillColor(Color.rgb(103, 110, 129));
//        set1.setDrawFilled(true);
//        set1.setFillAlpha(180);
//        set1.setLineWidth(2f);
//        set1.setDrawHighlightCircleEnabled(true);
//        set1.setDrawHighlightIndicators(false);
//
//        RadarDataSet set2 = new RadarDataSet(entries2, "This Week");
//        set2.setColor(Color.rgb(121, 162, 175));
//        set2.setFillColor(Color.rgb(121, 162, 175));
//        set2.setDrawFilled(true);
//        set2.setFillAlpha(180);
//        set2.setLineWidth(2f);
//        set2.setDrawHighlightCircleEnabled(true);
//        set2.setDrawHighlightIndicators(false);
//
//        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
//        sets.add(set1);
//        sets.add(set2);
//
//        RadarData data = new RadarData(sets);
//        data.setValueTypeface(mTfLight);
//        data.setValueTextSize(8f);
//        data.setDrawValues(false);
//        data.setValueTextColor(Color.WHITE);
//
//        mChart.setData(data);
//        mChart.invalidate();
//    }
}
