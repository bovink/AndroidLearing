package com.bovink.androidlearing.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by Retina975 on 17/6/2.
 * <p>
 * 设备工具类
 * <p>
 * 获取设备型号、设备IMEI等信息
 */

public class DeviceUtils {

    private DeviceUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * 获取手机型号与手机制造商拼接字符串
     *
     * @return
     */
    public static String getDevice() {
        return android.os.Build.MODEL + android.os.Build.MANUFACTURER;
    }

    /**
     * 获取IMEI
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }
}
