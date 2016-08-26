package com.isoftstone.rxjavademo.http;

import com.isoftstone.rxjavademo.beans.ModleBean;
import com.isoftstone.rxjavademo.beans.PagerBean;
import com.isoftstone.rxjavademo.beans.request.LoginRequest;
import com.isoftstone.rxjavademo.beans.result.SysUserResponseVo;
import com.isoftstone.rxjavademo.beans.result.SysUserBean;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.http
 *
 * @Author: xie
 * @Time: 2016/8/17 18:19
 * @Description:
 */

public interface HttpApi {

    @GET("auth/getToken")
    Observable<ModleBean<SysUserBean>> getToken(@Query("appId") String appId,
                                                @Query("deviceId") String deviceId);

    @POST("auth/login")
    Observable<ModleBean<PagerBean<SysUserResponseVo>>> login(@QueryMap Map<String, String> keys,
                                                              @Body LoginRequest login);
}
