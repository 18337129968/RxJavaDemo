package com.isoftstone.rxjavademo.http;

import android.util.Log;

import com.isoftstone.rxjavademo.beans.result.SysUserBean;

import rx.Subscriber;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.http
 *
 * @Author: xie
 * @Time: 2016/8/17 19:57
 * @Description:
 */

public class HttpObserver extends Subscriber<SysUserBean> {

    @Override
    public void onCompleted() {
        Log.i("tag", "------onCompleted");
    }

    @Override
    public void onError(Throwable e) {
        Log.i("tag", "------onError");
    }

    @Override
    public void onNext(SysUserBean sysUserBean) {
        Log.i("tag", "------onNext");
    }
}
