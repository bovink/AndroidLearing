package com.bovink.androidlearing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Retina975
 * @since 2017/08/25
 */

public class CircleProgressBar extends AppCompatImageView {

    int width;
    int height;

    Paint paint;
    Paint circlePaint;
    RectF rectF;
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

        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.parseColor("#e9e9e9"));
        circlePaint.setStyle(Paint.Style.FILL);

        rectF = new RectF();

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.intervalRange(0, 100000, 0, 10, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(@NonNull Long aLong) throws Exception {
//                                setRadius((int) (aLong * 100 / 100));

                                setProgress((int) (aLong / 10));
                            }
                        });

            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        rectF.set(5, 5, width - 5, height - 5);
        System.out.println("width = " + width);
        System.out.println("height = " + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//        canvas.drawCircle(width / 2, height / 2, radius + 30, paint);
        canvas.drawCircle(width / 2, height / 2, radius, circlePaint);
        canvas.drawArc(rectF, 270, angel, false, paint);

    }

    float radius = 80;

    public void setRadius(int radius) {
        this.radius = (float) (radius) * 100 / 100 * (width / 2 - 10) / 100;
        invalidate();
    }

    public void setProgress(int progress) {
        angel = (float) (progress) * 100 / 100 * 360 / 100;
        System.out.println("angel = " + angel);
        invalidate();

    }
}
