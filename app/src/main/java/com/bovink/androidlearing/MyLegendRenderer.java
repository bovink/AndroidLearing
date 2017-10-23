package com.bovink.androidlearing;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.renderer.LegendRenderer;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.List;

/**
 * @author fox
 * @since 2017/10/23
 */

public class MyLegendRenderer extends LegendRenderer {

    public MyLegendRenderer(ViewPortHandler viewPortHandler, Legend legend) {
        super(viewPortHandler, legend);
    }

    public void renderLegend(Canvas c) {

        if (!mLegend.isEnabled())
            return;

        Typeface tf = mLegend.getTypeface();

        if (tf != null)
            mLegendLabelPaint.setTypeface(tf);

        mLegendLabelPaint.setTextSize(mLegend.getTextSize());
        mLegendLabelPaint.setColor(mLegend.getTextColor());

        float labelLineHeight = Utils.getLineHeight(mLegendLabelPaint, legendFontMetrics);
        float labelLineSpacing = Utils.getLineSpacing(mLegendLabelPaint, legendFontMetrics)
                + Utils.convertDpToPixel(mLegend.getYEntrySpace());
        float formYOffset = labelLineHeight - Utils.calcTextHeight(mLegendLabelPaint, "ABC") / 2.f;

        LegendEntry[] entries = mLegend.getEntries();

        float formToTextSpace = Utils.convertDpToPixel(mLegend.getFormToTextSpace());
        float xEntrySpace = Utils.convertDpToPixel(mLegend.getXEntrySpace());
        Legend.LegendOrientation orientation = mLegend.getOrientation();
        Legend.LegendHorizontalAlignment horizontalAlignment = mLegend.getHorizontalAlignment();
        Legend.LegendVerticalAlignment verticalAlignment = mLegend.getVerticalAlignment();
        Legend.LegendDirection direction = mLegend.getDirection();
        float defaultFormSize = Utils.convertDpToPixel(mLegend.getFormSize());

        // space between the entries
        float stackSpace = Utils.convertDpToPixel(mLegend.getStackSpace());

        float yoffset = mLegend.getYOffset();
        float xoffset = mLegend.getXOffset();
        float originPosX = 0.f;

        switch (horizontalAlignment) {
            case LEFT:

                if (orientation == Legend.LegendOrientation.VERTICAL)
                    originPosX = xoffset;
                else
                    originPosX = mViewPortHandler.contentLeft() + xoffset;

                if (direction == Legend.LegendDirection.RIGHT_TO_LEFT)
                    originPosX += mLegend.mNeededWidth;

                break;

            case RIGHT:

                if (orientation == Legend.LegendOrientation.VERTICAL)
                    originPosX = mViewPortHandler.getChartWidth() - xoffset;
                else
                    originPosX = mViewPortHandler.contentRight() - xoffset;

                if (direction == Legend.LegendDirection.LEFT_TO_RIGHT)
                    originPosX -= mLegend.mNeededWidth;

                break;

            case CENTER:

                if (orientation == Legend.LegendOrientation.VERTICAL)
                    originPosX = mViewPortHandler.getChartWidth() / 2.f;
                else
                    originPosX = mViewPortHandler.contentLeft()
                            + mViewPortHandler.contentWidth() / 2.f;

                originPosX += (direction == Legend.LegendDirection.LEFT_TO_RIGHT
                        ? +xoffset
                        : -xoffset);

                // Horizontally layed out legends do the center offset on a line basis,
                // So here we offset the vertical ones only.
                if (orientation == Legend.LegendOrientation.VERTICAL) {
                    originPosX += (direction == Legend.LegendDirection.LEFT_TO_RIGHT
                            ? -mLegend.mNeededWidth / 2.0 + xoffset
                            : mLegend.mNeededWidth / 2.0 - xoffset);
                }

                break;
        }

        switch (orientation) {
            case HORIZONTAL: {

                List<FSize> calculatedLineSizes = mLegend.getCalculatedLineSizes();
                List<FSize> calculatedLabelSizes = mLegend.getCalculatedLabelSizes();
                List<Boolean> calculatedLabelBreakPoints = mLegend.getCalculatedLabelBreakPoints();

                float posX = originPosX;
                float posY = 0.f;

                switch (verticalAlignment) {
                    case TOP:
                        posY = yoffset;
                        break;

                    case BOTTOM:
                        posY = mViewPortHandler.getChartHeight() - yoffset - mLegend.mNeededHeight;
                        break;

                    case CENTER:
                        posY = (mViewPortHandler.getChartHeight() - mLegend.mNeededHeight) / 2.f + yoffset;
                        break;
                }

                int lineIndex = 0;

                for (int i = 0, count = entries.length; i < count; i++) {

                    LegendEntry e = entries[i];
                    boolean drawingForm = e.form != Legend.LegendForm.NONE;
                    float formSize = Float.isNaN(e.formSize) ? defaultFormSize : Utils.convertDpToPixel(e.formSize);

                    if (i < calculatedLabelBreakPoints.size() && calculatedLabelBreakPoints.get(i)) {
                        posX = originPosX;
                        posY += labelLineHeight + labelLineSpacing;
                    }

                    if (posX == originPosX &&
                            horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER &&
                            lineIndex < calculatedLineSizes.size()) {
                        posX += (direction == Legend.LegendDirection.RIGHT_TO_LEFT
                                ? calculatedLineSizes.get(lineIndex).width
                                : -calculatedLineSizes.get(lineIndex).width) / 2.f;
                        lineIndex++;
                    }

                    boolean isStacked = e.label == null; // grouped forms have null labels

                    if (drawingForm) {
                        if (direction == Legend.LegendDirection.RIGHT_TO_LEFT)
                            posX -= formSize;

                        drawForm(c, posX, posY + formYOffset, e, mLegend);

                        if (direction == Legend.LegendDirection.LEFT_TO_RIGHT)
                            posX += formSize;
                    }

                    if (!isStacked) {
                        if (drawingForm)
                            posX += direction == Legend.LegendDirection.RIGHT_TO_LEFT ? -formToTextSpace :
                                    formToTextSpace;

                        if (direction == Legend.LegendDirection.RIGHT_TO_LEFT)
                            posX -= calculatedLabelSizes.get(i).width;

                        drawLabel(c, posX, posY + labelLineHeight, e.label);

                        if (direction == Legend.LegendDirection.LEFT_TO_RIGHT)
                            posX += calculatedLabelSizes.get(i).width;

                        posX += direction == Legend.LegendDirection.RIGHT_TO_LEFT ? -xEntrySpace : xEntrySpace;
                    } else
                        posX += direction == Legend.LegendDirection.RIGHT_TO_LEFT ? -stackSpace : stackSpace;
                }

                break;
            }

            // 垂直
            case VERTICAL: {
                // contains the stacked legend size in pixels
                float stack = 0f;
                boolean wasStacked = false;
                float posY = 0.f;

                switch (verticalAlignment) {
                    case TOP:
                        posY = (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER
                                ? 0.f
                                : mViewPortHandler.contentTop());
                        posY += yoffset;
                        break;

                    case BOTTOM:
                        posY = (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER
                                ? mViewPortHandler.getChartHeight()
                                : mViewPortHandler.contentBottom());
                        posY -= mLegend.mNeededHeight + yoffset;
                        break;

                    case CENTER:
                        posY = mViewPortHandler.getChartHeight() / 2.f
                                - mLegend.mNeededHeight / 2.f
                                + mLegend.getYOffset();
                        break;
                }

                for (int i = 0; i < entries.length; i++) {

                    LegendEntry e = entries[i];
                    boolean drawingForm = e.form != Legend.LegendForm.NONE;
                    float formSize = Float.isNaN(e.formSize) ? defaultFormSize : Utils.convertDpToPixel(e.formSize);

                    float posX = originPosX;

                    if (drawingForm) {
                        if (direction == Legend.LegendDirection.LEFT_TO_RIGHT)
                            posX += stack;
                        else
                            posX -= formSize - stack;

                        // 画图形
                        drawForm(c, posX, posY + 60, e, mLegend);

                        if (direction == Legend.LegendDirection.LEFT_TO_RIGHT)
                            posX += formSize;
                    }

                    if (e.label != null) {

                        if (drawingForm && !wasStacked)
                            posX += direction == Legend.LegendDirection.LEFT_TO_RIGHT ? formToTextSpace
                                    : -formToTextSpace;
                        else if (wasStacked)
                            posX = originPosX;

                        if (direction == Legend.LegendDirection.RIGHT_TO_LEFT)
                            posX -= Utils.calcTextWidth(mLegendLabelPaint, e.label);

                        int baseY;
                        Paint.FontMetricsInt fontMetrics = mLegendLabelPaint.getFontMetricsInt();
                        int textHeight = fontMetrics.bottom - fontMetrics.top;
                        int top = (120 - textHeight) / 2;
                        baseY = top - fontMetrics.top;

                        // 画标签
                        if (!wasStacked) {
                            drawLabel(c, posX, posY +  baseY, e.label);
                        } else {
                            posY += labelLineHeight + labelLineSpacing;
                            drawLabel(c, posX, posY, e.label);
                        }

                        // make a step down
                        posY += 120;
                        System.out.println("labelLineHeight + labelLineSpacing = " + labelLineHeight + labelLineSpacing);
                        stack = 0f;
                    } else {
                        stack += formSize + stackSpace;
                        wasStacked = true;
                    }
                }

                break;

            }
        }
    }

    @Override
    protected void drawForm(Canvas c, float x, float y, LegendEntry entry, Legend legend) {
        super.drawForm(c, x, y, entry, legend);
        System.out.println("y = " + y);
    }
}
