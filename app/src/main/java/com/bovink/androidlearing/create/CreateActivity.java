package com.bovink.androidlearing.create;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.bovink.androidlearing.R;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.TestScheduler;

/**
 * com.bovink.androidlearing.create
 *
 * @author bovink
 * @since 2017/6/7
 */

public class CreateActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_create)
    void clickBtn(Button button) {

        TestScheduler testScheduler = new TestScheduler();

        Observable<Long> observable = CreateUtils.timer(testScheduler);

        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                System.out.println("aLong = " + aLong);

            }
        });

        testScheduler.advanceTimeBy(1, TimeUnit.MINUTES);
    }
}
