package com.bovink.androidlearing.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Retina975 on 17/5/8.
 *
 * Retrofit工具类
 */

public class RetrofitUtils {

    private RetrofitUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    public static Retrofit retrofit() {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(HttpUtils.HOST)
                .client(OkHttpUtils.okHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(OkHttpUtils.gsonConverterFactory());

        return builder.build();
    }

    public static Retrofit retrofit(String baseUrl) {

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpUtils.okHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(OkHttpUtils.gsonConverterFactory());

        return builder.build();
    }
}
