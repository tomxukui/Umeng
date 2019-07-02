package com.xukui.library.umeng.builder;

import android.text.TextUtils;

import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.xukui.library.umeng.callback.OnPushRegisterListener;

public class UmengPushData {

    private String messageSecret;
    private String resourcePackageName;
    private UmengMessageHandler umengMessageHandler;
    private UmengNotificationClickHandler umengNotificationClickHandler;
    private MiPushData miPushData;
    private OnPushRegisterListener onPushRegisterListener;

    UmengPushData(Builder builder) {
        this.messageSecret = builder.messageSecret;
        this.resourcePackageName = builder.resourcePackageName;
        this.umengMessageHandler = builder.umengMessageHandler;
        this.umengNotificationClickHandler = builder.umengNotificationClickHandler;
        this.miPushData = builder.miPushData;
        this.onPushRegisterListener = builder.onPushRegisterListener;
    }

    public String getMessageSecret() {
        return messageSecret;
    }

    public String getResourcePackageName() {
        return resourcePackageName;
    }

    public UmengMessageHandler getUmengMessageHandler() {
        return umengMessageHandler;
    }

    public UmengNotificationClickHandler getUmengNotificationClickHandler() {
        return umengNotificationClickHandler;
    }

    public MiPushData getMiPushData() {
        return miPushData;
    }

    public OnPushRegisterListener getOnPushRegisterListener() {
        return onPushRegisterListener;
    }

    public static class Builder {

        private String messageSecret;
        private String resourcePackageName;
        private UmengMessageHandler umengMessageHandler;
        private UmengNotificationClickHandler umengNotificationClickHandler;
        private MiPushData miPushData;
        private OnPushRegisterListener onPushRegisterListener;

        public Builder(String messageSecret) {
            if (TextUtils.isEmpty(messageSecret)) {
                throw new NullPointerException("友盟推送的messageSecret不能为空");
            }

            this.messageSecret = messageSecret;
        }

        public Builder setResourcePackageName(String resourcePackageName) {
            this.resourcePackageName = resourcePackageName;
            return this;
        }

        public Builder setUmengMessageHandler(UmengMessageHandler umengMessageHandler) {
            this.umengMessageHandler = umengMessageHandler;
            return this;
        }

        public Builder setUmengNotificationClickHandler(UmengNotificationClickHandler umengNotificationClickHandler) {
            this.umengNotificationClickHandler = umengNotificationClickHandler;
            return this;
        }

        public Builder setMiPushData(MiPushData miPushData) {
            this.miPushData = miPushData;
            return this;
        }

        public Builder setOnPushRegisterListener(OnPushRegisterListener listener) {
            onPushRegisterListener = listener;
            return this;
        }

        public UmengPushData create() {
            return new UmengPushData(this);
        }

    }

}