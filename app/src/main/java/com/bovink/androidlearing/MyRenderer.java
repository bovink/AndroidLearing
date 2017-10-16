package com.bovink.androidlearing;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.renderer.RadarChartRenderer;
import com.github.mikephil.charting.utils.ViewPortHandler;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/10/16
 */

public class MyRenderer extends RadarChartRenderer {

    public MyRenderer(RadarChart chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
        super(chart, animator, viewPortHandler);
    }

    @Override
    protected void drawDataSet(Canvas c, IRadarDataSet dataSet, int mostEntries) {
        super.drawDataSet(c, dataSet, mostEntries);
        System.out.println("dataSet.getEntryCount() = " + dataSet.getEntryCount());

        Path path = new Path();
        path.moveTo(100,100);
        path.lineTo(200,100);
        path.lineTo(200,200);
        path.lineTo(100,200);
        path.lineTo(100,100);
        drawFilledPath(c, path, Color.BLACK, 85);
    }
}
