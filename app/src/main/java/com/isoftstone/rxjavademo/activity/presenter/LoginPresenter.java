package com.isoftstone.rxjavademo.activity.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.isoftstone.rxjavademo.app.SingleBeans;
import com.isoftstone.rxjavademo.beans.PagerBean;
import com.isoftstone.rxjavademo.beans.request.LoginRequest;
import com.isoftstone.rxjavademo.beans.result.SysUserResponseVo;
import com.isoftstone.rxjavademo.http.HttpRequest;
import com.isoftstone.rxjavademo.view.LoginView;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.activity.presenter
 *
 * @Author: xie
 * @Time: 2016/8/26 17:53
 * @Description:
 */

public class LoginPresenter {
    private final LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void checkInput(String username, String password) {
        loginView.canLogin(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password));
    }

    public void login(Context context, LoginRequest login) {
        SingleBeans.getHttpManager()
                .login(context, false, login, new HttpRequest<PagerBean<SysUserResponseVo>>() {
                            @Override
                            public void onStart() {
                                loginView.showLoading();
                            }

                            @Override
                            public void onSuccess(PagerBean<SysUserResponseVo> loginResultPagerBean) {
                                loginView.success(loginResultPagerBean.content.get(0));
                            }

                            @Override
                            public void onFinish() {
                                loginView.hideLoading();
                            }

                            @Override
                            public void onError() {
                                loginView.failture();
                            }
                        }
                );
    }

    public void saveLoginInfo(String username, String password) {

    }
}
