package com.wd.home.utils;

import com.wd.home.bean.HomeBannerBean;
import com.wd.home.bean.HomeDepartmentBean;
import com.wd.home.bean.HomeDetailBean;
import com.wd.home.bean.HomeDetailCollectionBean;
import com.wd.home.bean.HomeDetailDeleteBean;
import com.wd.home.bean.HomeDiseaseDetailBean;
import com.wd.home.bean.HomeDrugsDetailBean;
import com.wd.home.bean.HomeDrugsKnowledgeBean;
import com.wd.home.bean.HomeFindDiseaseBean;
import com.wd.home.bean.HomeFindDrugsCategoryBean;
import com.wd.home.bean.HomeFindListBean;
import com.wd.home.bean.HomePageSearchBean;
import com.wd.home.bean.HomePlateListBean;
import com.wd.home.bean.HomeSearchBean;
import com.wd.home.bean.PopularSearchesBean;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    //资讯详情
    @GET("health/share/information/v1/findInformation")
    Observable<HomeDetailBean>getHomeDetail(@Query("infoId")int infoId);
    //资讯详情收藏
    @POST("health/user/verify/v1/addInfoCollection")
    Observable<HomeDetailCollectionBean>getDetailCollection(@Query("infoId")int infoId);
    @DELETE("health/user/verify/v1/cancelInfoCollection")
    Observable<HomeDetailDeleteBean>getCanelCollection(@Query("infoId")int infoId);
    //查询科室列表
    @GET("health/share/knowledgeBase/v1/findDepartment")
    Observable<HomeDepartmentBean>getHomeDepartment();
    //根据科室查询对应病症
    @GET("health/share/knowledgeBase/v1/findDiseaseCategory")
    Observable<HomeFindDiseaseBean>getFindDisease(@Query("departmentId")int departmentId);
    //常见病症详情
    @GET("health/share/knowledgeBase/v1/findDiseaseKnowledge")
    Observable<HomeDiseaseDetailBean>getHomeDiseaseDetail(@Query("id")int id);
    //药品科目分类列表查询
    @GET("health/share/knowledgeBase/v1/findDrugsCategoryList")
    Observable<HomeFindDrugsCategoryBean>getHomeDrugsCategory();
    //根据药品科目查询药品
    @GET("health/share/knowledgeBase/v1/findDrugsKnowledgeList")
    Observable<HomeDrugsKnowledgeBean>getHomeDrugsKnowledge(@Query("drugsCategoryId")int drugsCategoryId,@Query("page")int page,@Query("count")int count);
    //药品详情
    @GET("health/share/knowledgeBase/v1/findDrugsKnowledge")
    Observable<HomeDrugsDetailBean>getHomeDrugsDetail(@Query("id")int id);
    //首页搜索
    @GET("health/share/v1/homePageSearch")
    Observable<HomePageSearchBean> homePageSearch(@Query("keyWord")String keyWord);
    //热门搜索
    @GET("health/share/v1/popularSearch")
    Observable<PopularSearchesBean> popularSearch();


}
