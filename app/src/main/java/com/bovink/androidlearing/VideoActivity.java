package com.bovink.androidlearing;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Retina975
 * @since 2017/08/18
 */

@SuppressWarnings("deprecation")
public class VideoActivity extends AppCompatActivity {

    @BindView(R.id.view_texture)
    TextureView textureView;

    private Camera camera;
    private MediaRecorder recorder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            new Thread() {
                @Override
                public void run() {
                    initCamera();
                }
            }.start();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean result = false;
        if (requestCode == 1) {
            result = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
        if (!result) {
            finish();
        } else {

            initCamera();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseCamera();
    }

    @OnClick(R.id.btn_record)
    void record() {
        startRecord();
    }

    @OnClick(R.id.btn_stop)
    void stopRecord() {
        releaseRecorder();
        releaseCamera();
        initCamera();
    }

    private void initCamera() {
        camera = Camera.open();

        camera.setDisplayOrientation(90);

        try {
            camera.setPreviewTexture(textureView.getSurfaceTexture());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Camera.Parameters parameters = camera.getParameters();

        parameters.setRotation(90);
        parameters.setPreviewSize(1920, 1080);
        parameters.setPictureSize(1920, 1080);

        List<String> focusList = parameters.getSupportedFocusModes();
        if (focusList.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        } else if (focusList.contains(Camera.Parameters.FOCUS_MODE_FIXED)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_FIXED);
        } else if (focusList.contains(Camera.Parameters.FOCUS_MODE_INFINITY)) {
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
        } else {
            parameters.setFocusMode(focusList.get(0));
        }

        camera.setParameters(parameters);
        camera.startPreview();

    }

    private void startRecord() {
        recorder = new MediaRecorder();

        camera.unlock();
        recorder.setCamera(camera);
        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);

        File file = new File(Environment.getExternalStorageDirectory() + "/Pictures/" + "video.mp4");
        recorder.setOutputFile(file.getPath());
        recorder.setVideoSize(1920, 1080);
        // 解决花屏问题
        recorder.setVideoEncodingBitRate(3 * 1024 * 1024);
        recorder.setVideoFrameRate(30);

        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        recorder.start();

    }

    private void releaseRecorder() {

        recorder.stop();

        recorder.reset();
        recorder.release();
        recorder = null;
        camera.lock();
    }

    private void releaseCamera() {
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }
}
