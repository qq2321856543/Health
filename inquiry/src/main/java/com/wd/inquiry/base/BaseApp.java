package com.wd.inquiry.base;

import android.util.Log;
import android.widget.Toast;

import com.wd.common.base.util.Base.BaseApplication;

import cn.jpush.im.android.api.JMessageClient;

public class BaseApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "注册全局事件监听类", Toast.LENGTH_SHORT).show();
        Log.i("xxx","BaseApp");

    }
}
