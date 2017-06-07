package com.bovink.androidlearing.create;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.TestScheduler;

/**
 * com.bovink.androidlearing.create
 *
 * @author bovink
 * @since 2017/6/7
 */

public class CreateUtils {

    public static Observable<String> create() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("one");
                e.onNext("two");
                e.onNext("three");
                e.onNext("four");
                e.onNext("five");
                e.onComplete();

            }
        });

        return observable;
    }

    public static Observable<String> from(String[] strings) {
        Observable<String> observable = Observable.fromArray(strings);
        return observable;
    }

    public static Observable<String> empty() {
        Observable<String> observable = Observable.empty();
        return observable;
    }

    public static Observable<String> never() {
        Observable<String> observable = Observable.never();
        return observable;
    }

    public static Observable<String> error() {
        Observable<String> observable = Observable.error(new Callable<Throwable>() {
            @Override
            public Throwable call() throws Exception {
                return new Throwable("you got an error!");
            }
        });
        return observable;
    }

    public static Observable<Long> interval(TestScheduler testScheduler) {
        Observable<Long> observable = Observable.interval(10, 1, TimeUnit.SECONDS, testScheduler);
        return observable;
    }

    public static Observable<String> just(String s) {
        Observable<String> observable = Observable.just(s);
        return observable;
    }

    public static Observable<Integer> range() {
        Observable<Integer> observable = Observable.range(0, 10);
        return observable;
    }

    public static Observable<String> repeat() {
        Observable<String> observable = create().repeat(2);
        return observable;
    }

    public static Observable<String> startWith(String s) {
        Observable<String> observable = create().startWith(s);
        return observable;
    }

    public static Observable<Long> timer(TestScheduler testScheduler) {
        Observable<Long> observable = Observable.timer(10, TimeUnit.SECONDS, testScheduler);
        return observable;
    }



    public static Observable<String> amb(TestScheduler testScheduler) {
        Observable<String> observable_a = create().delay(8, TimeUnit.SECONDS, testScheduler);
        String[] strings = {"a", "b", "c"};
        Observable<String> observable_b = from(strings).delay(6, TimeUnit.SECONDS, testScheduler);
        List<Observable<String>> observableList = new ArrayList<>();

        observableList.add(observable_a);
        observableList.add(observable_b);
        Observable<String> observable = Observable.amb(observableList);
        return observable;
    }

}
