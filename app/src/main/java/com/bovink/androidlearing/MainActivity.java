package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.bovink.androidlearing.download.ProgressListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {
    private static String TAG = MainActivity.class.getName();
    @BindView(R.id.btn_download)
    Button btn_download;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    CompositeDisposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this); //这个最好放在基类里

        disposable = new CompositeDisposable();

        progressBar.setMax(100);

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadFile("video.mp4");
            }
        });
    }

    private void downloadFile(String img) {
        ImageApi api = ApiUtils.getImageApi(new ProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                int percent = (int) ((bytesRead * 100) / contentLength);
                System.out.println("percent = " + percent);
                progressBar.setProgress(percent);
            }
        });


        DisposableObserver<ResponseBody> observer = new DisposableObserver<ResponseBody>() {
            @Override
            public void onNext(@NonNull ResponseBody body) {

                writeResponseBodyToDisk(body);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        disposable.add(api.downloadFile(img)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeWith(observer));



    }

    private void writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getExternalFilesDir(null) + File.separator + "video.mp4");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);
                }

                outputStream.flush();

            } catch (IOException e) {

            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
    }
}
