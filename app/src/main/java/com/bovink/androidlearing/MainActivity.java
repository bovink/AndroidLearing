package com.bovink.androidlearing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.activity.CombineActivity;

import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        testFlowable();
    }

    private void testFlowable() {
        Flowable.interval(1, TimeUnit.NANOSECONDS)
                .onBackpressureDrop()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new FlowableSubscriber<Long>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);

                    }

                    @Override
                    public void onNext(Long aLong) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("aLong = " + aLong);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("MainActivity.onComplete");

                    }
                });
    }

    private void testObservable() {
        Observable.interval(1, TimeUnit.NANOSECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new DefaultObserver<Long>() {
                    @Override
                    public void onNext(@NonNull Long aLong) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("aLong = " + aLong);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("MainActivity.onComplete");

                    }
                });
    }

    @OnClick(R.id.btn_main)
    void showCreateActivity() {
        Intent intent = new Intent(this, CombineActivity.class);
        startActivity(intent);
    }
}
