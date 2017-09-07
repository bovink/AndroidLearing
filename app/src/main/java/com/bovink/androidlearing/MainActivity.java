package com.bovink.androidlearing;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private String filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        filePath = Environment.getExternalStorageDirectory() + "/File/dudao.apk";
    }

    @OnClick(R.id.btn_open)
    void openFile() {

        Uri fileUri;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            fileUri = Uri.fromFile(new File(filePath));
        } else {
            fileUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", new File(filePath));
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
//        MyTask myTask = new MyTask(this);
//        myTask.execute();
    }

    private class MyTask extends AsyncTask<Void, Integer, Void> {
        private Context context;

        public MyTask(Context context) {
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... params) {

            return null;
        }


    }

}
