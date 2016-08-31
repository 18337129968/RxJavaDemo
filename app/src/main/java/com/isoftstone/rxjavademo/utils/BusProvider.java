package com.isoftstone.rxjavademo.utils;

import com.squareup.otto.Bus;

/**
 * Created by IntelliJ IDEA in MaitianCommonLibrary
 * cn.maitian.app.library.event
 *
 * @Author: xie
 * @Time: 2016/4/14 16:27
 * @Description:
 */
public class BusProvider {

    private static Bus _bus = new Bus();

    private BusProvider() {
    }

    public static void post(Object obj) {
        _bus.post(obj);
    }

    public static void register(Object obj) {
        _bus.register(obj);
    }

    public static void unregister(Object obj) {
        _bus.unregister(obj);
    }
}
