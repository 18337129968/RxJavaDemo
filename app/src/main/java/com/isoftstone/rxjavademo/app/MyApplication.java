package com.isoftstone.rxjavademo.app;

import android.app.Application;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.app
 *
 * @Author: xie
 * @Time: 2016/8/18 10:21
 * @Description:
 */

public class MyApplication extends Application {

    public MyApplication getInstance() {
        return this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SingleBeans.getSingleBeans(this);
    }

}
