package com.bovink.androidlearing;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Utils;

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

        exampleBarChart.setBackgroundColor(Color.WHITE);
        exampleBarChart.getDescription().setEnabled(false);



        XAxis xAxis = exampleBarChart.getXAxis();
        xAxis.setDrawLabels(false);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(10f);

        YAxis leftYAxis = exampleBarChart.getAxisLeft();
        leftYAxis.setDrawLabels(false);
        leftYAxis.setDrawGridLines(false);
        leftYAxis.setAxisMinimum(0);
        leftYAxis.setAxisMaximum(100);

        YAxis rightYAxis = exampleBarChart.getAxisRight();
        rightYAxis.setDrawLabels(false);
        rightYAxis.setDrawGridLines(false);
        rightYAxis.setDrawAxisLine(false);

        setData();
    }

    private void setData() {

        List<BarEntry> entries1 = new ArrayList<>();
        entries1.add(new BarEntry(10, 99f));
        List<BarEntry> entries2 = new ArrayList<>();
        entries2.add(new BarEntry(9, 40f));
        List<BarEntry> entries3 = new ArrayList<>();
        entries3.add(new BarEntry(8, 35f));
        List<BarEntry> entries4 = new ArrayList<>();
        entries4.add(new BarEntry(7, 45f));
        List<BarEntry> entries5 = new ArrayList<>();
        entries5.add(new BarEntry(6, 25f));

        BarDataSet set1 = new BarDataSet(entries1, "BarDataSet1");
        set1.setColor(Color.LTGRAY);
        BarDataSet set2 = new BarDataSet(entries2, "BarDataSet2");
        set2.setColor(Color.GRAY);
        BarDataSet set3 = new BarDataSet(entries3, "BarDataSet3");
        set3.setColor(Color.DKGRAY);
        BarDataSet set4 = new BarDataSet(entries4, "BarDataSet4");
        set4.setColor(Color.BLACK);
        BarDataSet set5 = new BarDataSet(entries5, "BarDataSet5");
        set5.setColor(Color.BLUE);

        List<IBarDataSet> sets = new ArrayList<>();
        sets.add(set5);
        sets.add(set4);
        sets.add(set3);
        sets.add(set2);
        sets.add(set1);

        MyBarData data = new MyBarData(sets);
        // 2即代表20%的X轴
        data.setBarWidth(2f);
        data.setDrawValues(false);
        // 第三个参数为10即代表为100%的X轴，当fromX为顶点时，默认第一个Bar间隔顶点0.5倍的barSpace
        data.groupBarsOpposite(10f, 0, 0f);
        exampleBarChart.setData(data);
        exampleBarChart.setMinOffset(0);
        // 算出圆球到顶端的距离
        // 算出label之间的间距

        Legend legend = exampleBarChart.getLegend();
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setForm(Legend.LegendForm.CIRCLE);

        legend.setFormSize(20);
        legend.setTextSize(9);

        Paint mLegendLabelPaint = exampleBarChart.getLegendRenderer().getLabelPaint();
        Paint.FontMetrics legendFontMetrics = new Paint.FontMetrics();

        float labelLineHeight = Utils.getLineHeight(mLegendLabelPaint, legendFontMetrics);
        float labelLineSpacing = Utils.getLineSpacing(mLegendLabelPaint, legendFontMetrics)
                + Utils.convertDpToPixel(legend.getYEntrySpace());
        float formYOffset = labelLineHeight - Utils.calcTextHeight(mLegendLabelPaint, "ABC") / 2.f;
        System.out.println("labelLineHeight = " + labelLineHeight);
        System.out.println("labelLineSpacing = " + labelLineSpacing);
        System.out.println("formYOffset = " + formYOffset);

        legend.setYEntrySpace(30);
        System.out.println("DensityUtils.dpToPx(this, 10) = " + DensityUtils.dpToPx(this, 10));
    }

}
