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
    }

    private void init(Canvas canvas) {
        int space = 50;

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        Path path = new Path();

        RectF rectF = new RectF();

        // 左上角
        rectF.left = space / 2;
        rectF.top = 1;
        rectF.right = space / 2 + space;
        rectF.bottom = space + 1;
        path.addArc(rectF, 180, 90);

        path.moveTo(space / 2, space / 2);
        path.lineTo(space / 2, space);
        path.lineTo(0, space * 3 / 2);
        path.lineTo(space / 2, space * 2);
        path.lineTo(space / 2, getHeight() - space / 2);

        // 左下角
        rectF.left = space / 2;
        rectF.top = getHeight() - space - 1;
        rectF.right = space / 2 + space;
        rectF.bottom = getHeight() - 1;
        path.addArc(rectF, 90, 90);
        path.moveTo(space, getHeight() - 1);
        path.lineTo(getWidth() - space / 2, getHeight() - 1);

        // 右下角
        rectF.left = getWidth() - space - 1;
        rectF.top = getHeight() - space - 1;
        rectF.right = getWidth() - 1;
        rectF.bottom = getHeight() - 1;
        path.addArc(rectF, 0, 90);
        path.moveTo(getWidth() - 1, getHeight() - space / 2);
        path.lineTo(getWidth() - 1, space / 2);

        // 右上角
        rectF.left = getWidth() - space - 1;
        rectF.top = 1;
        rectF.right = getWidth() - 1;
        rectF.bottom = space + 1;
        path.addArc(rectF, 270, 90);
        path.moveTo(space, 1);
        path.lineTo(getWidth() - space / 2, 1);
        path.close();

        canvas.clipPath(path);
//        canvas.drawPath(path, paint);
    }
}
