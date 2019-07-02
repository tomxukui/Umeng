package com.xukui.library.umeng.callback;

public interface OnPushRegisterListener {

    void onStart();

    void onSuccess(String deviceToken);

    void onFailure(String s, String s1);

}
