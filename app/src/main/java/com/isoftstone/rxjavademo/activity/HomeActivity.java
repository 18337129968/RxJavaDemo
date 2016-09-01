package com.isoftstone.rxjavademo.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.isoftstone.rxjavademo.R;
import com.isoftstone.rxjavademo.app.BaseActivity;
import com.isoftstone.rxjavademo.app.MyApplication;
import com.isoftstone.rxjavademo.beans.result.SysUserResponseVo;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity {
    private TextView textView;

    @Inject
    protected SysUserResponseVo user;

    private SimpleDraweeView my_image_view;

    @Override
    protected int getContentResource() {
        return R.layout.activity_home;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        textView = (TextView) findViewById(R.id.hometext);
        MyApplication.getInstance().getUserComponent().inject(this);
        textView.setText(user.userName);
        my_image_view = (SimpleDraweeView) findViewById(R.id.my_image_view);
        my_image_view.setImageURI(Uri.parse("http://img2.3lian.com/2014/f2/37/d/40.jpg"));

        my_image_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
