package com.xukui.library.umeng.params;

public interface PushRegisterCallback {

    void onStart();

    void onSuccess(String deviceToken);

    void onFailure(String s, String s1);

}
