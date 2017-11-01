package com.bovink.androidlearing;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bc_example)
    BarChart exampleBarChart;
    float max = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        exampleBarChart.getDescription().setEnabled(false);
        exampleBarChart.setMaxVisibleValueCount(1000);

        XAxis xAxis = exampleBarChart.getXAxis();
        xAxis.setDrawLabels(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);

        YAxis leftYAxis = exampleBarChart.getAxisLeft();
//        leftYAxis.setDrawLabels(false);
        leftYAxis.setDrawGridLines(false);
        leftYAxis.setDrawAxisLine(false);
        leftYAxis.setDrawZeroLine(true);
        leftYAxis.setAxisMinimum(-100);
        leftYAxis.setAxisMaximum(100);

        YAxis rightYAxis = exampleBarChart.getAxisRight();
//        rightYAxis.setDrawLabels(false);
        rightYAxis.setDrawGridLines(false);
        rightYAxis.setDrawAxisLine(false);
        rightYAxis.setDrawZeroLine(true);
        rightYAxis.setAxisMinimum(-60);
        rightYAxis.setAxisMaximum(60);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 70));

        List<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(1, -55));

        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        set.setColor(Color.RED);

        BarDataSet set2 = new BarDataSet(entries2, "aaa");
        set2.setColor(Color.BLUE);
        set2.setAxisDependency(YAxis.AxisDependency.RIGHT);

        List<IBarDataSet> sets = new ArrayList<>();
        sets.add(set);
        sets.add(set2);

        BarData data = new BarData(sets);
        data.setValueTextSize(12);
        data.setValueTextColor(Color.parseColor("#000000"));

        data.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                if (value == max) {
                    return String.valueOf(value);
                }
                return "";
            }
        });
        data.setBarWidth(0.9f); // set custom bar width
//        data.setDrawValues(false);
        exampleBarChart.setData(data);
        exampleBarChart.setFitBars(true); // make the x-axis fit exactly all bars

        Legend legend = exampleBarChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setDirection(Legend.LegendDirection.LEFT_TO_RIGHT);
        legend.setDrawInside(true);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
    }

}
