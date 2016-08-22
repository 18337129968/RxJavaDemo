package com.isoftstone.rxjavademo.http;

import android.content.Context;
import android.text.TextUtils;

import com.isoftstone.rxjavademo.app.Constants;
import com.isoftstone.rxjavademo.app.SingleBeans;
import com.isoftstone.rxjavademo.beans.PagerBean;
import com.isoftstone.rxjavademo.beans.request.LoginRequest;
import com.isoftstone.rxjavademo.beans.result.LoginResult;
import com.isoftstone.rxjavademo.beans.result.SysUserBean;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.http
 *
 * @Author: xie
 * @Time: 2016/8/16 19:59
 * @Description:
 */

public class HttpManager extends BaseHttp {
    private static final HttpManager INSTANCE = new HttpManager();
    private static Map<String, String> keys = new HashMap<>();
    private static final String TOKEN = "token";
    private HttpApi api;

    public static HttpManager getInstance() {
        return INSTANCE;
    }

    /**
     * @return com.isoftstone.rxjavademo.http.HttpManager
     * @Title: httpRequest
     * @Description: (isCach是否缓存，firstCach请求首选缓存)
     * @params [context, isCach, firstCach]
     */
    public HttpManager httpRequest(Context context, boolean isCach) {
        super.context = context;
        api = createService(HttpApi.class, null, Constants.BASEURL, isCach);
        if (!TextUtils.isEmpty(SingleBeans.getTokenUtil().getToken()) && keys.size() == 0)
            keys.put(TOKEN, SingleBeans.getTokenUtil().getToken());
        return INSTANCE;
    }

    public void getToken(String appId, String deviceId, Subscriber<SysUserBean> subscriber) {
        httpResult(api.getToken(appId, deviceId), subscriber);
    }

    public void login(LoginRequest loginRequest, Subscriber<PagerBean<LoginResult>> subscriber) {
        httpResult(api.login(keys, loginRequest), subscriber);
    }
}
