package com.bovink.androidlearing;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.iv_image)
    ImageView imageView;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        context = this;

        new Thread() {
            @Override
            public void run() {
                super.run();
                Glide.get(context)
                        .clearDiskCache();
            }
        }.start();


        Glide.with(this)
                .load("https://konachan.net/sample/09ba006ef0ab25a4461dbb56fa235b28/Konachan.com%20-%20247571%20sample.jpg")
                .asBitmap()
                .transform(new MyTransform(this))
                .into(imageView);
    }

    private class MyTransform extends BitmapTransformation {
        private Context context;

        public MyTransform(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            System.out.println("toTransform.getWidth() = " + toTransform.getWidth());
            System.out.println("toTransform.getHeight() = " + toTransform.getHeight());
            int width, height;
            float scale = context.getResources().getDisplayMetrics().density;
            int resize = (int) (scale * 150 + 0.5f);
            float ratio = ((float) toTransform.getWidth()) / ((float) toTransform.getHeight());
            if (ratio > 1) {
                // 横
                width = resize;
                height = (int) (resize / ratio);

            } else {
                // 竖
                width = (int) (resize * ratio);
                height = resize;

            }
            return Bitmap.createScaledBitmap(toTransform, width, height, false);
        }

        @Override
        public String getId() {
            return "";
        }

    }
}
