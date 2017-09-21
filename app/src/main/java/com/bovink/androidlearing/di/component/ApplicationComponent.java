package com.bovink.androidlearing.di.component;

import android.app.Application;

import com.bovink.androidlearing.MyApplication;
import com.bovink.androidlearing.di.module.ActivityBuilder;
import com.bovink.androidlearing.di.module.ApplicationModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author fox
 * @since 2017/09/21
 */

@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        ActivityBuilder.class
})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

    void inject(MyApplication myApplication);
}
