package com.isoftstone.rxjavademo.app;

import android.content.Context;

import com.isoftstone.rxjavademo.http.HttpManager;
import com.isoftstone.rxjavademo.utils.CacheManager;
import com.isoftstone.rxjavademo.utils.Toastor;
import com.isoftstone.rxjavademo.utils.TokenUtil;

import java.io.File;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.app
 *
 * @Author: xie
 * @Time: 2016/8/18 10:16
 * @Description:
 */

public class AppManagers {

    private static AppManagers appManagers = new AppManagers();
    private static HttpManager httpManager;
    private static CacheManager cacheManager;
    private static TokenUtil tokenUtil;
    private static Toastor toastor;

    public static AppManagers getAppManagers(Context context) {

        if (httpManager == null) {
            httpManager = HttpManager.getInstance();
        }

        if (cacheManager == null) {
            cacheManager = CacheManager.get(context.getExternalCacheDir().getAbsolutePath() +
                    File.separator + "DataCache");
        }

        if (tokenUtil == null) {
            tokenUtil = tokenUtil.getInstance();
            tokenUtil.getTocken(context);
        }

        if (toastor == null) {
            toastor = new Toastor(context);
        }
        return appManagers;
    }

    public static HttpManager getHttpManager() {
        return httpManager;
    }

    public static CacheManager getCacheManager() {
        return cacheManager;
    }

    public static TokenUtil getTokenUtil() {
        return tokenUtil;
    }

    public static Toastor getToastor() {
        return toastor;
    }
}
