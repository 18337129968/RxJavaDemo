package com.isoftstone.rxjavademo.app.user;

import com.isoftstone.rxjavademo.beans.result.SysUserResponseVo;

import dagger.Module;
import dagger.Provides;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.app
 *
 * @Author: xie
 * @Time: 2016/8/25 16:06
 * @Description:
 */
@Module
public class UserModule {
    private SysUserResponseVo user;

    public UserModule(SysUserResponseVo user) {
        this.user = user;
    }

    @Provides
    @UserScope
    SysUserResponseVo provideUser() {
        return user;
    }
}
