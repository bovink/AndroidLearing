package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.TestScheduler;


/**
 * com.bovink.androidlearing.activity
 *
 * @author bovink
 * @since 2017/6/17
 */

public class CombineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combine);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_combine)
    void clickCombine() {

        System.out.println("CombineActivity.clickCombine");
        testCombineLatest4();

    }

    private void testCombineLatest1() {
        Function<Object[], Integer> function = objects -> {
            int sum = 0;
            for (Object object : objects) {
                sum += (Integer) object;
            }
            return sum;
        };

        Observable<Object> observable1 = Observable.zip(Observable.just(1, 2, 3),
                Observable.interval(1000, TimeUnit.MILLISECONDS)
                , (integer, aLong) -> integer);

        Observable<Object> observable2 = Observable.zip(Observable.just(4, 5, 6),
                Observable.interval(1500, TimeUnit.MILLISECONDS)
                , (integer, aLong) -> integer);

        Observable.combineLatest(function, 1, observable1, observable2)
                .subscribe(integer -> {
                    System.out.println("integer = " + integer);
                });
    }

    private void testCombineLatest2() {
        Function<Object[], String> function = objects -> objects[0].toString() + " " + objects[1].toString();
        List<Observable<String>> observableList = new ArrayList<>();

        observableList.add(Observable.zip(Observable.just("one", "two"),
                Observable.interval(1000, TimeUnit.MILLISECONDS),
                (s, aLong) -> s));
        observableList.add(Observable.zip(Observable.just("three", "four"),
                Observable.interval(1500, TimeUnit.MILLISECONDS),
                (s, aLong) -> s));

        Observable.combineLatest(observableList, function)
                .subscribe(s -> {
                    System.out.println("s = " + s);
                });

    }

    private void testCombineLatest3() {
        Function<Object[], String> function = objects -> objects[0].toString() + " " + objects[1].toString();

        Observable<String>[] observables = new Observable[2];

        observables[0] = Observable.zip(Observable.just("1", "2", "3"),
                Observable.interval(1000, TimeUnit.MILLISECONDS), (s, aLong) -> s);
        observables[1] = Observable.zip(Observable.just("one", "two", "three"),
                Observable.interval(1500, TimeUnit.MILLISECONDS), (s, aLong) -> s);

        Observable.combineLatest(observables, function).subscribe(s -> {
            System.out.println("s = " + s);
        });

    }

    private void testCombineLatest4() {
        Function3<String, Integer, Integer, String> function = (s, i1, i2) -> s + " " + i1 * i2;

        Observable<String> observable1 = Observable.zip(Observable.just("one", "two", "three"),
                Observable.interval(1000, TimeUnit.MILLISECONDS), (s, aLong) -> s);
        Observable<Integer> observable2 = Observable.zip(Observable.just(1, 2, 3),
                Observable.interval(1500, TimeUnit.MILLISECONDS), (i, aLong) -> i);
        Observable<Integer> observable3 = Observable.zip(Observable.just(10, 100, 1000),
                Observable.interval(2000, TimeUnit.MILLISECONDS), (i, aLong) -> i);

        Observable.combineLatest(observable1, observable2, observable3, function)
                .subscribe(s -> System.out.println("s = " + s));
    }

    private void testJoin() {
        TestScheduler scheduler = new TestScheduler();
        Observable<Long> observable1 = Observable
                .interval(5, TimeUnit.SECONDS, scheduler).take(5);

        Observable<Long> observable2 = Observable
                .interval(10, TimeUnit.SECONDS, scheduler)
                .map(i -> i + 10).take(5);

        observable1.join(observable2, new Function<Long, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(@NonNull Long aLong) throws Exception {
                return Observable.just(aLong).delay(4, TimeUnit.SECONDS, scheduler);
            }
        }, new Function<Long, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(@NonNull Long aLong) throws Exception {
                return Observable.just(aLong).delay(1, TimeUnit.SECONDS, scheduler);
            }
        }, new BiFunction<Long, Long, Long>() {
            @Override
            public Long apply(@NonNull Long aLong, @NonNull Long aLong2) throws Exception {
                return aLong + aLong2;
            }
        }).subscribe(i -> System.out.println("i = " + i));

        scheduler.advanceTimeBy(5, TimeUnit.MINUTES);

        // 手动画图可以理解
    }

    private void testMerge() {
        TestScheduler scheduler = new TestScheduler();
        Observable<Long> observable1 = Observable
                .interval(1000, TimeUnit.MILLISECONDS, scheduler)
                .take(5);
        Observable<Long> observable2 = Observable
                .interval(1200, 1000, TimeUnit.MILLISECONDS, scheduler)
                .map(i -> i + 10)
                .take(5);

        Observable.merge(observable1, observable2)
                .subscribe(i -> System.out.println("i = " + i));

        scheduler.advanceTimeBy(1, TimeUnit.MINUTES);
    }

    /**
     * 测试startWith
     */
    private void testStartWith() {
        Observable<String> observable = Observable.just("one", "two").startWith("hello");

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("s = " + s);
            }
        });
    }

    /**
     * 测试SwitchOnNext
     */
    private void testSwitchOnNext() {
        System.out.println("CombineActivity.testSwitchOnNext");
        Observable<String> observable1 = Observable.zip(Observable.just("one", "two", "three"),
                Observable.interval(1, TimeUnit.SECONDS),
                (string, along) -> string);
        Observable<Integer> observable2 = Observable.zip(Observable.just(10, 11, 12),
                Observable.interval(1, TimeUnit.SECONDS),
                (i, along) -> i);


        Observable.switchOnNext(Observable.zip(Observable.just(observable1, observable2),
                Observable.interval(2, TimeUnit.SECONDS),
                (observable, aLong) -> observable))
                .subscribe(string -> System.out.println("string = " + string));


    }

    private void testZip() {
        Observable.zip(Observable.just("one", "two", "three", "four"),
                Observable.just(1, 2, 3),
                (string, i) -> string + i)
                .subscribe(string -> System.out.println("string = " + string));
    }
}
