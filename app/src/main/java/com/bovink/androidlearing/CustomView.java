package com.bovink.androidlearing;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @author fox
 * @since 2018/03/06
 */

public class CustomView extends AppCompatTextView {

    private String time = "00:00:00";

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public String getTime() {
        return time;
    }

    public void setTime(String hour, String minute, String second) {

        time = hour + ":" + minute + ":" + second;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHour() {

        return time.substring(0, 2);
    }

    public String getMinute() {

        return time.substring(3, 5);
    }

    public String getSecond() {

        return time.substring(6, 8);
    }
}
