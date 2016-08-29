package com.isoftstone.rxjavademo.http;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.isoftstone.rxjavademo.app.Constants;
import com.isoftstone.rxjavademo.app.SingleBeans;
import com.isoftstone.rxjavademo.beans.BusProvider;
import com.isoftstone.rxjavademo.beans.ErrorBean;
import com.isoftstone.rxjavademo.beans.ModleBean;

import java.util.HashMap;
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
    protected static Map<String, String> keys = new HashMap<>();
    protected final String TOKEN = "token";
    protected Context context;
    protected HttpApi api;


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

    private <T> Subscription httpResult(Observable<ModleBean<T>> observable, final Subscriber<T> subscriber) {

        return observable.map(new Func1<ModleBean<T>, T>() {
            @Override
            public T call(ModleBean<T> modleBean) {
                if (modleBean.success) {
                    return modleBean.pager;
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

    protected <T> Subscription dispachHttp(Observable<ModleBean<T>> observable,
                                           final HttpRequest<T> httpRequest) {
        return httpResult(observable, new Subscriber<T>() {
            @Override
            public void onStart() {
                super.onStart();
                httpRequest.onStart();
            }

            @Override
            public void onCompleted() {
                unsubscribe();
                httpRequest.onFinish();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("tag", "------>onError=" + e.getMessage());
                unsubscribe();
                httpRequest.onError();
                BusProvider.getInstance().post(new ErrorBean(e.getMessage()));
            }

            @Override
            public void onNext(T t) {
                httpRequest.onSuccess(t);
            }
        });
    }

    /**
     * @return com.isoftstone.rxjavademo.http.HttpManager
     * @Title: httpRequest
     * @Description: (isCach是否缓存，firstCach请求首选缓存)
     * @params [context, isCach, firstCach]
     */
    protected void httpRequest(Context context, boolean isCach) {
        this.context = context;
        api = createService(HttpApi.class, null, Constants.BASEURL, isCach);
        if (!TextUtils.isEmpty(SingleBeans.getTokenUtil().getToken()) && keys.size() == 0)
            keys.put(TOKEN, SingleBeans.getTokenUtil().getToken());
    }

}

