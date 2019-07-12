package com.xukui.library.umeng.params;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;

public class UmengPushParam {

    private String messageSecret;//友盟推送的密钥
    private String resourcePackageName;//代码包名. 解决applicationId和代码包名不一致的情况
    private UmengMessageHandler umengMessageHandler;//友盟消息接收处理
    private UmengNotificationClickHandler umengNotificationClickHandler;//友盟消息点击处理
    private PushRegisterCallback registerCallback;//注册友盟推送的回调
    @Nullable
    private MiPushParam miPushParam;//小米厂商推送参数
    private boolean huaWeiPushEnabled;//是否开启华为厂商推送, 如果开启的话, 华为厂商推送参数需要在AndroidManifest.xml中配置

    public UmengPushParam(String messageSecret) {
        if (TextUtils.isEmpty(messageSecret)) {
            throw new NullPointerException("友盟推送的messageSecret不能为空");
        }

        this.messageSecret = messageSecret;
    }

    public String getMessageSecret() {
        return messageSecret;
    }

    public String getResourcePackageName() {
        return resourcePackageName;
    }

    public void setResourcePackageName(String resourcePackageName) {
        this.resourcePackageName = resourcePackageName;
    }

    public UmengMessageHandler getUmengMessageHandler() {
        return umengMessageHandler;
    }

    public void setUmengMessageHandler(UmengMessageHandler umengMessageHandler) {
        this.umengMessageHandler = umengMessageHandler;
    }

    public UmengNotificationClickHandler getUmengNotificationClickHandler() {
        return umengNotificationClickHandler;
    }

    public void setUmengNotificationClickHandler(UmengNotificationClickHandler umengNotificationClickHandler) {
        this.umengNotificationClickHandler = umengNotificationClickHandler;
    }

    public PushRegisterCallback getRegisterCallback() {
        return registerCallback;
    }

    public void setRegisterCallback(PushRegisterCallback registerCallback) {
        this.registerCallback = registerCallback;
    }

    @Nullable
    public MiPushParam getMiPushParam() {
        return miPushParam;
    }

    public void setMiPushParam(@Nullable MiPushParam miPushParam) {
        this.miPushParam = miPushParam;
    }

    public boolean isHuaWeiPushEnabled() {
        return huaWeiPushEnabled;
    }

    public void setHuaWeiPushEnabled(boolean huaWeiPushEnabled) {
        this.huaWeiPushEnabled = huaWeiPushEnabled;
    }

}