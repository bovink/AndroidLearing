package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.R;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.TestScheduler;

/**
 * Created by Retina975 on 17/6/16.
 */

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_filter)
    void clickFilter() {


        testDebounce();

    }

    private void testDebounce() {
        TestScheduler scheduler = new TestScheduler();
        Observable.zip(Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                Observable.interval(1, TimeUnit.SECONDS, scheduler),
                (i, aLong) -> i)
                .debounce(1500, TimeUnit.MILLISECONDS, scheduler)
                .subscribe(i -> System.out.println("i = " + i));

        scheduler.advanceTimeBy(1, TimeUnit.MINUTES);

    }

    private void testDistinct() {
        Observable.just(1, 2, 3, 4, 3, 2, 5, 1)
                .distinct()
                .subscribe(i -> System.out.println("i = " + i));
    }


    private void testElementAt() {
        Observable.just("one", "two", "three")
                .elementAt(2)
                .subscribe(s -> System.out.println("s = " + s));
    }

    private void testFilter() {
        Observable.just(1, 2, 3, 4, 5, 6, 7)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        if (integer % 2 == 0) {
                            return true;
                        }
                        return false;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        System.out.println("integer = " + integer);
                    }
                });

    }

    private void testFirst() {
        Observable.empty()
                .first("first")
                .subscribe(s -> System.out.println("s = " + s));
    }

    private void testIgnoreElements() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("one");
                e.onNext("two");
                e.onNext("three");
                e.onError(new Throwable("out of memory"));

            }
        })
                .ignoreElements()
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        System.out.println("FilterActivity.onComplete");

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println("FilterActivity.onError");

                    }
                });
    }

    private void testLast() {
        Observable.just("one", "two", "three", "four")
                .last("last")
                .subscribe(s -> System.out.println("s = " + s));
    }

    private void testSample() {
        TestScheduler scheduler = new TestScheduler();
        Observable.zip(Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
                Observable.interval(1, TimeUnit.SECONDS, scheduler),
                (i, along) -> i)
                .sample(2, TimeUnit.SECONDS, scheduler)
                .subscribe(i -> System.out.println("i = " + i));

        scheduler.advanceTimeBy(10, TimeUnit.MINUTES);
    }

    private void testSkip() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .skip(4)
                .subscribe(i -> System.out.println("i = " + i));
    }

    private void testSkipLast() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .skipLast(4)
                .subscribe(i -> System.out.println("i = " + i));
    }

    private void testTake() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .take(4)
                .subscribe(i -> System.out.println("i = " + i));
    }

    private void testTakeLast() {
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .takeLast(4)
                .subscribe(i -> System.out.println("i = " + i));
    }
}
