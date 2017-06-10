package com.bovink.androidlearing.model;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * com.bovink.androidlearing.model
 *
 * @author bovink
 * @since 2017/6/10
 */

public class Person {
    private String name = "nobody";

    public void setName(String name) {
        this.name = name;
    }

    public Observable<String> getObservableByJust() {
        return Observable.just(name);
    }

    public Observable<String> getObservableByFromCallable() {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return name;
            }
        });
    }

    public Observable<String> getObservableByDefer() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                return Observable.just(name);
            }
        });
    }
}
