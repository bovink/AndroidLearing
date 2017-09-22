package com.bovink.androidlearing.fruitlist;

import com.bovink.androidlearing.HttpUtils;
import com.bovink.androidlearing.model.Fruit;

import javax.inject.Named;
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

    @Named("web1")
    @Provides
    String providesWeb1() {

        return HttpUtils.WEB1;
    }

    @Named("web2")
    @Provides
    String providesWeb2() {

        return HttpUtils.WEB2;
    }

    @Named("web1")
    @Provides
    Fruit providesFruit1(@Named("web1") String fruitName) {

        return new Fruit(fruitName,"11111");
    }

    @Named("web2")
    @Provides
    Fruit providesFruit2(@Named("web2") String fruitName) {

        return new Fruit(fruitName,"22222");
    }
}
