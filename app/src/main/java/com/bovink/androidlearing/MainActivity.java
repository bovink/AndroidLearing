package com.bovink.androidlearing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultSubscriber;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import cn.finalteam.rxgalleryfinal.utils.ModelUtils;

public class MainActivity extends AppCompatActivity {

    int PICK_IMAGE_MULTIPLE = 1;
    Context context;
    @BindView(R.id.imageview1)
    ImageView imageView1;
    @BindView(R.id.imageview2)
    ImageView imageView2;
    @BindView(R.id.imageview3)
    ImageView imageView3;
    @BindView(R.id.imageview4)
    ImageView imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ModelUtils.setDebugModel(true);
        context = this;

    }

    @OnClick(R.id.btn_custom_album)
    void album() {
        RxGalleryFinalApi.setImgSaveRxSDCard("android_learning");

        //多选图片
        RxGalleryFinal
                .with(MainActivity.this)
                .image()
                .multiple()
                .maxSize(8)
                .imageLoader(ImageLoaderType.GLIDE)
                .subscribe(new RxBusResultSubscriber<ImageMultipleResultEvent>() {

                    @Override
                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                        List<MediaBean> mediaBeanList = imageMultipleResultEvent.getResult();
                        for (int i = 0; i < mediaBeanList.size(); i++) {
//                            System.out.println("mediaBeanList.get(i) = " + mediaBeanList.get(i));
                        }
//                        setImage(imageView1, mediaBeanList.get(0).getThumbnailSmallPath());
//                        setImage(imageView2, mediaBeanList.get(1).getThumbnailSmallPath());
//                        setImage(imageView3, mediaBeanList.get(2).getThumbnailSmallPath());
//                        setImage(imageView4, mediaBeanList.get(3).getThumbnailSmallPath());
                        System.out.println("mediaBeanList.get(0).getThumbnailSmallPath() = " + mediaBeanList.get(0).getThumbnailSmallPath());
                        System.out.println("mediaBeanList.get(0) = " + mediaBeanList.get(0));
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        Toast.makeText(context, "OVER", Toast.LENGTH_SHORT).show();
                    }
                })
                .openGallery();
    }

    @OnClick(R.id.btn_system_album)
    void system() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_MULTIPLE);
    }

    private void setImage(ImageView iv, String path) {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(openFileInput(path));
            iv.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
