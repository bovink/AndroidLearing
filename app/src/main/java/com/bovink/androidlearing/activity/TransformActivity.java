package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.R;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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

        testMap();
    }

    private void testBuffer() {
        Observable<List<String>> observable = Observable
                .just("one", "two", "three", "four")
                .buffer(2);

        observable.subscribe(strings -> {
            System.out.println("strings.size() = " + strings.size());
        });

    }


    /**
     * 完成
     */
    private void testFlatMap() {
        // 字符串列表
        List<String> fruits = Arrays.asList("apple", "banana", "orange");
        List<String> nums = Arrays.asList("a", "b", "c");

        // 释放字符串列表的Observable
        Observable<List<String>> observable = Observable.just(fruits, nums);

        // 将List<String>转换成Observable<String>
        Function<List<String>, Observable<String>> function = new Function<List<String>, Observable<String>>() {
            @Override
            public Observable<String> apply(@NonNull List<String> strings) throws Exception {
                return Observable.zip(
                        Observable.fromIterable(strings),
                        Observable.interval(500, TimeUnit.MILLISECONDS),
                        (s, aLong) -> s
                );
            }
        };

        Observable<String> stringObservable = observable.flatMap(function);

        stringObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("s = " + s);
            }
        });
    }

    /**
     * 完成
     */
    private void testConcatMap() {
        // 字符串列表
        List<String> fruits = Arrays.asList("apple", "banana", "orange");
        List<String> nums = Arrays.asList("a", "b", "c");

        // 释放字符串列表的Observable
        Observable<List<String>> observable = Observable.just(fruits, nums);

        // 将List<String>转换成Observable<String>
        Function<List<String>, Observable<String>> function = new Function<List<String>, Observable<String>>() {
            @Override
            public Observable<String> apply(@NonNull List<String> strings) throws Exception {
                return Observable.zip(
                        Observable.fromIterable(strings),
                        Observable.interval(500, TimeUnit.MILLISECONDS),
                        (s, aLong) -> s
                );
            }
        };

        Observable<String> stringObservable = observable.concatMap(function);

        stringObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("s = " + s);
            }
        });
    }

    private void testGroupBy() {
        Observable.just("one", "two, three", "four", "five, six")
                .groupBy(s -> {
                    if (s.contains(",")) {
                        return 1;
                    }
                    return 2;
                })
                .subscribe(observable -> {
                    if (observable.getKey() == 1) {

                        observable.subscribe(s -> {
                            System.out.println("s = " + s);
                        });
                    }

                });
    }

    /**
     * 完成
     */
    private void testMap() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4);

        // 将Integer转换成String
        Function<Integer, String> function = new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                Integer result = integer * 100;
                return result.toString();
            }
        };

        Observable<String> stringObservable = observable.map(function);

        stringObservable.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println("s = " + s);
            }
        });
    }

    private void testScan() {
        Observable.just("one", "two", "three", "four")
                .scan((s1, s2) -> s1 + " and " + s2)

                .subscribe(integer -> System.out.println("integer = " + integer));


    }

    private void testWindow1() {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5", "6");

        Observable<Observable<String>> observableObservable = observable.window(2);

        observableObservable.subscribe(new Consumer<Observable<String>>() {
            @Override
            public void accept(@NonNull Observable<String> stringObservable) throws Exception {
                System.out.println("get");
            }
        });

    }

    private void testWindow2() {
        Observable<String> observable = Observable.just("1", "2", "3", "4", "5", "6");

        Observable<Observable<String>> observableObservable = observable.window(1, 1);

        observableObservable.subscribe(new Consumer<Observable<String>>() {
            @Override
            public void accept(@NonNull Observable<String> stringObservable) throws Exception {
                System.out.println("get");
                stringObservable.subscribe(s -> System.out.println("s = " + s));
            }
        });
    }

    private void testWindow3() {
        Observable<String> observable = Observable.zip(Observable.just("1", "2", "3", "4", "5", "6"),
                Observable.interval(500, TimeUnit.MILLISECONDS), (s, aLong) -> s);

        Observable<Observable<String>> observableObservable = observable.window(1010, TimeUnit.MILLISECONDS);

        observableObservable.subscribe(new Consumer<Observable<String>>() {
            @Override
            public void accept(@NonNull Observable<String> stringObservable) throws Exception {
                System.out.println("get");
                stringObservable.subscribe(s -> System.out.println("s = " + s));
            }
        });
    }

    private void testWindow4() {
        Observable<String> observable = Observable.zip(Observable.just("1", "2", "3", "4", "5", "6"),
                Observable.interval(500, TimeUnit.MILLISECONDS), (s, aLong) -> s);

        Observable<Observable<String>> observableObservable = observable.window(1010, 2010, TimeUnit.MILLISECONDS);

        observableObservable.subscribe(new Consumer<Observable<String>>() {
            @Override
            public void accept(@NonNull Observable<String> stringObservable) throws Exception {
                System.out.println("get");
                stringObservable.subscribe(s -> System.out.println("s = " + s));
            }
        });

    }

    private void testWindow5() {
        Observable<String> observable = Observable.zip(Observable.just("1", "2", "3", "4", "5", "6"),
                Observable.interval(500, TimeUnit.MILLISECONDS), (s, aLong) -> s);

        Observable<Observable<String>> observableObservable = observable.window(1510, TimeUnit.MILLISECONDS, 3);

        observableObservable.subscribe(new Consumer<Observable<String>>() {
            @Override
            public void accept(@NonNull Observable<String> stringObservable) throws Exception {
                System.out.println("get");
                stringObservable.subscribe(s -> System.out.println("s = " + s));
            }
        });

    }

    private void testWindow7() {
        Observable<String> observable = Observable.zip(Observable.just("1", "2", "3", "4", "5", "6"),
                Observable.interval(500, TimeUnit.MILLISECONDS), (s, aLong) -> s);

        Observable<Observable<String>> observableObservable = observable.window(2010, TimeUnit.MILLISECONDS, 2, true);

        observableObservable.subscribe(new Consumer<Observable<String>>() {
            @Override
            public void accept(@NonNull Observable<String> stringObservable) throws Exception {
                System.out.println("get");
                stringObservable.subscribe(s -> System.out.println("s = " + s));
            }
        });

    }

    private void testWindow6() {
        Observable<String> observable = Observable.zip(Observable.just("1", "2", "3", "4", "5", "6"),
                Observable.interval(500, TimeUnit.MILLISECONDS), (s, aLong) -> s);

        Observable<Observable<String>> observableObservable = observable.window(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {
                return Observable.just("one").delay(500, TimeUnit.MILLISECONDS);
            }
        });

        observableObservable.subscribe(new Consumer<Observable<String>>() {
            @Override
            public void accept(@NonNull Observable<String> stringObservable) throws Exception {
                System.out.println("get");
            }
        });

    }
}
