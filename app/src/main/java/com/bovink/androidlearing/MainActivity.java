package com.bovink.androidlearing;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.bean.ImageFolder;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mDirPaths = new ArrayList<>();
    private int mPicsSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getImages();
    }

    private void getImages() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
//            ViewKit.shortToast("检测到没有内存卡");
            return;
        }
//        showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = getContentResolver();

                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or " +
                                MediaStore.Images.Media.MIME_TYPE + "=? or " +
                                MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png", "image/jpg"},
                        MediaStore.Images.Media.DATE_TAKEN + " DESC");//获取图片的cursor，按照时间倒序（发现没卵用)

                while (mCursor.moveToNext()) {
                    String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));// 1.获取图片的路径
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                        continue;//不获取sd卡根目录下的图片
                    String parentPath = parentFile.getAbsolutePath();//2.获取图片的文件夹信息
                    String parentName = parentFile.getName();
                    ImageFolder imageFolder ;//自定义一个model，来保存图片的信息

                    //这个操作，可以提高查询的效率，将查询的每一个图片的文件夹的路径保存到集合中，
                    //如果存在，就直接查询下一个，避免对每一个文件夹进行查询操作
                    if (mDirPaths.contains(parentPath)) {
                        continue;
                    } else {
                        System.out.println("parentPath = " + parentPath);
                        mDirPaths.add(parentPath);//将父路径添加到集合中
                        imageFolder = new ImageFolder();
                        imageFolder.setLogo(path);
                        imageFolder.setPath(parentPath);
                        imageFolder.setName(parentName);
                    }
                    List<String> strings = null;
                    try {
                        strings = Arrays.asList(parentFile.list(getFileterImage()));
                        System.out.println("strings.size() = " + strings.size());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    int picSize = strings.size();//获取每个文件夹下的图片个数
                    imageFolder.setCount(picSize);//传入每个相册的图片个数
//                    mImageFloders.add(imageFloder);//添加每一个相册
                    //获取图片最多的文件夹信息（父目录对象和个数，使得刚开始显示的是最多图片的相册
                    if (picSize > mPicsSize) {
                        mPicsSize = picSize;
//                        mImgDir = parentFile;
                    }
                }
                mCursor.close();
                mDirPaths = null;
//                mHandler.sendEmptyMessage(1);
            }
        }).start();
    }

    //图片筛选器，过滤无效图片
    private FilenameFilter getFileterImage() {
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.endsWith(".jpg")
                        || filename.endsWith(".png")
                        || filename.endsWith(".jpeg"))
                    return true;
                return false;
            }
        };
        return filenameFilter;
    }
}
