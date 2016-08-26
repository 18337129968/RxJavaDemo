package com.isoftstone.rxjavademo.activity.dagger;

import com.isoftstone.rxjavademo.activity.HomeActivity;

import dagger.Subcomponent;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.activity.dagger
 *
 * @Author: xie
 * @Time: 2016/8/26 9:38
 * @Description:
 */
@Subcomponent
public interface LoginComponent {
    void inject(HomeActivity aActivity);
}
