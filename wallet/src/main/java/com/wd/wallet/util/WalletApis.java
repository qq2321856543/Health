package com.wd.wallet.util;

import com.wd.wallet.bean.WalletAliPayBean;
import com.wd.wallet.bean.WalletDoctorBean;
import com.wd.wallet.bean.WalletDrawCashBean;
import com.wd.wallet.bean.WalletRecordBean;
import com.wd.wallet.bean.WalletUserBean;
import com.wd.wallet.bean.WalletWxPayBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Time: 2020/6/1
 * Author: 王冠华
 * Description:
 */
public interface WalletApis {
    @GET("health/user/verify/v1/findUserConsumptionRecordList")
    Observable<WalletRecordBean>getRecord(@Query("page")int page,@Query("count")int count);
    @GET("health/user/verify/v1/findUserWallet")
    Observable<WalletUserBean>getUserMoney();
    //微信充值
    @POST("health/user/verify/v1/recharge")
    @FormUrlEncoded
    Observable<WalletWxPayBean>getWxPay(@Field("money")double money,@Field("payType")int payType);
    //微信充值
    @POST("health/user/verify/v1/recharge")
    @FormUrlEncoded
    Observable<WalletAliPayBean>getAliPay(@Field("money")double money, @Field("payType")int payType);
    @POST("health/user/verify/v1/drawCash")
    @FormUrlEncoded
    Observable<WalletDrawCashBean>getDrawCash(@Field("money") int money);
    //充值成功展示医生
    @GET("health/user/inquiry/v1/findDoctorList")
    Observable<WalletDoctorBean>getDoctor(@Query("deptId")int deptId,@Query("condition")int condition,@Query("page")int page,@Query("count")int count);
}
