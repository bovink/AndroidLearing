package com.bovink.androidlearing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
    }

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
}
