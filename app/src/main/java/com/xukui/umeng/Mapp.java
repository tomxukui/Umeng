package com.xukui.umeng;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.xukui.library.umeng.UmengClient;
import com.xukui.library.umeng.params.UmengParam;

public class Mapp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UmengParam umengParam = new UmengParam("appkey", "channel", UMConfigure.DEVICE_TYPE_PHONE);
        UmengClient.init(this, umengParam, true);
    }

}