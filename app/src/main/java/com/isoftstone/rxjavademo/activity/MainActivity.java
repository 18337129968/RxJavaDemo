package com.isoftstone.rxjavademo.activity;

import android.os.Bundle;
import android.util.Log;

import com.isoftstone.rxjavademo.R;
import com.isoftstone.rxjavademo.app.BaseActivity;
import com.isoftstone.rxjavademo.app.SingleBeans;
import com.isoftstone.rxjavademo.beans.PagerBean;
import com.isoftstone.rxjavademo.beans.request.LoginRequest;
import com.isoftstone.rxjavademo.beans.result.LoginResult;
import com.isoftstone.rxjavademo.http.BaseHttp;

public class MainActivity extends BaseActivity {
    private LoginRequest login;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        login = new LoginRequest("15892", "123456", "e3225cc1-eba7-4993-93f9-63044d4ee540");
        login();
    }

    private void login() {
        SingleBeans.getHttpManager()
                .login(this, false, login, new BaseHttp.HttpRequest<PagerBean<LoginResult>>() {
                            @Override
                            public void onStart() {

                            }

                            @Override
                            public void onSuccess(PagerBean<LoginResult> loginResultPagerBean) {
                                Log.i("tag", "----token--=" + loginResultPagerBean.content.get(0).token);
                            }

                            @Override
                            public void onFinish() {

                            }

                            @Override
                            public void onError() {

                            }
                        }
                );
    }

}
