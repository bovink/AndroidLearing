package com.bovink.androidlearing.network;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author fox
 * @since 2017/12/19
 */

public interface IdentifyRestApi {

    @Headers("Content-Type: audio/pcm;rate=16000")
    @POST("server_api")
    Single<Void> identifyAudioFile(
            @QueryMap Map<String, String> params,
            @Body byte[] bytes
    );
}
