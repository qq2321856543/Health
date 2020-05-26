package com.wd.patient.network;

import com.wd.patient.bean.BingXiangBean;
import com.wd.patient.bean.BingYouQuanBean;
import com.wd.patient.bean.DengBean;
import com.wd.patient.bean.KeLieBean;
import com.wd.patient.bean.SearchBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.network
 * @ClassName: PatientApis
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/5/21 20:53
 */
public interface PatientApis {
    @GET("health/user/sickCircle/v1/findSickCircleList")
    Observable<BingYouQuanBean> getBing(@Query("departmentId")int departmentId, @Query("page")int page, @Query("count")int count);
    //病友圈详情
    @GET("health/user/sickCircle/v1/findSickCircleInfo")
    Observable<BingXiangBean> getBingXiang(@Query("sickCircleId")int sickCircleId);

    //登录
    @POST("health/user/v1/login")
    Observable<DengBean> getDeng(@Query("email")String email, @Query("pwd")String pwd);

    //根据关键字查询
    @GET("health/user/sickCircle/v1/searchSickCircle")
    Observable<SearchBean> getSearch(@Query("keyWord")String keyWord);

    //查询科室列表
    @GET("health/share/knowledgeBase/v1/findDepartment")
    Observable<KeLieBean> getKeLie();
}
