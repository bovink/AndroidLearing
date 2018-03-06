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
    public static void setSecond(CustomView view, String second) {

        view.setTime(view.getHour(),
                view.getMinute(),
                second + "b");
    }

    @BindingAdapter({"hour", "minute"})
    public static void loadTime(CustomView view, String hour, String minute) {

        view.setTime(hour + "a",
                minute + "a",
                view.getSecond());
    }

}
