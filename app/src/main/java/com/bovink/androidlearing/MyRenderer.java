package com.bovink.androidlearing;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.renderer.RadarChartRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
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
    public void drawData(Canvas c) {
        super.drawData(c);

        setdd(c);
    }

    private int[] colors = {Color.BLACK, Color.DKGRAY, Color.GRAY, Color.LTGRAY, Color.WHITE};
    private Path previousPath;

    private void setdd(Canvas c) {

        float phaseX = mAnimator.getPhaseX();
        float phaseY = mAnimator.getPhaseY();

        float sliceangle = mChart.getSliceAngle();

        float factor = mChart.getFactor();

        MPPointF center = mChart.getCenterOffsets();
        MPPointF pOut = MPPointF.getInstance(0, 0);
        Path surface = mDrawDataSetSurfacePathBuffer;

        for (int i = 0; i < 5; i++) {// 几等分
            boolean hasMovedToPoint = false;

            surface.reset();

            for (int j = 0; j < 7; j++) {

                Utils.getPosition(
                        center,
                        (10 * (i + 1) - mChart.getYChartMin()) * factor * phaseY,
                        sliceangle * j * phaseX + mChart.getRotationAngle(), pOut);

                if (Float.isNaN(pOut.x))
                    continue;

                if (!hasMovedToPoint) {
                    surface.moveTo(pOut.x, pOut.y);
                    hasMovedToPoint = true;
                } else
                    surface.lineTo(pOut.x, pOut.y);
            }


            surface.close();
            Path buffer = new Path(surface);
            if (previousPath != null) {
                surface.op(previousPath, Path.Op.DIFFERENCE);
            }
            drawFilledPath(c, surface, colors[i], 128);
            previousPath = buffer;
        }

    }
}
