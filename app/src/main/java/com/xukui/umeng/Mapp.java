package com.xukui.umeng;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.xukui.library.umeng.UmengClient;
import com.xukui.library.umeng.builder.UmengData;
import com.xukui.library.umeng.builder.UmengPushData;

public class Mapp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UmengClient.builder()
                .setContext(this)
                .setLogEnabled(true)
                .setUmengData(new UmengData.Builder("appkey", "channel", UMConfigure.DEVICE_TYPE_PHONE).create())
                .setUmengPushData(new UmengPushData.Builder("messageSecret")
                        .setOnPushRegisterListener(null)
                        .create())
                .create();
    }

}
