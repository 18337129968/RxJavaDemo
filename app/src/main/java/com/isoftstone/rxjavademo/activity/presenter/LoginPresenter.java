package com.isoftstone.rxjavademo.activity.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.isoftstone.rxjavademo.app.AppManagers;
import com.isoftstone.rxjavademo.app.MyApplication;
import com.isoftstone.rxjavademo.beans.PagerBean;
import com.isoftstone.rxjavademo.beans.request.LoginRequest;
import com.isoftstone.rxjavademo.beans.result.DaoMaster;
import com.isoftstone.rxjavademo.beans.result.DaoSession;
import com.isoftstone.rxjavademo.beans.result.SysUserResponseVo;
import com.isoftstone.rxjavademo.beans.result.SysUserResponseVoDao;
import com.isoftstone.rxjavademo.http.HttpRequest;
import com.isoftstone.rxjavademo.view.LoginView;

import java.util.List;

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
        AppManagers.getHttpManager()
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

    private SysUserResponseVoDao userDao;

    public void saveUser(Context context, SysUserResponseVo user) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "users-db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getSysUserResponseVoDao();
        userDao.insert(user);

        List<SysUserResponseVo> userResponseVo = userDao.queryBuilder().
                where(SysUserResponseVoDao.Properties.UserName.eq("张二月"))
                .build().list();

        MyApplication.getInstance().createUserComponent(userResponseVo.get(0));
    }

}
