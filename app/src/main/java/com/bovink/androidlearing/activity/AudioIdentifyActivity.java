package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.network.ApiUtils;
import com.bovink.androidlearing.utils.DeviceUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author fox
 * @since 2017/12/20
 */

public class AudioIdentifyActivity extends AppCompatActivity {

    private String testFileName;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testFileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/16k.pcm";
        compositeDisposable = new CompositeDisposable();
    }

    private void getAccessToken() {
        // AccessToken
        // 有效期12-19至1-19
        // 24.508115420aa19cd203cd12c3ffd6fdcb.2592000.1516242203.282335-7631707


        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credentials");
        params.put("client_id", "rrfejFB86wfKqyGRITsPYXA4");
        params.put("client_secret", "7la28gRMgv2NAybIWb9yim8ewf61DG6f");

        compositeDisposable.add(ApiUtils.getAccessTokenApi()
                .getAccessToken(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void aVoid) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                }));
    }

    private void identifyAudio() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("cuid", DeviceUtils.getIMEI(this));
        params.put("token", "24.508115420aa19cd203cd12c3ffd6fdcb.2592000.1516242203.282335-7631707");

        File file = new File(testFileName);
        RequestBody body = RequestBody.create(MediaType.parse("audio/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("", file.getName(), body);

        compositeDisposable.add(ApiUtils.getIdentifyRestApi()
                .identifyAudioFile(params, part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void aVoid) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                }));
    }
}
