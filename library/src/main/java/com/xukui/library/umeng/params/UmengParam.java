package com.xukui.library.umeng.params;

import android.support.annotation.Nullable;
import android.text.TextUtils;

public class UmengParam {

    private String appkey;//友盟的appkey
    private String channel;//渠道
    private int deviceType;//设备类型. UMConfigure.DEVICE_TYPE_PHONE和UMConfigure.DEVICE_TYPE_BOX
    @Nullable
    private UmengPushParam pushParam;//推送参数

    public UmengParam(String appkey, String channel, int deviceType) {
        if (TextUtils.isEmpty(appkey)) {
            throw new NullPointerException("友盟的appkey不能为空");
        }

        this.appkey = appkey;
        this.channel = channel;
        this.deviceType = deviceType;
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

    @Nullable
    public UmengPushParam getPushParam() {
        return pushParam;
    }

    public void setPushParam(@Nullable UmengPushParam pushParam) {
        this.pushParam = pushParam;
    }

}