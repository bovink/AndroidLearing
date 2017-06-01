package com.bovink.androidlearing;

import com.bovink.androidlearing.download.ProgressListener;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/5/26
 */

public class ApiUtils {

    private static ImageApi imageApi;

    public static ImageApi getImageApi(ProgressListener listener) {
        if (imageApi == null) {
            imageApi = RetrofitUtils.retrofit(listener)
                    .create(ImageApi.class);
        }

        return imageApi;
    }
}
