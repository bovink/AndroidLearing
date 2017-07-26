package com.bovink.androidlearing;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.videoview)
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        String file = Environment.getExternalStorageDirectory() + "/Video/video.mp4";
        videoView.setVideoPath(file);

        videoView.start();
        videoView.setMediaController(new MediaController(this));

    }
}
