package com.isoftstone.rxjavademo.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentResource());
        startWork(savedInstanceState);
    }

    protected abstract int getContentResource();

    protected abstract void startWork(Bundle savedInstanceState);
}
