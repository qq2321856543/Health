package com.wd.home.utils;

import com.wd.home.bean.HomeBannerBean;
import com.wd.home.bean.HomeDetailBean;
import com.wd.home.bean.HomeFindListBean;
import com.wd.home.bean.HomePlateListBean;
import com.wd.home.bean.HomeSearchBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Time: 2020/5/22
 * Author: 王冠华
 * Description:
 */
public interface HomeApis {
    @GET("health/share/v1/bannersShow")
    Observable<HomeBannerBean>getBanner();
    @GET("health/share/v1/homePageSearch")
    Observable<HomeSearchBean>getHomeSearch(@Query("keyWord")String keyWord);
    @GET("health/share/information/v1/findInformationPlateList")
    Observable<HomePlateListBean>getHomePlateList();
    @GET("health/share/information/v1/findInformationList")
    Observable<HomeFindListBean>getHomeList(@Query("plateId")int plateId,@Query("page")int page,@Query("count")int count);
    @GET("health/share/information/v1/findInformation")
    Observable<HomeDetailBean>getHomeDetail(@Query("infoId")int infoId);
}
