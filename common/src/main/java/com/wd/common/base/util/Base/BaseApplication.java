package com.wd.common.base.util.Base;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;

import cn.jpush.im.android.api.JMessageClient;

public class BaseApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Fresco.initialize(this);
            ARouter.openLog();
            ARouter.openDebug();
        ARouter.init(this);

        JMessageClient.setDebugMode(true);
        JMessageClient.init(getApplicationContext(), true);
        //注册全局事件监听类
        //JMessageClient.registerEventReceiver(new GlobalEventListener(getApplicationContext()));
    }
    public static Context getContext(){
        return context;
    }
}
