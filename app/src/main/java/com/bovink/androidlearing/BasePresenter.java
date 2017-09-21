package com.bovink.androidlearing;

/**
 * com.bovink.androidlearing
 *
 * @author bovink
 * @since 2017/9/18
 */

public interface BasePresenter<T> {
    void setView(T view);
}
