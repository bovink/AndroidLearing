package com.bovink.androidlearing;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        File path;
        // app专属文件
        // app外部存储
        path = getExternalFilesDir(null);
        fun(path,"getExternalFilesDir");
        // app内部存储
        path = getFilesDir();
        fun(path,"getFilesDir");
        // app外部文件
        path = Environment.getExternalStorageDirectory();
        path = new File(path, "File");
        if (!path.exists()) {
            path.mkdir();
        }
        fun(path, "getExternalStorageDirectory");

    }

    private void fun(File path, String name) {
        File file = new File(path, name + ".txt");

        LogUtils.e("" + path);
        write(file);
    }

    private void write(File file) {
        try {
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(file.getName().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
