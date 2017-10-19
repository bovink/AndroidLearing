package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bc_example)
    HorizontalBarChart exampleBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        exampleBarChart.getDescription().setEnabled(false);
        exampleBarChart.getLegend().setEnabled(false);

        XAxis xAxis = exampleBarChart.getXAxis();
//        xAxis.setDrawLabels(false);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(-0.5f);
        xAxis.setAxisMaximum(99.5f);
//        xAxis.setDrawAxisLine(false);

        YAxis leftYAxis = exampleBarChart.getAxisLeft();
//        leftYAxis.setDrawLabels(false);
        leftYAxis.setDrawGridLines(false);
//        leftYAxis.setDrawAxisLine(false);
//        leftYAxis.setAxisMaximum(100);

        YAxis rightYAxis = exampleBarChart.getAxisRight();
        rightYAxis.setDrawLabels(false);
        rightYAxis.setDrawGridLines(false);
        rightYAxis.setDrawAxisLine(false);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(80f, 30f));
//        entries.add(new BarEntry(1f, 80f));
//        entries.add(new BarEntry(2f, 60f));
//        entries.add(new BarEntry(3f, 50f));
//        // gap of 2f
//        entries.add(new BarEntry(5f, 70f));
//        entries.add(new BarEntry(6f, 60f));

        BarDataSet set = new BarDataSet(entries, "BarDataSet");

        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        data.setDrawValues(false);
        exampleBarChart.setData(data);
        exampleBarChart.setFitBars(true); // make the x-axis fit exactly all bars
    }

}
