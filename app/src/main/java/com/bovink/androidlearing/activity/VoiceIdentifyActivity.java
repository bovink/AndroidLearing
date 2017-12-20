package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.bovink.androidlearing.IRecogListener;
import com.bovink.androidlearing.RecogEventAdapter;
import com.bovink.androidlearing.RecogResult;

import org.json.JSONObject;

import java.util.Map;

/**
 * @author fox
 * @since 2017/12/20
 */

public class VoiceIdentifyActivity extends AppCompatActivity {

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

        }

        @Override
        public void onAsrFinalResult(String[] results, RecogResult recogResult) {

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onDestroy() {
        release();
        super.onDestroy();
    }

    public void clickStart() {
        //        Map<String, Object> map = new HashMap<>();
//        PidBuilder pidBuilder = new PidBuilder();
//        map = pidBuilder.addPidInfo(map);
//
//        start(map);
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
}
