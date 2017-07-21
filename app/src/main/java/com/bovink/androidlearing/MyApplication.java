package com.bovink.androidlearing;

import android.app.Application;
import android.content.Context;

/**
 * @author Retina975
 * @since 2017/07/21
 */

public class MyApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
