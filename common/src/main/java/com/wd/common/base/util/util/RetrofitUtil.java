package com.wd.common.base.util.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;


import com.wd.common.base.util.Base.BaseApplication;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ProjectName: WeiDuMovie
 * @Package: com.bw.movie.util
 * @ClassName: RetrofitUtil
 * @Description:
 * @Author: 郑昊菲
 * @CreateDate: 2020.5.18 0017 14:12
 * @UpdateUser:
 * @UpdateDate: 2020.5.18 0017 14:12
 * @UpdateRemark:
 * @Version: 1.0
 */
public class RetrofitUtil{
    private HashMap<String,String> mRetroFitHashMap=new HashMap<>();

    private RetrofitUtil() {

    }
    private static class SingleInstance{
        private static RetrofitUtil sRetrofitUtil = new RetrofitUtil();
    }
    public static RetrofitUtil getInstance(){
        return SingleInstance.sRetrofitUtil;
    }
    String BaseUrl="http://mobile.bwstudent.com/";
    private Retrofit createrRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .addInterceptor(new HeadInteceptor())
                .addInterceptor(httpLoggingInterceptor);
        OkHttpClient okHttpClient = builder.build();
        //
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    private class HeadInteceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String userId = SPUtils.getString(BaseApplication.getContext(), SPUtils.USERINFO_NAME, SPUtils.USERINFO_KEY_USER_ID);
            String sessionId = SPUtils.getString(BaseApplication.getContext(), SPUtils.USERINFO_NAME, SPUtils.USERINFO_KEY_SESSION_ID);

            if (TextUtils.isEmpty(userId)||TextUtils.isEmpty(sessionId)){
                Request build = request.newBuilder()
                        .addHeader("ak", "0110010010000")
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .build();
                return chain.proceed(build);
                //
            }
            Request build = request.newBuilder()
                    .addHeader("userId", userId)
                    .addHeader("sessionId", sessionId)
                    .addHeader("ak", "0110010010000")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            return chain.proceed(build);
        }
    }
    public Boolean isWifi(Context context){
        ConnectivityManager cm= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null) {
            return true;
        }
        return false;
    }
    public <T>T getRetrofitServie(Class<T> cls){
        return createrRetrofit().create(cls);
    }

}
