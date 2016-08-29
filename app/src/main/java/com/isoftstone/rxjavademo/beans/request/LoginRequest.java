package com.isoftstone.rxjavademo.beans.request;

import javax.inject.Inject;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.beans.request
 *
 * @Author: xie
 * @Time: 2016/8/19 14:00
 * @Description:
 */

public class LoginRequest {
    public String userLoginName;
    public String password;
    public String bindingImei;

    @Inject
    public LoginRequest() {

    }

    public void setUser(String userLoginName, String password, String bindingImei) {
        this.userLoginName = userLoginName;
        this.password = password;
        this.bindingImei = bindingImei;
    }

//    public LoginRequest(String userLoginName, String password, String bindingImei) {
//        this.userLoginName = userLoginName;
//        this.password = password;
//        this.bindingImei = bindingImei;
//    }
}
