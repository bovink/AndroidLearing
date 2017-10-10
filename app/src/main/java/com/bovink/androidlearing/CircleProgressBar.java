package com.bovink.androidlearing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * @author Retina975
 * @since 2017/08/25
 */

public class CircleProgressBar extends AppCompatImageView {

    private Context context;
    /**
     * View的宽度
     */
    int width;
    /**
     * View的高度
     */
    int height;
    /**
     * 环的画笔
     */
    Paint paint;
    /**
     * 环的底部画笔
     */
    Paint circleBottomPaint;
    /**
     * 外园的画笔
     */
    Paint outCirclePaint;
    /**
     * 内圆的画笔
     */
    Paint innerCirclePaint;
    /**
     *
     */
    float outRadius;
    /**
     *
     */
    float innerRadius;
    /**
     *
     */
    RectF rectF;
    /**
     * 角度
     */
    float angel = 180;
    /**
     * 开始进度
     */
    private boolean startProgress;

    public CircleProgressBar(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#F9715D"));
        paint.setStrokeWidth(spToPx(context, 4));
        paint.setStyle(Paint.Style.STROKE);

        circleBottomPaint = new Paint();
        circleBottomPaint.setAntiAlias(true);
        circleBottomPaint.setColor(Color.parseColor("#CBCBCB"));
        circleBottomPaint.setStrokeWidth(spToPx(context, 1));
        circleBottomPaint.setStyle(Paint.Style.STROKE);

        outCirclePaint = new Paint();
        outCirclePaint.setAntiAlias(true);
        outCirclePaint.setColor(Color.parseColor("#FFFFFF"));
        outCirclePaint.setStyle(Paint.Style.FILL);

        innerCirclePaint = new Paint();
        innerCirclePaint.setAntiAlias(true);
        innerCirclePaint.setColor(Color.parseColor("#E9E9E9"));
        innerCirclePaint.setStyle(Paint.Style.FILL);

        rectF = new RectF();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        outRadius = width / 2 - dpToPx(context, 15);
        innerRadius = outRadius - dpToPx(context, 10);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawCircle(width / 2, height / 2, outRadius, outCirclePaint);
        canvas.drawCircle(width / 2, height / 2, innerRadius, innerCirclePaint);


        rectF.set(
                (width / 2 - outRadius + spToPx(context, 1)),
                (width / 2 - outRadius + spToPx(context, 1)),
                (width / 2 + outRadius - spToPx(context, 1)),
                (width / 2 + outRadius - spToPx(context, 1)));
        canvas.drawArc(rectF, 270, 360, false, circleBottomPaint);
        canvas.drawArc(rectF, 270, angel, false, paint);

    }

    private void setProgress(float progress) {
        angel = progress * 360;
        invalidate();

    }

    private int dpToPx(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private int spToPx(Context context, float sp) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }
}
