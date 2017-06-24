package com.bovink.androidlearing.network;

import com.bovink.androidlearing.download.ProgressListener;
import com.bovink.androidlearing.network.api.CityApi;
import com.bovink.androidlearing.network.api.ImageApi;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/5/26
 */

public class ApiUtils {

    private static ImageApi imageApi;
    private static CityApi cityApi;

    public static ImageApi getImageApi(ProgressListener listener) {
        if (imageApi == null) {
            imageApi = RetrofitUtils.retrofit(listener)
                    .create(ImageApi.class);
        }

        return imageApi;
    }

    public static CityApi getCityApi() {
        if (cityApi == null) {
            cityApi = RetrofitUtils.retrofit()
                    .create(CityApi.class);
        }

        return cityApi;
    }
}
