package com.wd.my.util;


import com.wd.common.base.util.util.RetrofitUtil;

public class CreatApis {
    public static Apis creatClass(){
        return RetrofitUtil.getInstance().getRetrofitServie(Apis.class);
    }
}
