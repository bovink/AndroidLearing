package com.bovink.androidlearing.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * com.bovink.androidlearing.di.module
 *
 * @author bovink
 * @since 2017/9/21
 */

@Module
public class ApplicationModule {

    @Provides
    Context providesContext(Application application) {
        return application.getApplicationContext();
    }
}
