package com.isoftstone.rxjavademo.app;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.app
 *
 * @Author: xie
 * @Time: 2016/8/25 15:53
 * @Description:
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return application;
    }

}
