package com.bovink.androidlearing;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;

/**
 * 照相
 *
 * @author Retina975
 * @since 2017/08/18
 */

@SuppressWarnings("deprecation")
public class PictureActivity extends AppCompatActivity {
    @BindView(R.id.view_texture)
    TextureView textureView;
    @BindView(R.id.rv_preview_size)
    RecyclerView previewSizeRecyclerView;

    private Camera camera;
    private Camera.Parameters parameters;
    private List<Camera.Size> previewSizes;
    private PreviewSizeRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        } else {

            Observable.just(1)
                    .delay(100, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DefaultObserver<Integer>() {
                        @Override
                        public void onNext(@io.reactivex.annotations.NonNull Integer integer) {

                            initCamera();
                        }

                        @Override
                        public void onError(@io.reactivex.annotations.NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                            previewSizeRecyclerView.setLayoutManager(new LinearLayoutManager(PictureActivity.this));
                            previewSizeRecyclerView.setAdapter(adapter);

                        }
                    });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean permissionAccepted = false;
        if (requestCode == 1) {
            permissionAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
        if (permissionAccepted) {
            initCamera();
        } else {
            finish();
        }
    }

    @OnClick(R.id.btn_record)
    void record() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (camera != null) {
            camera.release();
            camera = null;
        }
    }

    private void initCamera() {

        camera = Camera.open();

        camera.setDisplayOrientation(90);

        try {
            camera.setPreviewTexture(textureView.getSurfaceTexture());
        } catch (IOException e) {
            e.printStackTrace();
        }

        parameters = camera.getParameters();
        // 影响takePiction输出的图片的角度
        parameters.setRotation(90);
        obtainCameraPreviewSize();
        int width = previewSizes.get(0).width;
        int height = previewSizes.get(0).height;
        System.out.println("width = " + width);
        System.out.println("height = " + height);
        parameters.setPreviewSize(width, height);
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

    private void obtainCameraPreviewSize() {


        String originAspectRatio = getAspectRatio(textureView.getHeight(), textureView.getWidth());
        System.out.println("originAspectRatio = " + originAspectRatio);

        previewSizes = new ArrayList<>();
        List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
        List<String> ratios = new ArrayList<>();
        for (Camera.Size size : sizes) {
            String previewAspectRatio = getAspectRatio(size.width, size.height);
            if (previewAspectRatio.equals(originAspectRatio)) {
                previewSizes.add(size);
                System.out.println("previewAspectRatio = " + previewAspectRatio);
                System.out.println("size.width = " + size.width);
                System.out.println("size.height = " + size.height);
                String ratio = size.width + ":" + size.height;
                ratios.add(ratio);
            }
        }
        System.out.println("ratios.size() = " + ratios.size());
        adapter = new PreviewSizeRecyclerViewAdapter(this, ratios);
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

    public class PreviewSizeRecyclerViewAdapter extends RecyclerView.Adapter<PreviewSizeRecyclerViewAdapter.PreviewSizeViewHolder> {
        private List<String> ratios;
        private Context context;

        public PreviewSizeRecyclerViewAdapter(Context context, List<String> ratios) {
            this.context = context;
            this.ratios = ratios;
        }

        @Override
        public PreviewSizeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PreviewSizeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_preview, parent, false));
        }

        @Override
        public void onBindViewHolder(PreviewSizeViewHolder holder, final int position) {
            holder.previewSizeTextView.setText(ratios.get(position));
            holder.previewSizeTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parameters.setPreviewSize(previewSizes.get(position).width,previewSizes.get(position).height);
                    camera.setParameters(parameters);
                }
            });

        }

        @Override
        public int getItemCount() {
            return ratios.size();
        }

        public class PreviewSizeViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_preview_size)
            TextView previewSizeTextView;

            public PreviewSizeViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
