package com.bovink.androidlearing;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/5/26
 */

public class ApiUtils {

    private static ImageApi imageApi;

    public static ImageApi getImageApi() {
        if (imageApi == null) {
            imageApi = RetrofitUtils.retrofit()
                    .create(ImageApi.class);
        }

        return imageApi;
    }
}
