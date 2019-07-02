package com.xukui.library.umeng.builder;

import android.text.TextUtils;

public class UmengData {

    private String appkey;
    private String channel;
    private int deviceType;

    UmengData(UmengData.Builder builder) {
        this.appkey = builder.appkey;
        this.channel = builder.channel;
        this.deviceType = builder.deviceType;
    }

    public String getAppkey() {
        return appkey;
    }

    public String getChannel() {
        return channel;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public static class Builder {

        private String appkey;
        private String channel;
        private int deviceType;

        public Builder(String appkey, String channel, int deviceType) {
            if (TextUtils.isEmpty(appkey)) {
                throw new NullPointerException("友盟的appKey不能为空");
            }
            if (channel == null) {
                throw new NullPointerException("友盟的channel不能为空");
            }

            this.appkey = appkey;
            this.channel = channel;
            this.deviceType = deviceType;
        }

        public UmengData create() {
            return new UmengData(this);
        }

    }

}