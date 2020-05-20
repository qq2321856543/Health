package com.wd.health;

import android.support.multidex.MultiDex;

import com.wd.common.base.util.Base.BaseApplication;

public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
