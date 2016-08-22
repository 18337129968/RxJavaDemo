package com.isoftstone.rxjavademo.http;

import android.content.Context;

import com.isoftstone.rxjavademo.beans.PagerBean;
import com.isoftstone.rxjavademo.beans.request.LoginRequest;
import com.isoftstone.rxjavademo.beans.result.LoginResult;
import com.isoftstone.rxjavademo.beans.result.SysUserBean;

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


    public static HttpManager getInstance() {
        return INSTANCE;
    }

    public void getToken(Context context, boolean isCach,
                         String appId, String deviceId,
                         HttpRequest<SysUserBean> httpRequest) {
        httpRequest(context,isCach);
        dispachHttp(api.getToken(appId, deviceId), httpRequest);
    }

    public void login(Context context, boolean isCach,
                      LoginRequest loginRequest,
                      HttpRequest<PagerBean<LoginResult>> httpRequest) {
        httpRequest(context,isCach);
        dispachHttp(api.login(keys, loginRequest), httpRequest);
    }
}
