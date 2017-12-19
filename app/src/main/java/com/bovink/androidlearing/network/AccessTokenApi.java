package com.bovink.androidlearing.network;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author fox
 * @since 2017/12/19
 */

public interface AccessTokenApi {

    @GET("homePage/getHomePage.do")
    Single<Void> getHomePage(@QueryMap Map<String, String> options);
}
