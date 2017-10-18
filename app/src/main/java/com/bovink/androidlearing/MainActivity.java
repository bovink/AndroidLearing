package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.lc_example)
    LineChart exampleLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initLineChart();
    }

    private void initLineChart() {
        exampleLineChart.setTouchEnabled(false);
        exampleLineChart.getDescription().setEnabled(false);

        Legend legend = exampleLineChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

        XAxis xAxis = exampleLineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftYAxis = exampleLineChart.getAxisLeft();
        leftYAxis.setMaxWidth(10);
        leftYAxis.setAxisMinimum(0);
        leftYAxis.setAxisMaximum(55);

        YAxis rightYAxis = exampleLineChart.getAxisRight();
        rightYAxis.setDrawLabels(false);
        rightYAxis.setDrawAxisLine(false);
        rightYAxis.setDrawGridLines(false);

        setData();
    }

    private void setData() {

        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            entries.add(new Entry(i, (float) (Math.random() * 50)));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "1");

        LineData lineData = new LineData(lineDataSet);
        lineData.setDrawValues(false);

        exampleLineChart.setData(lineData);

    }

}
