package com.bovink.androidlearing.network;

import com.bovink.androidlearing.download.ProgressInterceptor;
import com.bovink.androidlearing.download.ProgressListener;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/5/26
 */

public class OkHttpUtils {

    public static OkHttpClient okHttpClient(ProgressListener listener) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        ProgressInterceptor progressInterceptor = new ProgressInterceptor(listener);
        builder.addInterceptor(httpLoggingInterceptor)
                .addNetworkInterceptor(progressInterceptor);
        return builder.build();
    }

    public static OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);
        return builder.build();
    }
}
