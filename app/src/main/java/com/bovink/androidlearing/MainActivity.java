package com.bovink.androidlearing;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.iv_test)
    WeiXinImageView imageView;
    @BindView(R.id.iv_real)
    ImageView realIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                float beishu = params.height / params.width;
                System.out.println("beishu = " + beishu);


                params.width = ScreenUtils.getScreenWidth(MainActivity.this) / 4;
                params.height = (int) (params.width * beishu);

                imageView.setLayoutParams(params);
            }
        };
        handler.sendEmptyMessageDelayed(0, 1000);

    }
}
