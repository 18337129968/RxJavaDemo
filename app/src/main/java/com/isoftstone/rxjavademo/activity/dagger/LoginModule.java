package com.isoftstone.rxjavademo.activity.dagger;

import com.isoftstone.rxjavademo.activity.presenter.LoginPresenter;
import com.isoftstone.rxjavademo.view.LoginView;

import dagger.Module;
import dagger.Provides;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.activity.dagger
 *
 * @Author: xie
 * @Time: 2016/8/29 10:36
 * @Description:
 */
@Module
public class LoginModule {
    private LoginView loginView;

    public LoginModule(LoginView loginView) {
        this.loginView = loginView;
    }

    @Provides
    LoginView provideLoginView() {
        return loginView;
    }

    @Provides
    LoginPresenter provideLoginPresenter() {
        return new LoginPresenter(loginView);
    }

}
