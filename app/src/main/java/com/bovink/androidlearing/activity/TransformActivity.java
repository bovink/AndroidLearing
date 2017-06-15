package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * com.bovink.androidlearing.activity
 *
 * @author bovink
 * @since 2017/6/15
 */

public class TransformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_transform)
    void clickTransform() {

        testBuffer();
    }

    private void testBuffer() {
        Observable<List<String>> observable = Observable
                .just("one", "two", "three", "four")
                .buffer(2);


    }


}
