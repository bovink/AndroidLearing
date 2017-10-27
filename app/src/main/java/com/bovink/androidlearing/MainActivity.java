package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bc_example)
    BarChart exampleBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        exampleBarChart.getDescription().setEnabled(false);

        XAxis xAxis = exampleBarChart.getXAxis();
        xAxis.setDrawLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);

        YAxis leftYAxis = exampleBarChart.getAxisLeft();
        leftYAxis.setDrawLabels(false);
        leftYAxis.setDrawGridLines(false);
        leftYAxis.setDrawAxisLine(false);
        leftYAxis.setDrawZeroLine(true);
        leftYAxis.setAxisMaximum(100);

        YAxis rightYAxis = exampleBarChart.getAxisRight();
        rightYAxis.setDrawLabels(false);
        rightYAxis.setDrawGridLines(false);
        rightYAxis.setDrawAxisLine(false);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 100f));
        entries.add(new BarEntry(1f, 80f));
        entries.add(new BarEntry(2f, 60f));
        entries.add(new BarEntry(3f, 50f));
        // gap of 2f
        entries.add(new BarEntry(5f, 70f));
        entries.add(new BarEntry(6f, 60f));

        List<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(0f, -100f));

        BarDataSet set = new BarDataSet(entries, "BarDataSet");

        BarDataSet set2 = new BarDataSet(entries2, "aaa");

        List<IBarDataSet> sets = new ArrayList<>();
        sets.add(set);
        sets.add(set2);

        BarData data = new BarData(sets);
        data.setBarWidth(0.9f); // set custom bar width
        data.setDrawValues(false);
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
