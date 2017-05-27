package com.bovink.androidlearing;

import android.util.Log;

/**
 * Log工具类
 *
 * @author bovink
 * @since 2016/6/15
 */
public class LogUtils {
    /**
     * Log开关
     */
    public static boolean isDebug = true;
    /**
     * 默认tag
     */
    private final static String TAG = "LogUtils";

    private LogUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * info
     *
     * @param tag tag
     * @param msg msg
     */
    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    /**
     * info
     *
     * @param msg msg
     */
    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    /**
     * debug
     *
     * @param tag tag
     * @param msg msg
     */
    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }

    /**
     * debug
     *
     * @param msg msg
     */
    public static void d(String msg) {
        if (isDebug) {
            Log.d(TAG, msg);
        }
    }

    /**
     * error
     *
     * @param tag tag
     * @param msg msg
     */
    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

    /**
     * error
     *
     * @param msg msg
     */
    public static void e(String msg) {
        if (isDebug) {
            Log.e(TAG, msg);
        }
    }

    /**
     * verbose
     *
     * @param tag tag
     * @param msg msg
     */
    public static void v(String tag, String msg) {
        if (isDebug) {
            Log.v(tag, msg);
        }
    }

    /**
     * verbose
     *
     * @param msg msg
     */
    public static void v(String msg) {
        if (isDebug) {
            Log.v(TAG, msg);
        }
    }

    /**
     * warn
     *
     * @param tag tag
     * @param msg msg
     */
    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg);
        }
    }

    /**
     * warn
     *
     * @param msg msg
     */
    public static void w(String msg) {
        if (isDebug) {
            Log.w(TAG, msg);
        }
    }
}
