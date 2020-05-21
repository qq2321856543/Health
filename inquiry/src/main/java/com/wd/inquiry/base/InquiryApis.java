package com.wd.inquiry.base;

import com.wd.inquiry.bean.DoctorListBean;
import com.wd.inquiry.bean.FindDepartmentBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InquiryApis {
    //查询科室列表
    @GET("health/share/knowledgeBase/v1/findDepartment")
    Observable<FindDepartmentBean> getFindDepartment();

    //查询问诊医生列表
    @GET("health/user/inquiry/v1/findDoctorList")
    Observable<DoctorListBean> getDoctorList(@Query("deptId") int deptId,@Query("condition") int condition,@Query("sortBy") int sortBy,@Query("page") int page,@Query("count") int count);

}
