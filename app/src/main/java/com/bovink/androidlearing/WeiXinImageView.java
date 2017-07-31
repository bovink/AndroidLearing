package com.bovink.androidlearing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * @author Retina975
 * @since 2017/07/31
 */

public class WeiXinImageView extends AppCompatImageView {

    public WeiXinImageView(Context context) {
        super(context);
    }

    public WeiXinImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeiXinImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {

        init(canvas);
        super.draw(canvas);
        drawStroke(canvas);
    }

    /**
     * 切图
     *
     * @param canvas
     */
    private void init(Canvas canvas) {
        int space = 50;

        Paint penPaint = new Paint();
        penPaint.setColor(Color.BLACK);
        penPaint.setAntiAlias(true);
        penPaint.setStyle(Paint.Style.STROKE);

        Path path = new Path();

        path.addCircle(space, space / 2, space / 2, Path.Direction.CW);
        path.addCircle(getWidth() - space / 2, space / 2, space / 2, Path.Direction.CW);
        path.addCircle(space, getHeight() - space / 2, space / 2, Path.Direction.CW);
        path.addCircle(getWidth() - space / 2, getHeight() - space / 2, space / 2, Path.Direction.CW);

        path.addRect(space / 2, space / 2, getWidth(), getHeight() - space / 2, Path.Direction.CW);
        path.addRect(space, 0, getWidth() - space / 2, getHeight(), Path.Direction.CW);
        path.moveTo(space / 2, space);
        path.lineTo(0, space * 3 / 2);
        path.lineTo(space / 2, space * 2);
        canvas.clipPath(path);

    }

    /**
     * 画线
     *
     * @param canvas
     */
    private void drawStroke(Canvas canvas) {
        int space = 50;

        Paint penPaint = new Paint();
        penPaint.setColor(Color.GREEN);
        penPaint.setAntiAlias(true);
        penPaint.setStyle(Paint.Style.STROKE);

        Path path = new Path();
        path.moveTo(space / 2, 0);
        path.lineTo(space / 2, space);
        path.lineTo(0, space * 3 / 2);
        path.lineTo(space / 2, space * 2);
        path.lineTo(space / 2, getHeight());
        path.lineTo(getWidth(), getHeight());
        path.lineTo(getWidth(), 0);
        path.lineTo(space / 2, 0);


        RectF rectF = new RectF(space / 2, 0, space * 3 / 2, space);
        path.addArc(rectF, 180, 90);
        rectF = new RectF(space / 2, getHeight() - space, space * 3 / 2, getHeight());
        path.addArc(rectF, 90, 90);
        rectF = new RectF(getWidth() - space, getHeight() - space, getWidth(), getHeight());
        path.addArc(rectF, 0, 90);
        rectF = new RectF(getWidth() - space, 0, getWidth(), space);
        path.addArc(rectF, 270, 90);

        canvas.drawPath(path, penPaint);

    }
}
