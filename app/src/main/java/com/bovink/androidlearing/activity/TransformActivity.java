package com.bovink.androidlearing.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bovink.androidlearing.R;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

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

//        testScan();
        testWindow7();
    }

    private void testBuffer() {
        Observable<List<String>> observable = Observable
                .just("one", "two", "three", "four")
                .buffer(2);

        observable.subscribe(strings -> {
            System.out.println("strings.size() = " + strings.size());
        });

    }

    private void testFlatMap() {
        String[] s1 = {"one", "two"};
        String[] s2 = {"three", "four"};
        Observable.just(s1, s2)
                .flatMap(Observable::fromArray)
                .subscribe(s -> System.out.println("s = " + s));

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

    private void testMap() {
        Observable.just(1, 2, 3, 4)
                .map(integer -> integer * 10)
                .subscribe(i -> {
                    System.out.println("i = " + i);
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
