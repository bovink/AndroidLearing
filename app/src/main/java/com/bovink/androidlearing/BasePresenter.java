package com.bovink.androidlearing;

/**
 * @author fox
 * @since 2017/11/14
 */

public interface BasePresenter<T> {


    void bindView(T view);
}
