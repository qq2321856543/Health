package com.wd.login.activity.util;

import com.wd.login.activity.bean.LoginLoginBean;
import com.wd.login.activity.bean.LoginRegisterBean;
import com.wd.login.activity.bean.LoginResetPwdBean;
import com.wd.login.activity.bean.LoginSendEmailCodeBean;
import com.wd.login.activity.bean.LogincheckCodeBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Time: 2020/5/21
 * Author: 王冠华
 * Description:
 */
public interface LoginApis {
    @POST("health/user/v1/sendOutEmailCode")
    @FormUrlEncoded
    Observable<LoginSendEmailCodeBean>getSendEmail(@Field("email") String email);
    @POST("health/user/v1/register")
    @FormUrlEncoded
    Observable<LoginRegisterBean>getRegister(@Field("email")String email,@Field("code")String code,@Field("pwd1")String pwd1,
                                             @Field("pwd2")String pwd2,@Field("invitationCode")String invitationCode);
    @POST("health/user/v1/login")
    @FormUrlEncoded
    Observable<LoginLoginBean>getLogin(@Field("email")String email,@Field("pwd")String pwd);
    @POST("health/user/v1/checkCode")
    @FormUrlEncoded
    Observable<LogincheckCodeBean>getCheckCode(@Field("email")String email,@Field("code")String code);
    @PUT("health/user/v1/resetUserPwd")
    @FormUrlEncoded
    Observable<LoginResetPwdBean>getResetPwd(@Field("email")String email,@Field("pwd1")String pwd1,
                                             @Field("pwd2")String pwd2);
}
