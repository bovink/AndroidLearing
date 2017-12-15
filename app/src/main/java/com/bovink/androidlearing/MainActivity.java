package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;

import org.json.JSONObject;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EventManager eventManager;

    private EventListener eventListener = new EventListener() {
        @Override
        public void onEvent(String name, String params, byte[] data, int offset, int length) {
            System.out.println("name = " + name);
            System.out.println("params = " + params);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        release();
        super.onDestroy();
    }

    private void initEventManager() {
        eventManager = EventManagerFactory.create(this, "asr");
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
