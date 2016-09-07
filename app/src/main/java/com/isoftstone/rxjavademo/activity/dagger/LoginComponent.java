package com.isoftstone.rxjavademo.activity.dagger;

import com.isoftstone.rxjavademo.activity.MainActivity;
import com.isoftstone.rxjavademo.beans.request.LoginRequest;

import dagger.Subcomponent;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.activity.dagger
 *
 * @Author: xie
 * @Time: 2016/8/26 9:38
 * @Description:
 */
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent {
    LoginRequest login();

    void inject(MainActivity activity);
}
