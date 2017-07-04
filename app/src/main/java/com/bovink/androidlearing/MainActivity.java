package com.bovink.androidlearing;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.fl_target)
    FrameLayout targetFl;

    boolean targetShowed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_click)
    void clickBtn() {
        if (targetShowed) {

            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(targetFl, "Y", 0, 1000);
            objectAnimator.setInterpolator(new LinearOutSlowInInterpolator());
            objectAnimator.setDuration(800);
            objectAnimator.start();
        }else {

            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(targetFl, "Y", 1000, 0);
            objectAnimator.setInterpolator(new LinearOutSlowInInterpolator());
            objectAnimator.setDuration(800);
            objectAnimator.start();
        }
        targetShowed = !targetShowed;

    }
}
