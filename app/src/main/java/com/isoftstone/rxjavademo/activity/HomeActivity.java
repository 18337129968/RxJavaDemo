package com.isoftstone.rxjavademo.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.isoftstone.rxjavademo.R;
import com.isoftstone.rxjavademo.app.BaseActivity;
import com.isoftstone.rxjavademo.app.MyApplication;
import com.isoftstone.rxjavademo.beans.result.SysUserResponseVo;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity {
    private TextView textView;

    @Inject
    protected SysUserResponseVo user;

    @Override
    protected int getContentResource() {
        return R.layout.activity_home;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        textView = (TextView) findViewById(R.id.hometext);
        MyApplication.getInstance().getUserComponent().inject(this);
        textView.setText(user.userName);
    }
}
