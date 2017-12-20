package com.bovink.androidlearing.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Retina975 on 17/5/8.
 * <p>
 * OkHttp工具类
 */

public class OkHttpUtils {

    private OkHttpUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }


    /**
     * @return 配置好的OkHttpClient
     */
    public static OkHttpClient okHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 配置Log
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor);

        return builder.build();
    }

    /**
     * @return 配置好的GsonConverterFactory
     */
    public static GsonConverterFactory gsonConverterFactory() {

        Gson gson = new GsonBuilder()
                .setLenient() //对JSON数据采取宽松模式
                .create();

        return GsonConverterFactory.create(gson);
    }
}
