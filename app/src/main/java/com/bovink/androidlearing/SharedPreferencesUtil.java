package com.bovink.androidlearing;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * @author Retina975
 * @since 2017/07/21
 */

public class SharedPreferencesUtil {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private SharedPreferencesUtil(Context context, String name) {

        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesUtil getInstance(Context context, String name) {
        return new SharedPreferencesUtil(context, name);
    }

    public SharedPreferencesUtil put(String key, String value) {
        editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
        return this;
    }

    public SharedPreferencesUtil put(String key, Boolean value) {
        editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
        return this;
    }

    public SharedPreferencesUtil put(String key, Float value) {
        editor = sp.edit();
        editor.putFloat(key, value);
        editor.apply();
        return this;
    }

    public SharedPreferencesUtil put(String key, int value) {
        editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
        return this;
    }

    public SharedPreferencesUtil put(String key, long value) {
        editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
        return this;
    }

    public SharedPreferencesUtil put(String key, Set<String> value) {
        editor = sp.edit();
        editor.putStringSet(key, value);
        editor.apply();
        return this;
    }

}
