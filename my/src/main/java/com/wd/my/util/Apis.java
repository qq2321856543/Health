package com.wd.my.util;




import com.wd.my.bean.ConsultationBean;
import com.wd.my.bean.SignInBean;
import com.wd.my.bean.UpLoadBean;
import com.wd.my.bean.UserInfoBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface Apis{
    /**
     * 上传头像
     * */
    @POST("health/user/verify/v1/modifyHeadPic")
    Observable<UpLoadBean> getUpLoadHeadPic(@Body RequestBody body);

    /**
     * 查询用户信息
     */
    @GET("health/user/verify/v1/getUserInfoById")
    Observable<UserInfoBean> getUserInfo();

    /**
     * 查询是否签到
     * */
    @GET("health/user/verify/v1/whetherSignToday")
    Observable<SignInBean> getSignInDay();

    /**
     * 签到
     * */
    @POST("health/user/verify/v1/addSign")
    Observable<SignInBean> signIn();

    /**
     * 当前问诊
     * */
    @GET("health/user/inquiry/verify/v1/findCurrentInquiryRecord")
    Observable<ConsultationBean> getCurrentConsultation();

    /**
     * 历史问诊
     * */
    @GET("health/user/inquiry/verify/v1/findHistoryInquiryRecord")
    Observable<ConsultationBean> getHtistoryConsultation(@Query("page") int page, @Query("count") int count);
}
