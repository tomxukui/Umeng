package com.xukui.library.umeng;

import android.app.Application;
import android.text.TextUtils;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.xukui.library.umeng.builder.UmengData;
import com.xukui.library.umeng.builder.UmengPushData;

import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.xiaomi.MiPushRegistar;

public final class UmengClient {

    private Application mContext;
    private UmengData umengData;
    private UmengPushData umengPushData;
    private boolean logEnabled;

    static volatile UmengClient INSTANCE;

    public static UmengClient getInstance() {
        if (INSTANCE == null) {
            synchronized (UmengClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UmengClient(new UmengClient.Builder());
                }
            }
        }
        return INSTANCE;
    }

    UmengClient(Builder builder) {
        mContext = builder.context;
        umengData = builder.umengData;
        umengPushData = builder.umengPushData;
        logEnabled = builder.logEnabled;

        init();
    }

    private void init() {
        //配置数据统计
        UMConfigure.init(mContext, umengData.getAppkey(), umengData.getChannel(), umengData.getDeviceType(), umengPushData == null ? null : umengPushData.getMessageSecret());
        UMConfigure.setLogEnabled(logEnabled);

        //配置友盟推送
        if (umengPushData != null) {
            PushAgent pushAgent = PushAgent.getInstance(mContext);

            if (umengPushData.getOnPushRegisterListener() != null) {
                umengPushData.getOnPushRegisterListener().onStart();
            }

            pushAgent.register(new IUmengRegisterCallback() {

                @Override
                public void onSuccess(String deviceToken) {
                    if (umengPushData.getOnPushRegisterListener() != null) {
                        umengPushData.getOnPushRegisterListener().onSuccess(deviceToken);
                    }
                }

                @Override
                public void onFailure(String s, String s1) {
                    if (umengPushData.getOnPushRegisterListener() != null) {
                        umengPushData.getOnPushRegisterListener().onFailure(s, s1);
                    }
                }

            });

            if (!TextUtils.isEmpty(umengPushData.getResourcePackageName())) {
                PushAgent.getInstance(mContext).setResourcePackageName(umengPushData.getResourcePackageName());
            }
            PushAgent.getInstance(mContext).setMessageHandler(umengPushData.getUmengMessageHandler());
            PushAgent.getInstance(mContext).setNotificationClickHandler(umengPushData.getUmengNotificationClickHandler());

            //配置小米推送
            if (umengPushData.getMiPushData() != null) {
                MiPushRegistar.register(mContext, umengPushData.getMiPushData().getAppId(), umengPushData.getMiPushData().getAppKey());
            }

            //配置华为推送
            if (umengPushData.isHuaWeiPushEnabled()) {
                HuaWeiRegister.register(mContext);
            }
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Application getContext() {
        return mContext;
    }

    public static class Builder {

        Application context;
        UmengData umengData;
        UmengPushData umengPushData;
        boolean logEnabled;

        public Builder() {
        }

        public Builder setContext(Application context) {
            if (context == null) {
                throw new NullPointerException("友盟的Application不能为空");
            }

            this.context = context;
            return this;
        }

        public Builder setUmengData(UmengData umengData) {
            if (umengData == null) {
                throw new NullPointerException("友盟的UmengData不能为空");
            }

            this.umengData = umengData;
            return this;
        }

        public Builder setUmengPushData(UmengPushData data) {
            umengPushData = data;
            return this;
        }

        public Builder setLogEnabled(boolean logEnabled) {
            this.logEnabled = logEnabled;
            return this;
        }

        public UmengClient create() {
            synchronized (UmengClient.class) {
                if (UmengClient.INSTANCE != null) {
                    throw new RuntimeException("友盟的UmengClient不能多次初始化");
                }

                UmengClient.INSTANCE = new UmengClient(this);
                return UmengClient.INSTANCE;
            }
        }
    }

}