package com.bovink.androidlearing.convert;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.bovink.androidlearing.CustomView;

/**
 * @author fox
 * @since 2018/03/06
 */

public class ResourceConverter {
    @BindingConversion
    public static ColorDrawable convertColorToDrawable(String color) {
        if (color.equals("198")) {

            return new ColorDrawable(Color.parseColor("#66ccff"));
        } else if (color.equals("199")) {

            return new ColorDrawable(Color.parseColor("#cccccc"));
        }

        return null;
    }

    @BindingAdapter("hour")
    public static void setHour(CustomView view, String hour) {

        view.setTime(hour,
                view.getMinute(),
                view.getSecond());
    }

    @BindingAdapter("minute")
    public static void setMinute(CustomView view, String minute) {

        view.setTime(view.getHour(),
                minute,
                view.getSecond());
    }

    @BindingAdapter("second")
    public static void setSecond(CustomView view, String oldSecond, String newSecond) {
        if (oldSecond == null) {

            view.setTime(view.getHour(),
                    view.getMinute(),
                    newSecond + "a");
        } else {
            if (!oldSecond.equals(newSecond)) {

                view.setTime(view.getHour(),
                        view.getMinute(),
                        newSecond + "b");
            }
        }
    }

    @BindingAdapter("onTimeChanged")
    public static void setOnTimeChangedListener(CustomView view, CustomView.OnTimeChangedListener oldValue, CustomView.OnTimeChangedListener newValue) {
        System.out.println("ResourceConverter.setOnTimeChangedListener");

        if (oldValue != null) {

            view.removeOnTimeChangedListener(oldValue);
        }
        if (newValue != null) {

            view.addOnTimeChangedListener(newValue);
        }
    }

}
