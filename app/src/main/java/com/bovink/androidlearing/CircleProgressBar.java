package com.bovink.androidlearing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Retina975
 * @since 2017/08/25
 */

public class CircleProgressBar extends AppCompatImageView {

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
    float outRadius = 130;
    /**
     *
     */
    float innerRadius = 100;
    /**
     *
     */
    RectF rectF;
    /**
     * 角度
     */
    float angel = 0;

    public CircleProgressBar(Context context) {
        super(context);
        init();
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#66ccff"));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

        outCirclePaint = new Paint();
        outCirclePaint.setAntiAlias(true);
        outCirclePaint.setColor(Color.parseColor("#e9e9e9"));
        outCirclePaint.setStyle(Paint.Style.FILL);

        innerCirclePaint = new Paint();
        innerCirclePaint.setAntiAlias(true);
        innerCirclePaint.setColor(Color.parseColor("#ffffff"));
        innerCirclePaint.setStyle(Paint.Style.FILL);

        rectF = new RectF();

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                startProgress();
                return false;
            }
        });
    }

    private void startProgress() {

        Observable.intervalRange(0, 10, 0, 10, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        outRadius += 3.5;
                        innerRadius -= 3.5;
                        invalidate();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        rectF.set(width / 2 - outRadius - 5, width / 2 - outRadius - 5, width / 2 + outRadius + 5, width / 2 + outRadius + 5);
                        adjustCircleProgress();
                    }
                });
    }

    private void adjustCircleProgress() {
        Observable.intervalRange(1, 1000, 0, 10, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        float percent = (float) aLong / 1000;
                        setProgress(percent);
                    }
                });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawCircle(width / 2, height / 2, outRadius, outCirclePaint);
        canvas.drawCircle(width / 2, height / 2, innerRadius, innerCirclePaint);
        canvas.drawArc(rectF, 270, angel, false, paint);

    }

    public void setProgress(float progress) {
        angel = progress * 360;
        invalidate();

    }
}
