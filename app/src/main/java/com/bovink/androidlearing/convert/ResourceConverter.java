package com.bovink.androidlearing.convert;

import android.databinding.BindingConversion;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

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
}
