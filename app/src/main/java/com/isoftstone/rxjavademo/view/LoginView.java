package com.isoftstone.rxjavademo.view;

import com.isoftstone.rxjavademo.beans.result.SysUserResponseVo;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.view
 *
 * @Author: xie
 * @Time: 2016/8/26 17:23
 * @Description:
 */

public interface LoginView extends BaseView {
    void canLogin(boolean canLogin);
    void success(SysUserResponseVo user);
    void failture();
}
