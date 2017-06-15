package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.bovink.androidlearing.R;
import com.bovink.androidlearing.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DefaultObserver;

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

        testTimer();
    }

    /**
     * 测试create
     */
    private void testCreate() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("one");
                e.onNext("two");
                int random = new Random().nextInt(10);
                if (random >= 5) {
                    e.onComplete();
                } else {
                    e.onError(new Throwable("random is less than 5"));
                }
            }
        });

        observable.subscribe(new DefaultObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                System.out.println("s = " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("CreateActivity.onError");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("CreateActivity.onComplete");
            }
        });
    }

    /**
     * 测试just
     */
    private void testJust() {
        Observable<String> observable = Observable.just("one");

        observable.subscribe(new DefaultObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                System.out.println("s = " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("CreateActivity.onComplete");
            }
        });
    }

    /**
     * 测试from
     */
    private void testFromArray() {
        String[] strings = {"one", "two", "three"};

        Observable<String> observable = Observable.fromArray(strings);
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("s = " + s);
            }
        });
    }

    /**
     * 测试fromIterable
     */
    private void testFromIterable() {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");

        Observable.fromIterable(list)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        System.out.println("s = " + s);
                    }
                });
    }

    /**
     * 测试fromCallable
     */
    private void testFromCallable() {
        Person person = new Person();
//        Observable<String> observable = person.getObservableByFromCallable();
        Observable<String> observable = person.getObservableByJust();

        person.setName("jack");

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("s = " + s);
            }
        });
    }

    /**
     * 测试defer
     */
    private void testDefer() {
        Person person = new Person();
        Observable<String> observable = person.getObservableByDefer();
//        Observable<String> observable = person.getObservableByJust();

        person.setName("jack");

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("s = " + s);
            }
        });
    }

    /**
     * 测试interval
     */
    private void testInterval() {
        Observable<Long> observable = Observable.interval(10, 1, TimeUnit.SECONDS);

        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                System.out.println("aLong = " + aLong);
            }
        });
    }

    /**
     * 测试intervalRange
     */
    private void testIntervalRange() {
        Observable<Long> observable = Observable.intervalRange(100, 10, 10, 1, TimeUnit.SECONDS);

        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                System.out.println("aLong = " + aLong);
            }
        });
    }

    /**
     * 测试range
     */
    private void testRange() {
        Observable<Integer> observable = Observable.range(0, 10);

        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                System.out.println("integer = " + integer);
            }
        });
    }

    /**
     * 测试repeat
     */
    private void testRepeat() {
        Observable<String> observable = Observable.just("one").repeat();

        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("s = " + s);
            }
        });
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
     * 测试timer
     */
    private void testTimer() {
        Observable<Long> observable = Observable.timer(10, TimeUnit.SECONDS);

        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(@NonNull Long aLong) throws Exception {
                System.out.println("aLong = " + aLong);
            }
        });
    }

}
