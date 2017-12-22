package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.TextView;
import android.widget.Toast;

import com.bovink.androidlearing.R;
import com.bovink.androidlearing.model.AudioIdentifyModel;
import com.bovink.androidlearing.model.RecognizeResultModel;
import com.bovink.androidlearing.network.ApiUtils;
import com.bovink.androidlearing.utils.DeviceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.tv_result)
    TextView resultTextView;

    private String testFileName;
    private CompositeDisposable compositeDisposable;

    private final String fileName = "test2.wav";

    private File audioFile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_audio);
        ButterKnife.bind(this);

        testFileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/" + fileName;
        audioFile = new File(testFileName);
        compositeDisposable = new CompositeDisposable();
    }

    @OnClick(R.id.btn_analyse)
    void startAnalyse() {

        try {
            identifyAudio2();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                .subscribeWith(new DisposableSingleObserver<RecognizeResultModel>() {
                    @Override
                    public void onSuccess(@NonNull RecognizeResultModel model) {

                        if (model.getErr_no() == 0) {

                            resultTextView.setText(model.getResult().get(0));
                        } else {

                            Toast.makeText(AudioIdentifyActivity.this, model.getErr_msg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                }));
    }

    private void identifyAudio2() throws IOException {
        AudioIdentifyModel model = new AudioIdentifyModel();
        model.setCuid(DeviceUtils.getIMEI(this));
        model.setToken("24.508115420aa19cd203cd12c3ffd6fdcb.2592000.1516242203.282335-7631707");
        model.setFormat("wav");
        model.setRate(16000);
        model.setChannel(1);
        model.setLen(audioFile.length());
        model.setSpeech(Base64.encodeToString(loadFile(audioFile), Base64.NO_WRAP));


        compositeDisposable.add(ApiUtils.getIdentifyRestApi()
                .identifyAudioFile(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<RecognizeResultModel>() {
                    @Override
                    public void onSuccess(@NonNull RecognizeResultModel model) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                }));
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            is.close();
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }
}
