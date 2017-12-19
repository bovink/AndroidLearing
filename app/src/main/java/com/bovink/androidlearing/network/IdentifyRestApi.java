package com.bovink.androidlearing.network;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author fox
 * @since 2017/12/19
 */

public interface IdentifyRestApi {

    @POST("server_api")
    Single<Void> identifyAudioFile(
            @QueryMap Map<String, String> params,
            @Body byte[] bytes,
            @Header("format") String format,
            @Header("rate") int rate);
}
