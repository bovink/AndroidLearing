package com.bovink.androidlearing;

import com.bovink.androidlearing.download.ProgressListener;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/5/26
 */

public class RetrofitUtils {

    public static Retrofit retrofit(ProgressListener listener) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(HttpUtils.Host)
                .client(OkHttpUtils.okHttpClient(listener))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        return builder.build();
    }

}
