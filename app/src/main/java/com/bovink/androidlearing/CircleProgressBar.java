package com.bovink.androidlearing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

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
    float angel = 0;
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
        paint.setColor(Color.parseColor("#66ccff"));
        paint.setStrokeWidth(spToPx(context, 4));
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

        setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startProgress = true;
                startProgress();
                if (onLongClickListener != null) {

                    onLongClickListener.onLongClick();
                }
                return true;
            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        startProgress = false;
                        break;
                }
                return false;
            }
        });
    }

    private OnVideoRecordedListener onVideoRecordedListener;

    public void setOnVideoRecordedListener(OnVideoRecordedListener onVideoRecordedListener) {
        this.onVideoRecordedListener = onVideoRecordedListener;
    }

    public interface OnVideoRecordedListener {
        void onVideoRecorded();
    }


    private OnLongClickListener onLongClickListener;

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public interface OnLongClickListener {
        void onLongClick();
    }

    private void startProgress() {
        Message msg = new Message();
        msg.what = 1001;
        msg.arg1 = 1;
        circleHandler.sendMessage(msg);
    }

    private void adjustCircleProgress() {
        Message msg = new Message();
        msg.what = 1001;
        msg.arg1 = 1;
        progressHandler.sendMessage(msg);
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
        canvas.drawArc(rectF, 270, angel, false, paint);

    }

    private void setProgress(float progress) {
        angel = progress * 360;
        invalidate();

    }

    private Handler circleHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1001) {
                // 开始绘制
                outRadius += dpToPx(context, 1);
                innerRadius -= dpToPx(context, 1.5f);
                invalidate();

                Message nextMsg;
                // 执行到10为止
                if (msg.arg1 != 10) {
                    nextMsg = obtainMessage();
                    nextMsg.what = msg.what;
                    nextMsg.arg1 = msg.arg1 + 1;
                    sendMessageDelayed(nextMsg, 10);
                } else {
                    if (startProgress) {

                        rectF.set(
                                (width / 2 - outRadius + spToPx(context, 2)),
                                (width / 2 - outRadius + spToPx(context, 2)),
                                (width / 2 + outRadius - spToPx(context, 2)),
                                (width / 2 + outRadius - spToPx(context, 2)));

                        adjustCircleProgress();
                    } else {

                        nextMsg = obtainMessage();
                        nextMsg.what = 1001;
                        nextMsg.arg1 = 1;
                        reserveHandler.sendMessage(nextMsg);
                    }
                }
            }
        }
    };

    private Handler reserveHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1001) {
                // 开始绘制
                outRadius -= dpToPx(context, 1);
                innerRadius += dpToPx(context, 1.5f);
                invalidate();

                Message nextMsg;
                // 执行到10为止
                if (msg.arg1 != 10) {

                    nextMsg = obtainMessage();
                    nextMsg.what = msg.what;
                    nextMsg.arg1 = msg.arg1 + 1;
                    sendMessageDelayed(nextMsg, 10);
                } else {
                    if (onVideoRecordedListener != null) {
                        onVideoRecordedListener.onVideoRecorded();
                        lastProgressTime = 0;
                    }
                }
            }
        }
    };

    private long lastProgressTime = 0;
    private long lastDelayTime = 25;

    private Handler progressHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            long now = System.currentTimeMillis();
            if (lastProgressTime != 0) {

                int beyondTime = (int) (now - lastProgressTime);
                lastDelayTime = 25 - beyondTime + lastDelayTime;
            }
            lastProgressTime = now;

            if (msg.what == 1001) {
                // 开始绘制
                float percent = (float) msg.arg1 / 405;
                setProgress(percent);

                Message nextMsg;
                // 执行1000次
                if (startProgress && msg.arg1 != 405) {
                    nextMsg = obtainMessage();
                    nextMsg.what = msg.what;
                    nextMsg.arg1 = msg.arg1 + 1;
                    sendMessageDelayed(nextMsg, lastDelayTime);
                } else {
                    angel = 0;

                    nextMsg = obtainMessage();
                    nextMsg.what = 1001;
                    nextMsg.arg1 = 1;
                    reserveHandler.sendMessage(nextMsg);
                }
            }
        }
    };

    private int dpToPx(Context context, float dp) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private int spToPx(Context context, float sp) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }
}
