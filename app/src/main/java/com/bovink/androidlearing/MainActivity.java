package com.bovink.androidlearing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_text)
    TextView textView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



    }







    private static final String serverURL = "http://vop.baidu.com/server_api";

    private void method2() throws Exception {
        String cuid = DeviceUtils.getIMEI(this);
        String token = "24.508115420aa19cd203cd12c3ffd6fdcb.2592000.1516242203.282335-7631707";
        File pcmFile = new File("");
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
