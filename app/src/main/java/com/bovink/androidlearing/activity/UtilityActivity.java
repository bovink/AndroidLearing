package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.R;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * com.bovink.androidlearing.activity
 *
 * @author bovink
 * @since 2017/6/17
 */

public class UtilityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utility);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_utility)
    void clickUtility() {

        testDelay();
    }


    private void testDelay() {

        System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());
        Observable.just("one")
                .delay(5, TimeUnit.SECONDS)
                .subscribe(s ->{
                    System.out.println("s = " + s);
                    System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());
                });

    }

}


