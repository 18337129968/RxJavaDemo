package com.isoftstone.rxjavademo.app;

import com.isoftstone.rxjavademo.activity.dagger.LoginComponent;
import com.isoftstone.rxjavademo.activity.dagger.LoginModule;
import com.isoftstone.rxjavademo.app.user.UserComponent;
import com.isoftstone.rxjavademo.app.user.UserModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.app
 *
 * @Author: xie
 * @Time: 2016/8/24 16:07
 * @Description:
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    UserComponent getUserComponent(UserModule userModule);

    LoginComponent getLoginComponent(LoginModule loginModule);
}
