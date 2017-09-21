package com.bovink.androidlearing.fruitlist;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * com.bovink.androidlearing.di.module
 *
 * @author bovink
 * @since 2017/9/21
 */

@Module
public class FruitListActivityModule {

    @Provides
    @Singleton
    FruitListContract.Presenter providesPresent(FruitListPresent fruitListPresent) {
        return fruitListPresent;
    }
}
