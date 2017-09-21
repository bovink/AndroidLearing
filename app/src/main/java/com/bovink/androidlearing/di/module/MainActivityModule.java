package com.bovink.androidlearing.di.module;

import com.bovink.androidlearing.model.Person;

import dagger.Module;
import dagger.Provides;

/**
 * @author fox
 * @since 2017/09/21
 */

@Module
public class MainActivityModule {


    @Provides
    Person providesPerson() {
        return new Person("lilei", "12");
    }

}
