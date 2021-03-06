package com.isoftstone.rxjavademo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.isoftstone.rxjavademo.R;
import com.isoftstone.rxjavademo.activity.presenter.LoginPresenter;
import com.isoftstone.rxjavademo.app.BaseActivity;
import com.isoftstone.rxjavademo.app.MyApplication;
import com.isoftstone.rxjavademo.beans.request.LoginRequest;
import com.isoftstone.rxjavademo.beans.result.SysUserResponseVo;
import com.isoftstone.rxjavademo.utils.AppUtil;
import com.isoftstone.rxjavademo.view.LoginView;
import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.functions.Action1;

@EActivity()
public class MainActivity extends BaseActivity implements LoginView {
    @Inject
    protected LoginPresenter loginPresenter;
    @Inject
    protected LoginRequest login;
    @ViewById
    Button button;
    @ViewById
    EditText username , password;

    @Override
    protected int getContentResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        MyApplication.getInstance().creatLoginComponent(this).inject(this);
//        btn = (Button) findViewById(R.id.button);
//        name = (EditText) findViewById(R.id.username);
        username.setText("15892");
//        pwd = (EditText) findViewById(R.id.password);
        password.setText("123456");
        login.setUser("15892", "123456", "e3225cc1-eba7-4993-93f9-63044d4ee540",
                AppUtil.getPackageInfo(this).versionName, 2);

        RxView.clicks(button).throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        loginPresenter.login(MainActivity.this, login);
                    }
                });

        RxTextView.textChanges(username)
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        loginPresenter.checkInput(charSequence.toString(), password.getText().toString());
                    }
                });

        RxTextView.textChanges(password)
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        loginPresenter.checkInput(username.getText().toString(), charSequence.toString());
                    }
                });

    }

    @Override
    public void canLogin(boolean canLogin) {
        if (canLogin) {
            button.setEnabled(canLogin);
        } else {
            button.setEnabled(false);
        }
    }

    @Override
    public void success(SysUserResponseVo user) {
        loginPresenter.saveUser(MainActivity.this,user);
//        MyApplication.getInstance().createUserComponent(user);
        startActivity(new Intent(MainActivity.this, HomeActivity.class));
    }

    @Override
    public void failture() {
        dissmissProgressDialog();
    }

    @Override
    public void showLoading() {
        showProgressDialog("加载中...");
    }

    @Override
    public void hideLoading() {
        dissmissProgressDialog();
    }

    @AfterViews
    public void initView(){

    }

}
