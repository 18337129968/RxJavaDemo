package com.isoftstone.rxjavademo.app.user;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.app
 *
 * @Author: xie
 * @Time: 2016/8/25 16:55
 * @Description:
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface UserScope {

}
