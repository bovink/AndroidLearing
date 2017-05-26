package com.bovink.androidlearing;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/5/26
 */

public interface ImageApi {

    @Streaming
    @GET("/javaweb01/img/{img}")
    Call<ResponseBody> downloadFile(@Path("img") String img);
}
