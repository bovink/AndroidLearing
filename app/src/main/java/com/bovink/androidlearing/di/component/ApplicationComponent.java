package com.bovink.androidlearing.di.component;

import android.app.Application;

import com.bovink.androidlearing.MyApplication;
import com.bovink.androidlearing.di.module.ActivityBuilder;
import com.bovink.androidlearing.di.module.ApplicationModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * com.bovink.androidlearing.di.component
 *
 * @author bovink
 * @since 2017/9/21
 */

@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuilder.class,
        ApplicationModule.class
})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

    void inject(MyApplication application);
}
