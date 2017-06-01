package com.bovink.androidlearing.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * com.bovink.androidlearing.download
 *
 * @author bovink
 * @since 2017/6/1
 */

public class ProgressInterceptor implements Interceptor {

    private ProgressListener progressListener;

    public ProgressInterceptor(ProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                .build();
    }
}
