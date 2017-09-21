package com.bovink.androidlearing.di.module;

import com.bovink.androidlearing.fruitlist.FruitListActivity;
import com.bovink.androidlearing.fruitlist.FruitListActivityModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * com.bovink.androidlearing.di.module
 *
 * @author bovink
 * @since 2017/9/21
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = FruitListActivityModule.class)
    @Singleton
    abstract FruitListActivity buildFruitListActivity();
}
