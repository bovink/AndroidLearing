package com.bovink.androidlearing;

import retrofit2.Retrofit;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/5/26
 */

public class RetrofitUtils {

    public static Retrofit retrofit() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(HttpUtils.Host)
                .client(OkHttpUtils.okHttpClient());
        return builder.build();
    }

}
