package com.isoftstone.rxjavademo.http;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.URLUtil;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.isoftstone.rxjavademo.app.Constants;
import com.isoftstone.rxjavademo.app.SingleBeans;
import com.isoftstone.rxjavademo.beans.ModleBean;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.http
 *
 * @Author: xie
 * @Time: 2016/8/19 11:10
 * @Description:
 */

public class BaseHttp {
    protected Context context;

    private OkHttpClient _createClient(Map<String, String> headers, boolean isCach) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(Constants.HTTP_CONNECTTIME, TimeUnit.SECONDS);
        builder.readTimeout(Constants.HTTP_CONNECTTIME, TimeUnit.SECONDS);
        builder.writeTimeout(Constants.HTTP_CONNECTTIME, TimeUnit.SECONDS);
        if (isCach) {
            builder.addInterceptor(new HttpCachInterceptor(context));
            builder.cache(SingleBeans.getCache());
        } else {
            builder.addInterceptor(new HttpInterceptor(headers));
        }

        if (Constants.HTTP_DEBUG) {
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.networkInterceptors().add(new StethoInterceptor());
            builder.addInterceptor(logInterceptor);
        }

        return builder.build();
    }

    protected <T> T createService(Class<T> clazz, Map<String, String> headers, String baseURL, boolean isCach) {

        if (TextUtils.isEmpty(baseURL)) {
            throw new IllegalArgumentException("HttpManager-->createService >>> baseURL can not be empty");
        }

        if (!URLUtil.isHttpUrl(baseURL)) {
            throw new IllegalArgumentException("HttpManager-->createService >>> baseURL is invalid");
        }

        if (clazz == null) {
            throw new IllegalArgumentException("HttpManager-->createService >>> clazz can not pass null");
        }

        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseURL);

        builder.client(_createClient(headers, isCach));

        return builder.build().create(clazz);
    }

    protected <T> Subscription httpResult(Observable<ModleBean<T>> observable, final Subscriber<T> subscriber) {

        return observable.map(new Func1<ModleBean<T>, T>() {
            @Override
            public T call(ModleBean<T> modleBean) {
                if (modleBean.success) {
                    return (T) modleBean.pager;
                } else {
                    subscriber.onError(new Throwable(modleBean.msg));
                }
                return null;
            }
        }).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}