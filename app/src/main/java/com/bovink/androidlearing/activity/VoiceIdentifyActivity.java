package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.asr.SpeechConstant;
import com.bovink.androidlearing.IRecogListener;
import com.bovink.androidlearing.PidBuilder;
import com.bovink.androidlearing.R;
import com.bovink.androidlearing.RecogEventAdapter;
import com.bovink.androidlearing.RecogResult;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author fox
 * @since 2017/12/20
 */

public class VoiceIdentifyActivity extends AppCompatActivity {
    private final String TAG = "VoiceIdentifyActivity";
    @BindView(R.id.tv_temporary)
    TextView temporaryTextView;
    @BindView(R.id.tv_final)
    TextView finalTextView;
    @BindView(R.id.tv_volume_percent)
    TextView volumePercentTextView;
    @BindView(R.id.tv_volume)
    TextView volumeTextView;

    private EventManager eventManager;

    private EventListener eventListener;

    private IRecogListener iRecogListener = new IRecogListener() {
        @Override
        public void onAsrReady() {


        }

        @Override
        public void onAsrBegin() {

            Log.e(TAG, "说话开始");
        }

        @Override
        public void onAsrEnd() {

            Log.e(TAG, "说话结束");
        }

        @Override
        public void onAsrPartialResult(String[] results, RecogResult recogResult) {

            Log.e(TAG, "持续识别中");
            temporaryTextView.setText(results[0]);
        }

        @Override
        public void onAsrFinalResult(String[] results, RecogResult recogResult) {

            finalTextView.setText(results[0]);
        }

        @Override
        public void onAsrFinish(RecogResult recogResult) {

            Log.e(TAG, "识别结束,如果是长语音可能会继续识别");
        }

        @Override
        public void onAsrFinishError(int errorCode, int subErrorCode, String errorMessage, String descMessage, RecogResult recogResult) {

            Log.e(TAG, "识别错误");
        }

        @Override
        public void onAsrLongFinish() {

            Log.e(TAG, "长语音识别结束");
        }

        @Override
        public void onAsrVolume(int volumePercent, int volume) {

            volumePercentTextView.setText(String.valueOf(volumePercent));
            volumeTextView.setText(String.valueOf(volume));
        }

        @Override
        public void onAsrAudio(byte[] data, int offset, int length) {

        }

        @Override
        public void onAsrExit() {

        }

        @Override
        public void onAsrOnlineNluResult(String nluResult) {

            Log.e(TAG, nluResult);
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
        setContentView(R.layout.activity_identify_voice);
        ButterKnife.bind(this);

        initEventManager();
    }


    @Override
    protected void onDestroy() {
        release();
        super.onDestroy();
    }

    @OnClick(R.id.btn_start)
    public void clickStart() {
        Map<String, Object> map = new HashMap<>();
        map.put(SpeechConstant.VAD_ENDPOINT_TIMEOUT, 0);// 开启长语音
//        InFileStream.reset();
//        try {
//            InFileStream.setInputStream(getAssets().open("8k.wav"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        map.put(SpeechConstant.IN_FILE, "#com.bovink.androidlearing.InFileStream.create16kStream()");
        PidBuilder pidBuilder = new PidBuilder();
        map = pidBuilder.addPidInfo(map);

        start(map);
    }

    @OnClick(R.id.btn_cancel)
    public void clickCancel() {
        cancel();
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
