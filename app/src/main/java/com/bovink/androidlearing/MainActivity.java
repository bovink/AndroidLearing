package com.bovink.androidlearing;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bovink.androidlearing.network.ApiUtils;
import com.bovink.androidlearing.utils.DeviceUtils;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_text)
    TextView textView;

    private String testFileName;

    private CompositeDisposable compositeDisposable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        compositeDisposable = new CompositeDisposable();


        testFileName = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES) + "/16k.pcm";
        System.out.println("testFileName = " + testFileName);
//        getAccessToken();
    }


    @OnClick(R.id.btn_click)
    void onClick() {
//        Map<String, Object> map = new HashMap<>();
//        PidBuilder pidBuilder = new PidBuilder();
//        map = pidBuilder.addPidInfo(map);
//
//        start(map);

        try {
            identifyAudio();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        compositeDisposable.add(Completable.complete().observeOn(Schedulers.io())
//                .subscribeWith(new DisposableCompletableObserver() {
//                    @Override
//                    public void onComplete() {
//
//                        try {
//                            method2();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//                }));
    }



    private void getAccessToken() {
        // AccessToken
        // 有效期12-19至1-19
        // 24.508115420aa19cd203cd12c3ffd6fdcb.2592000.1516242203.282335-7631707


        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "client_credentials");
        params.put("client_id", "rrfejFB86wfKqyGRITsPYXA4");
        params.put("client_secret", "7la28gRMgv2NAybIWb9yim8ewf61DG6f");

        compositeDisposable.add(ApiUtils.getAccessTokenApi()
                .getAccessToken(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void aVoid) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                }));
    }

    private void identifyAudio() throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("cuid", DeviceUtils.getIMEI(this));
        params.put("token", "24.508115420aa19cd203cd12c3ffd6fdcb.2592000.1516242203.282335-7631707");

        File file = new File(testFileName);
        RequestBody body = RequestBody.create(MediaType.parse("audio/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("", file.getName(), body);

        compositeDisposable.add(ApiUtils.getIdentifyRestApi()
                .identifyAudioFile(params, part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void aVoid) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                }));
    }

    private static final String serverURL = "http://vop.baidu.com/server_api";

    private void method2() throws Exception {
        String cuid = DeviceUtils.getIMEI(this);
        String token = "24.508115420aa19cd203cd12c3ffd6fdcb.2592000.1516242203.282335-7631707";
        File pcmFile = new File(testFileName);
        HttpURLConnection conn = (HttpURLConnection) new URL(serverURL
                + "?cuid=" + cuid + "&token=" + token).openConnection();

        // add request header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "audio/pcm; rate=8000");

        conn.setDoInput(true);
        conn.setDoOutput(true);

        // send request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.write(loadFile(pcmFile));
        wr.flush();
        wr.close();

        printResponse(conn);
    }

    private String printResponse(HttpURLConnection conn) throws Exception {
        if (conn.getResponseCode() != 200) {
            // request error
            return "";
        }
        InputStream is = conn.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        System.out.println(new JSONObject(response.toString()).toString(4));
        return response.toString();
    }

    private byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            is.close();
            throw new IOException("Could not completely read file " + file.getName());
        }

        is.close();
        return bytes;
    }
}
