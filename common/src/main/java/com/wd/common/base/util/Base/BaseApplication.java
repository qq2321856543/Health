package com.wd.common.base.util.Base;

import android.app.Application;
import android.content.Context;

//import com.alibaba.android.arouter.launcher.ARouter;

public class BaseApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
 //       ARouter.init(this);
    }
    public static Context getContext(){
        return context;
    }
}
