package com.wd.common.base.util.Base;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.wd.common.base.util.util.GlobalEventListener;
import com.wd.common.base.util.util.MD;
import com.wd.common.base.util.util.RsaCoder;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class BaseApplication extends Application {

    private static Context context;
    private String s;

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
        JMessageClient.registerEventReceiver(new GlobalEventListener(getApplicationContext()));
        String name1="zhengha123";
        String pwd1= "88888";
        String name2="zhenghaofei";
        String pwd2="666666";
//        JMessageClient.register(name1, pwd1, new BasicCallback() {
//            @Override
//            public void gotResult(int code, String desc) {
//                if (code == 0) {
//                    // 注册成功
//                    Toast.makeText(BaseApplication.this, "注册成功", Toast.LENGTH_SHORT).show();
//                } else {
//                    // 注册失败。status：错误码；desc：错误描述
//                    Toast.makeText(BaseApplication.this, "注册失败"+code+desc, Toast.LENGTH_SHORT).show();
//                    Log.i("xxx",""+code+"//"+desc);
//
//                }
//            }
//        });

//        try {
//            s = RsaCoder.decryptByPublicKey(pwd1);
//            Log.i("xxx","s:"+s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String md5 = MD.MD5(s);
//        Log.i("xxx","md5:"+md5);

        //登录
//        JMessageClient.login(name1, pwd1, new BasicCallback() {
//            @Override
//            public void gotResult(int i, String s) {
//                Log.i("xxx",""+i+"///"+s);
//
//            }
//        });
    }
    public static Context getContext(){
        return context;
    }
}
