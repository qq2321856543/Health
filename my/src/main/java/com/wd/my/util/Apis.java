package com.wd.my.util;




import com.wd.my.bean.AddArchivesBean;
import com.wd.my.bean.AddUserArchivesBean;
import com.wd.my.bean.ConsultationBean;
import com.wd.my.bean.DeleteUserArchivesBean;
import com.wd.my.bean.EndInquiryBean;
import com.wd.my.bean.FindUserArchivesBean;
import com.wd.my.bean.SignInBean;
import com.wd.my.bean.TaskBean;
import com.wd.my.bean.UpImageBean;
import com.wd.my.bean.UpLoadBean;
import com.wd.my.bean.UpdateUserArchivesBean;
import com.wd.my.bean.UserInfoBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    //结束问诊
    @PUT("health/user/inquiry/verify/v1/endInquiry")
    Observable<EndInquiryBean> getEndInquiry(@Query("recordId") int recordId);

    //用户查看自己的档案
    @GET("health/user/verify/v1/findUserArchives")
    Observable<FindUserArchivesBean> getFindUserArchives();

    //添加用户档案
    @POST("health/user/verify/v1/addUserArchives")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Observable<AddUserArchivesBean> getAddUserArchives(@Body RequestBody body);

    //修改用户档案
    @PUT("health/user/verify/v1/updateUserArchives")
    Observable<UpdateUserArchivesBean> getUpdateUserArchives(@Query("archivesId") int archivesId,@Query("diseaseMain") String diseaseMain,@Query("diseaseNow") String diseaseNow,@Query("diseaseBefore") String diseaseBefore,@Query("treatmentHospitalRecent") String treatmentHospitalRecent,@Query("treatmentProcess") String treatmentProcess,@Query("treatmentStartTime") String treatmentStartTime,@Query("treatmentEndTime") String treatmentEndTime);

    //删除用户档案
    @DELETE("health/user/verify/v1/deleteUserArchives")
    Observable<DeleteUserArchivesBean> getDeleteUserArchives(@Query("archivesId") int archivesId);

    //上传用户档案相关图片
    @POST("health/user/verify/v1/uploadArchivesPicture")
    Observable<UpImageBean> getUpImage(@Body RequestBody body);

    /**
     * 查询用户任务
     * */
    @GET("health/user/verify/v1/findUserTaskList")
    Observable<TaskBean> getTask();

    /**
     *做任务
     * */
    @POST("health/user/verify/v1/doTask")
    @FormUrlEncoded
    Observable<AddArchivesBean> doTask(@Field("taskId") int taskId);

    /**
     * 领取奖励
     * */
    @POST("health/user/verify/v1/receiveReward")
    @FormUrlEncoded
    Observable<AddArchivesBean> receiveReward(@Field("taskId") int taskId);

    /**
     * 查询用户连续签到天数
     * */
    @GET("health/user/verify/v1/findUserSign")
    Observable<SignInBean> getSignDay();
}
