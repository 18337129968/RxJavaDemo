package com.isoftstone.rxjavademo.app.user;

import com.isoftstone.rxjavademo.activity.dagger.LoginComponent;
import com.isoftstone.rxjavademo.activity.dagger.LoginModule;
import com.isoftstone.rxjavademo.beans.request.LoginRequest;

import dagger.Subcomponent;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.app.user
 *
 * @Author: xie
 * @Time: 2016/8/25 17:10
 * @Description:
 */
@UserScope
@Subcomponent(modules = UserModule.class)
public interface UserComponent {
    LoginComponent getLoginComponent(LoginModule loginModule);
    LoginRequest login();
}
