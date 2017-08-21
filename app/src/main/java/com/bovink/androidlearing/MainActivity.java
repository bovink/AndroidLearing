package com.bovink.androidlearing;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

public class MainActivity extends AppCompatActivity {

    private FFmpeg ffmpeg;
    private Context context;

    private String input;
    private String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        loadFFMpegBinary();

        input = Environment.getExternalStorageDirectory() + "/Pictures/" + "video.mp4";
        output = Environment.getExternalStorageDirectory() + "/Pictures/" + "video_compress.mp4";
        String cmd = "-i " + input + " -vcodec libx264 -crf 20 " + output;
        executeFFMpegBinary(cmd.split(" "));
    }

    private void loadFFMpegBinary() {
        ffmpeg = FFmpeg.getInstance(context);
        try {
            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {
                @Override
                public void onFailure() {
                    super.onFailure();
                    System.out.println("MainActivity.onFailure");
                }
            });
        } catch (FFmpegNotSupportedException e) {
            e.printStackTrace();
        }
    }

    private void executeFFMpegBinary(String[] cmd) {

        try {
            ffmpeg.execute(cmd, new ExecuteBinaryResponseHandler() {
                @Override
                public void onProgress(String message) {
                    super.onProgress(message);
                    System.out.println("message = " + message);
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    System.out.println("MainActivity.onFinish");
                }

                @Override
                public void onStart() {
                    super.onStart();
                    System.out.println("MainActivity.onStart");
                }

                @Override
                public void onFailure(String message) {
                    super.onFailure(message);
                    System.out.println("MainActivity.onFailure");
                }

                @Override
                public void onSuccess(String message) {
                    super.onSuccess(message);
                    System.out.println("MainActivity.onSuccess");
                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
        }

    }
}
