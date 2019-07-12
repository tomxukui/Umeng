package com.xukui.library.umeng;

import android.app.Application;
import android.text.TextUtils;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.xukui.library.umeng.params.MiPushParam;
import com.xukui.library.umeng.params.PushRegisterCallback;
import com.xukui.library.umeng.params.UmengParam;
import com.xukui.library.umeng.params.UmengPushParam;

import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.xiaomi.MiPushRegistar;

public class UmengClient {

    public static void init(Application application, UmengParam umengParam, boolean logEnabled) {
        UmengPushParam pushParam = umengParam.getPushParam();

        //配置数据统计
        UMConfigure.init(application, umengParam.getAppkey(), umengParam.getChannel(), umengParam.getDeviceType(), pushParam == null ? null : pushParam.getMessageSecret());
        UMConfigure.setLogEnabled(logEnabled);

        //配置友盟推送
        if (pushParam != null) {
            PushAgent pushAgent = PushAgent.getInstance(application);

            //设置推送注册回调
            final PushRegisterCallback pushRegisterCallback = pushParam.getRegisterCallback();
            if (pushRegisterCallback != null) {
                pushRegisterCallback.onStart();
            }
            pushAgent.register(new IUmengRegisterCallback() {

                @Override
                public void onSuccess(String deviceToken) {
                    if (pushRegisterCallback != null) {
                        pushRegisterCallback.onSuccess(deviceToken);
                    }
                }

                @Override
                public void onFailure(String s, String s1) {
                    if (pushRegisterCallback != null) {
                        pushRegisterCallback.onFailure(s, s1);
                    }
                }

            });

            //设置代码包名, 解决applicationId和代码包名不一致的情况
            String resourcePackageName = pushParam.getResourcePackageName();
            if (!TextUtils.isEmpty(resourcePackageName)) {
                PushAgent.getInstance(application).setResourcePackageName(resourcePackageName);
            }

            //设置推送处理
            PushAgent.getInstance(application).setMessageHandler(pushParam.getUmengMessageHandler());
            PushAgent.getInstance(application).setNotificationClickHandler(pushParam.getUmengNotificationClickHandler());

            //配置小米推送
            MiPushParam miPushParam = pushParam.getMiPushParam();
            if (miPushParam != null) {
                MiPushRegistar.register(application, miPushParam.getAppId(), miPushParam.getAppKey());
            }

            //配置华为推送
            if (pushParam.isHuaWeiPushEnabled()) {
                HuaWeiRegister.register(application);
            }
        }
    }

}