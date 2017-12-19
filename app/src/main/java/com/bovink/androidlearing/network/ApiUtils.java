package com.bovink.androidlearing.network;

/**
 * @author fox
 * @since 2017/12/19
 */

public class ApiUtils {

    public static AccessTokenApi accessTokenApi;

    public static IdentifyRestApi identifyRestApi;

    public static AccessTokenApi getAccessTokenApi() {
        if (accessTokenApi == null) {

            accessTokenApi = RetrofitUtils.retrofit()
                    .create(AccessTokenApi.class);
        }

        return accessTokenApi;
    }

    public static IdentifyRestApi getIdentifyRestApi() {
        if (identifyRestApi == null) {

            identifyRestApi = RetrofitUtils.retrofit(HttpUtils.REST_HOST)
                    .create(IdentifyRestApi.class);
        }

        return identifyRestApi;
    }
}
