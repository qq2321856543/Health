package com.wd.inquiry.base;

import com.wd.inquiry.bean.ConsultDoctorBean;
import com.wd.inquiry.bean.CurrentInquiryRecordBean;
import com.wd.inquiry.bean.DoctorInfoBean;
import com.wd.inquiry.bean.DoctorListBean;
import com.wd.inquiry.bean.FindDepartmentBean;
import com.wd.inquiry.bean.FollowDoctorBean;
import com.wd.inquiry.bean.InquiryRecordListBean;
import com.wd.inquiry.bean.PushMessageBean;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InquiryApis {
    //查询科室列表
    @GET("health/share/knowledgeBase/v1/findDepartment")
    Observable<FindDepartmentBean> getFindDepartment();

    //查询问诊医生列表
    @GET("health/user/inquiry/v1/findDoctorList")
    Observable<DoctorListBean> getDoctorList(@Query("deptId") int deptId,@Query("condition") int condition,@Query("sortBy") int sortBy,@Query("page") int page,@Query("count") int count);

    //咨询医生
    @PUT("health/user/inquiry/verify/v1/consultDoctor")
    @FormUrlEncoded
    Observable<ConsultDoctorBean> getConsultDoctor(@Field("doctorId") int doctorId);

    //查询医生明细
    @GET("health/user/inquiry/v1/findDoctorInfo")
    Observable<DoctorInfoBean> getDoctorInfo(@Query("doctorId") int doctorId);

    //关注医生
    @POST("health/user/inquiry/verify/v1/followDoctor")
    @FormUrlEncoded
    Observable<FollowDoctorBean> getFollowDoctor(@Field("doctorId") int doctorId);

    //取消关注医生
    @DELETE("health/user/inquiry/verify/v1/cancelFollow")
    Observable<FollowDoctorBean> getCancelFollow(@Query("doctorId") int doctorId);

    //问诊发送消息
    @POST("health/user/inquiry/verify/v1/pushMessage")
    @FormUrlEncoded
    Observable<PushMessageBean> getPushMessage(@Field("inquiryId") int inquiryId,@Field("content") String content,@Field("type") int type,@Field("doctorId") int doctorId);

    //历史问诊聊天记录
    @GET("health/user/inquiry/verify/v1/findInquiryRecordList")
    Observable<InquiryRecordListBean> getInquiryRecordList(@Query("inquiryId") int inquiryId,@Query("page") int page,@Query("count") int count);

    //用户查看当前问诊
    @GET("health/user/inquiry/verify/v1/findCurrentInquiryRecord")
    Observable<CurrentInquiryRecordBean> getCurrentInquiryRecord();

}
