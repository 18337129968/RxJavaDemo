package com.isoftstone.rxjavademo.utils;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import com.isoftstone.rxjavademo.app.SingleBeans;
import com.isoftstone.rxjavademo.beans.result.SysUserBean;

import rx.Subscriber;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.http
 *
 * @Author: xie
 * @Time: 2016/8/16 18:23
 * @Description:
 */

public class TokenUtil {

    private static TokenUtil tokenUtil;
    private final String APP_ID = "e3225cc1-eba7-4993-93f9-63044d4ee540";
    private final String KEY_DEVICE_ID = "mm_device_id";
    private final String KEY_TOKEN = "mm_token";
    private static String DEVICE_ID;
    private String mToken;


    public static TokenUtil getInstance() {
        if (tokenUtil == null) {
            tokenUtil = new TokenUtil();
        }
        return tokenUtil;
    }

    public void getTocken(Context context) {
        mToken = SingleBeans.getCacheManager().getAsString(KEY_TOKEN);
        if (TextUtils.isEmpty(mToken)) {
            String deviceId = SingleBeans.getCacheManager().getAsString(KEY_DEVICE_ID);
            if (TextUtils.isEmpty(deviceId)) {
                deviceId = TelephoneUtil.getIMEI(context);
                if (TextUtils.isEmpty(deviceId)) {
                    deviceId = AndroidUtil.getMacAddress(context);
                    if (TextUtils.isEmpty(deviceId)) {
                        deviceId = Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
                    }
                }
                SingleBeans.getCacheManager().put(KEY_DEVICE_ID, deviceId);
                DEVICE_ID = deviceId;
            }

            SingleBeans.getHttpManager().httpRequest(context, true)
                    .getToken(APP_ID, deviceId, new Subscriber<SysUserBean>() {
                        @Override
                        public void onCompleted() {
                            SingleBeans.getCacheManager().put(KEY_TOKEN, mToken);
                            unsubscribe();
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("tag", "------>onError=" + e.getMessage());
                        }

                        @Override
                        public void onNext(SysUserBean sysUserBean) {
                            mToken = sysUserBean.content.get(0) + "";
                        }
                    });

        }
    }

    public String getToken() {
        if (TextUtils.isEmpty(mToken)) {
            return SingleBeans.getCacheManager().getAsString(KEY_TOKEN);
        }
        return mToken;
    }


}

