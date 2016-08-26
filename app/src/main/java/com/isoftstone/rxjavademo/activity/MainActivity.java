package com.isoftstone.rxjavademo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.isoftstone.rxjavademo.R;
import com.isoftstone.rxjavademo.app.BaseActivity;
import com.isoftstone.rxjavademo.app.MyApplication;
import com.isoftstone.rxjavademo.app.SingleBeans;
import com.isoftstone.rxjavademo.beans.PagerBean;
import com.isoftstone.rxjavademo.beans.request.LoginRequest;
import com.isoftstone.rxjavademo.beans.result.SysUserResponseVo;
import com.isoftstone.rxjavademo.http.HttpRequest;

public class MainActivity extends BaseActivity {
    private LoginRequest login;
    private Button btn;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {

        btn = (Button) findViewById(R.id.button);
        login = new LoginRequest("15892", "123456", "e3225cc1-eba7-4993-93f9-63044d4ee540");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        SingleBeans.getHttpManager()
                .login(this, false, login, new HttpRequest<PagerBean<SysUserResponseVo>>() {
                            @Override
                            public void onStart() {
                            }

                            @Override
                            public void onSuccess(PagerBean<SysUserResponseVo> loginResultPagerBean) {
//                                Log.i("tag", "----token--=" + loginResultPagerBean.content.get(0).token);
                                MyApplication.getInstance().createUserComponent(loginResultPagerBean.content.get(0));
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
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
