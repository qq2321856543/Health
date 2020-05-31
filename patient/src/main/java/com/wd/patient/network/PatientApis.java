package com.wd.patient.network;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.patient.bean.BingXiangBean;
import com.wd.patient.bean.BingYouQuanBean;
import com.wd.patient.bean.DengBean;
import com.wd.patient.bean.KeLieBean;
import com.wd.patient.bean.PatientCommentBean;
import com.wd.patient.bean.PublishCommentBean;
import com.wd.patient.bean.SearchBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    public static PatientApis createrRetrofit(){
        return RetrofitUtil.getInstance().getRetrofitServie(PatientApis.class);
    }

    @GET("health/user/sickCircle/v1/findSickCircleList")
    Observable<BingYouQuanBean> getBing(@Query("departmentId")int departmentId, @Query("page")int page, @Query("count")int count);
    //病友圈详情
    @GET("health/user/sickCircle/v1/findSickCircleInfo")
    Observable<BingXiangBean> getBingXiang(@Query("sickCircleId")int sickCircleId);

    //根据关键字查询
    @GET("health/user/sickCircle/v1/searchSickCircle")
    Observable<SearchBean> getSearch(@Query("keyWord")String keyWord);

    //查询科室列表
    @GET("health/share/knowledgeBase/v1/findDepartment")
    Observable<KeLieBean> getKeLie();

    //查询病友圈评论列表
    @GET("health/user/sickCircle/v1/findSickCircleCommentList")
    Observable<PatientCommentBean> showComment(@Query("sickCircleId")int sickCircleId,@Query("page")int page,@Query("count")int count);

    //病友圈发表评论
    @POST("health/user/sickCircle/verify/v1/publishComment")
    @FormUrlEncoded
    Observable<PublishCommentBean> sendMessages(@Field("sickCircleId") int sickCircleId,@Field("content") String content);
}
