package com.xukui.library.umeng.params;

import android.text.TextUtils;

public class MiPushParam {

    private String appId;
    private String appKey;

    public MiPushParam(String appId, String appKey) {
        if (TextUtils.isEmpty(appId)) {
            throw new NullPointerException("小米推送的appid不能为空");
        }
        if (TextUtils.isEmpty(appKey)) {
            throw new NullPointerException("小米推送的appKey不能为空");
        }

        this.appId = appId;
        this.appKey = appKey;
    }

    public String getAppId() {
        return appId;
    }

    public String getAppKey() {
        return appKey;
    }

}