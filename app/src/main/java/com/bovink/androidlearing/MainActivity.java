package com.bovink.androidlearing;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.MediaController;
import android.widget.RelativeLayout;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_video)
    TextureView videoTextureView;
    @BindView(R.id.activity_main)
    RelativeLayout mainRelativeLayout;

    private MediaPlayer mediaPlayer;

    String path;

    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        videoTextureView.setVisibility(View.GONE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        } else {
            videoTextureView.setVisibility(View.VISIBLE);
            videoTextureView.setSurfaceTextureListener(new MySurfaceTextureListener());
        }

        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/kekai/video.mp4";
        System.out.println("path = " + path);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean granted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

        if (granted) {

            System.out.println("granted = " + granted);
            videoTextureView.setVisibility(View.VISIBLE);
            videoTextureView.setSurfaceTextureListener(new MySurfaceTextureListener());
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initPlayer(SurfaceTexture surface) {

        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setSurface(new Surface(surface));


        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();

        mediaController = new MediaController(this);
        mediaController.setMediaPlayer(new MyMediaPlayerController());
        mediaController.setAnchorView(mainRelativeLayout);
        mediaController.setEnabled(true);
        mediaController.show();
    }

    private class MySurfaceTextureListener implements TextureView.SurfaceTextureListener {

        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            initPlayer(surface);

        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {

        }
    }

    @OnTouch(R.id.activity_main)
    boolean touchScreen() {

        mediaController.show();
        return false;
    }

    private class MyMediaPlayerController implements MediaController.MediaPlayerControl {

        @Override
        public void start() {

            mediaPlayer.start();
        }

        @Override
        public void pause() {

            mediaPlayer.pause();
        }

        @Override
        public int getDuration() {
            return mediaPlayer.getDuration();
        }

        @Override
        public int getCurrentPosition() {
            return mediaPlayer.getCurrentPosition();
        }

        @Override
        public void seekTo(int pos) {
            mediaPlayer.seekTo(pos);
        }

        @Override
        public boolean isPlaying() {
            return mediaPlayer.isPlaying();
        }

        @Override
        public int getBufferPercentage() {
            return 20;
        }

        @Override
        public boolean canPause() {
            return true;
        }

        @Override
        public boolean canSeekBackward() {
            return true;
        }

        @Override
        public boolean canSeekForward() {
            return true;
        }

        @Override
        public int getAudioSessionId() {
            return mediaPlayer.getAudioSessionId();
        }
    }
}
