package com.bovink.androidlearing;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * @author fox
 * @since 2018/03/06
 */

public class Time extends BaseObservable {

    private String hour;
    private String minute;
    private String second;

    public Time(String hour, String minute, String second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    @Bindable
    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
        notifyPropertyChanged(BR.second);
    }
}
