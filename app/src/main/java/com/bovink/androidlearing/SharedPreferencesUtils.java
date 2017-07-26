package com.bovink.androidlearing;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Retina975
 * @since 2017/07/21
 */

public class SharedPreferencesUtils {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    private SharedPreferencesUtils(Context context, String name) {

        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static SharedPreferencesUtils getInstance(Context context, String name) {
        return new SharedPreferencesUtils(context, name);
    }

    public SharedPreferencesUtils put(String key, String value) {
        editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
        return this;
    }

    public SharedPreferencesUtils put(String key, Boolean value) {
        editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
        return this;
    }

    public SharedPreferencesUtils put(String key, Float value) {
        editor = sp.edit();
        editor.putFloat(key, value);
        editor.apply();
        return this;
    }

    public SharedPreferencesUtils put(String key, int value) {
        editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
        return this;
    }

    public SharedPreferencesUtils put(String key, long value) {
        editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
        return this;
    }

    public SharedPreferencesUtils put(String key, Set<String> value) {
        editor = sp.edit();
        editor.putStringSet(key, value);
        editor.apply();
        return this;
    }

    public String getString(String key) {
        return sp.getString(key, "defalut");
    }

    public Boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public Float getFloat(String key) {
        return sp.getFloat(key, -1.0f);
    }

    public Integer getInt(String key) {
        return sp.getInt(key, -1);
    }

    public Long getLong(String key) {
        return sp.getLong(key, -1);
    }

    public Set<String> getStringSet(String key) {
        Set<String> strings = sp.getStringSet(key, null);
        if (strings == null) {
            strings = new HashSet<>();
        }

        return strings;
    }

    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    public Boolean contains(String key) {
        return sp.contains(key);
    }

    public void clear() {
        editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    public void remove(String key) {
        editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }
}
