package com.bovink.androidlearing.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * @author fox
 * @since 2017/09/21
 */

@Module
public class ApplicationModule {

    @Provides
    Context providesContext(Application application) {
        return application;
    }
}
