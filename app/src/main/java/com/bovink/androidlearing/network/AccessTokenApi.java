package com.bovink.androidlearing.network;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author fox
 * @since 2017/12/19
 */

public interface AccessTokenApi {

    @POST("oauth/2.0/token")
    Single<Void> getAccessToken(@QueryMap Map<String, String> options);
}
