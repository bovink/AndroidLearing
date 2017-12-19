package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.bovink.androidlearing.network.ApiUtils;

import org.json.JSONObject;

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

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_text)
    TextView textView;

    private CompositeDisposable compositeDisposable;

    private EventManager eventManager;

    private EventListener eventListener;

    private IRecogListener iRecogListener = new IRecogListener() {
        @Override
        public void onAsrReady() {


        }

        @Override
        public void onAsrBegin() {

        }

        @Override
        public void onAsrEnd() {

        }

        @Override
        public void onAsrPartialResult(String[] results, RecogResult recogResult) {

            textView.setText(results[0]);
        }

        @Override
        public void onAsrFinalResult(String[] results, RecogResult recogResult) {

            textView.setText(results[0]);
        }

        @Override
        public void onAsrFinish(RecogResult recogResult) {

        }

        @Override
        public void onAsrFinishError(int errorCode, int subErrorCode, String errorMessage, String descMessage, RecogResult recogResult) {

        }

        @Override
        public void onAsrLongFinish() {

        }

        @Override
        public void onAsrVolume(int volumePercent, int volume) {

        }

        @Override
        public void onAsrAudio(byte[] data, int offset, int length) {

        }

        @Override
        public void onAsrExit() {

        }

        @Override
        public void onAsrOnlineNluResult(String nluResult) {

        }

        @Override
        public void onOfflineLoaded() {

        }

        @Override
        public void onOfflineUnLoaded() {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        compositeDisposable = new CompositeDisposable();

        initEventManager();
        getAccessToken();
    }

    @Override
    protected void onDestroy() {
        release();
        super.onDestroy();
    }

    @OnClick(R.id.btn_click)
    void onClick() {
        Map<String, Object> map = new HashMap<>();
        PidBuilder pidBuilder = new PidBuilder();
        map = pidBuilder.addPidInfo(map);

        start(map);
    }

    private void initEventManager() {
        eventManager = EventManagerFactory.create(this, "asr");
        eventListener = new RecogEventAdapter(iRecogListener);
        eventManager.registerListener(eventListener);

    }

    private void start(Map<String, Object> params) {

        String json = new JSONObject(params).toString();
        System.out.println(json);
        eventManager.send(SpeechConstant.ASR_START, json, null, 0, 0);
    }

    private void stop() {
        eventManager.send(SpeechConstant.ASR_STOP, "{}", null, 0, 0);

    }

    private void cancel() {

        if (eventManager != null) {
            eventManager.send(SpeechConstant.ASR_CANCEL, "{}", null, 0, 0);
        }
    }

    public void release() {

        if (eventManager == null) {
            return;
        }

        cancel();
        eventManager.unregisterListener(eventListener);
        eventManager = null;
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
}
