package com.isoftstone.rxjavademo.beans;

import com.squareup.otto.Bus;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.beans
 *
 * @Author: xie
 * @Time: 2016/8/29 15:06
 * @Description:
 */

public class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
    }
}
