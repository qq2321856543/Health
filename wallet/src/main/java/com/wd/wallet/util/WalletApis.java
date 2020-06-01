package com.wd.wallet.util;

import com.wd.wallet.bean.WalletRecordBean;
import com.wd.wallet.bean.WalletUserBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
}
