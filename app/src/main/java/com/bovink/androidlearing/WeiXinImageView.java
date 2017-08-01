package com.bovink.androidlearing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * 仿微信图片缩略图
 *
 * @author Retina975
 * @since 2017/07/31
 */

public class WeiXinImageView extends AppCompatImageView {
    private Context context;
    /**
     * 图片缩放的最大尺寸
     */
    private int resize;
    /**
     * 图片箭头的方向
     * 默认为指向左侧
     */
    private Direction direction = Direction.LEFT;

    /**
     * 图片箭头的方向
     */
    public enum Direction {
        LEFT,
        RIGHT
    }


    public WeiXinImageView(Context context) {
        super(context);
        init(context);
    }

    public WeiXinImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WeiXinImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        resize = DensityUtils.dpToPx(context, 150);


    }


    /**
     * 设置图片的箭头方向
     *
     * @param direction
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void draw(Canvas canvas) {

        clipImage(canvas);
        super.draw(canvas);
        drawStroke(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (oldw == 0 && oldh == 0) {
            // 如果图片第一改变大小，则自动缩放

            // 缩放倍率
            final float time = ((float) h) / ((float) w);

            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {

                    ViewGroup.LayoutParams params = getLayoutParams();
                    if (time >= 1) {
                        // 图片为竖向图

                        params.width = (int) (resize / time);
                        params.height = resize;
                    } else {
                        // 图片为横向图

                        params.width = resize;
                        params.height = (int) (resize * time);
                    }
                    setLayoutParams(params);
                }
            });
        }
    }


    /**
     * 切图
     *
     * @param canvas
     */
    private void clipImage(Canvas canvas) {
        int space = 30;
        int distance;

        Path path = new Path();

        if (direction == Direction.LEFT) {
            // 箭头方向为左侧
            distance = 0;
        } else {
            // 箭头方向为右侧
            distance = space / 2;
        }

        // 裁剪圈圈
        path.addCircle(space - distance, space / 2, space / 2, Path.Direction.CW);
        path.addCircle(getWidth() - space / 2 - distance, space / 2, space / 2, Path.Direction.CW);
        path.addCircle(space - distance, getHeight() - space / 2, space / 2, Path.Direction.CW);
        path.addCircle(getWidth() - space / 2 - distance, getHeight() - space / 2, space / 2, Path.Direction.CW);

        // 裁剪矩形
        path.addRect(space / 2 - distance, space / 2, getWidth() - distance, getHeight() - space / 2, Path.Direction.CW);
        path.addRect(space - distance, 0, getWidth() - space / 2 - distance, getHeight(), Path.Direction.CW);

        // 裁剪三角
        if (direction == Direction.LEFT) {
            // 箭头指向左侧
            path.moveTo(space / 2, 40);
            path.lineTo(0, 55);
            path.lineTo(space / 2, 70);
        } else {
            // 箭头指向右侧
            path.moveTo(getWidth() - space / 2, 40);
            path.lineTo(getWidth(), 55);
            path.lineTo(getWidth() - space / 2, 70);
        }
        canvas.clipPath(path);
    }

    /**
     * 描边
     *
     * @param canvas
     */
    private void drawStroke(Canvas canvas) {
        int space = 30;
        int distance;

        Paint penPaint = new Paint();
        penPaint.setColor(Color.GRAY);
        penPaint.setAntiAlias(true);
        penPaint.setStyle(Paint.Style.STROKE);

        Path path = new Path();

        if (direction == Direction.LEFT) {

            path.moveTo(space / 2, 0);
            // 画左侧三角
            path.lineTo(space / 2, 40);
            path.lineTo(0, 55);
            path.lineTo(space / 2, 70);
            // 画直线描边
            path.lineTo(space / 2, getHeight());
            path.lineTo(getWidth(), getHeight());
            path.lineTo(getWidth(), 0);
            path.lineTo(space / 2, 0);

            distance = 0;
        } else {
            path.moveTo(getWidth() - space / 2, 0);
            // 画右侧三角
            path.lineTo(getWidth() - space / 2, 40);
            path.lineTo(getWidth(), 55);
            path.lineTo(getWidth() - space / 2, 70);
            // 画直线描边
            path.lineTo(getWidth() - space / 2, getHeight());
            path.lineTo(0, getHeight());
            path.lineTo(0, 0);
            path.lineTo(getWidth() - space / 2, 0);

            distance = space / 2;
        }


        // 画弧形描边
        RectF rectF = new RectF(space / 2 - distance, 0, space * 3 / 2 - distance, space);
        // 左上角弧形
        path.addArc(rectF, 180, 90);
        rectF = new RectF(space / 2 - distance, getHeight() - space, space * 3 / 2 - distance, getHeight());
        // 左下角弧形
        path.addArc(rectF, 90, 90);
        rectF = new RectF(getWidth() - space - distance, getHeight() - space, getWidth() - distance, getHeight());
        // 右下角弧形
        path.addArc(rectF, 0, 90);
        rectF = new RectF(getWidth() - space - distance, 0, getWidth() - distance, space);
        // 右上角弧形
        path.addArc(rectF, 270, 90);

        canvas.drawPath(path, penPaint);
    }
}
