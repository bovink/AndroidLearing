package com.bovink.androidlearing;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.TextureView;
import android.view.WindowManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.view_texture)
    TextureView textureView;

    Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }


    @OnClick(R.id.btn_record)
    void record() {


        openCamera();
    }

    private void openCamera() {

        camera = Camera.open();

        camera.setDisplayOrientation(90);

        try {
            camera.setPreviewTexture(textureView.getSurfaceTexture());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Camera.Parameters parameters = camera.getParameters();
        // 影响takePiction输出的图片的角度
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

//        String originAspectRatio = getAspectRatio(textureView.getHeight(), textureView.getWidth());
//        System.out.println("originAspectRatio = " + originAspectRatio);
//        System.out.println("textureView.getWidth() = " + textureView.getWidth());
//        System.out.println("textureView.getHeight() = " + textureView.getHeight());
//
//        List<Camera.Size> previewSizes = new ArrayList<>();
//        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
//        for (Camera.Size size : sizes) {
//            String previewAspectRatio = getAspectRatio(size.width, size.height);
//            if (previewAspectRatio.equals(originAspectRatio)) {
//                System.out.println("previewAspectRatio = " + previewAspectRatio);
//                System.out.println("size.width = " + size.width);
//                System.out.println("size.height = " + size.height);
//            }
//        }

        camera.setParameters(parameters);
        camera.startPreview();
    }

    @OnClick(R.id.btn_capture)
    void capture() {
        System.out.println("MainActivity.capture");

        takePicture();
    }

    private void takePicture() {

        camera.takePicture(null, null, null, new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(final byte[] data, Camera camera) {
                new Thread() {
                    @Override
                    public void run() {
                        File file = new File(Environment.getExternalStorageDirectory() + "/Pictures/" + "MainActivity.jpg");
                        System.out.println("file.getAbsolutePath() = " + file.getAbsolutePath());
                        OutputStream os = null;
                        try {
                            os = new FileOutputStream(file);
                            os.write(data);
                            os.close();
                        } catch (IOException e) {
                            Log.w("MainActivity", "Cannot write to " + file, e);
                        } finally {
                            if (os != null) {
                                try {
                                    os.close();
                                } catch (IOException e) {
                                    // Ignore
                                }
                            }
                        }
                    }
                }.start();
            }
        });
        camera.startPreview();
    }


    private String getAspectRatio(int width, int height) {
        int x = width;
        int y = height;
        int gcd = gcd(x, y);
        x /= gcd;
        y /= gcd;
        return x + ":" + y;
    }

    /**
     * 获取最小公约数
     *
     * @param a
     * @param b
     * @return
     */
    private static int gcd(int a, int b) {
        while (b != 0) {
            int c = b;
            b = a % b;
            a = c;
        }
        return a;
    }
}
