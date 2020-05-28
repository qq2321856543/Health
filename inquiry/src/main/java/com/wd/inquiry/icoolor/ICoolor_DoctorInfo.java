package com.wd.inquiry.icoolor;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.inquiry.bean.DoctorInfoBean;
import com.wd.inquiry.bean.DoctorListBean;
import com.wd.inquiry.bean.FollowDoctorBean;

public interface ICoolor_DoctorInfo {
    interface IView extends IBaseView{
        //医生详情
        void getDoctorInfoSuccess(DoctorInfoBean doctorInfoBean);
        //关注医生
        void getFollowDoctorSuccess(FollowDoctorBean followDoctorBean);
        //取消关注医生
        void getCancelFollowDoctorSuccess(FollowDoctorBean followDoctorBean);
    }
    interface IPresenter{
        void getDoctorInfo(int doctorId);
        void getFollowDoctor(int doctorId);
        void getCancelFollowDoctor(int doctorId);
    }
    interface IModel{
        void getDoctorInfo(int doctorId, DoctorInfoCallback doctorInfoCallback);
        void getFollowDoctor(int doctorId, FollowDoctorCallback followDoctorCallback);
        void getCancelFollowDoctor(int doctorId, CancelFollowCallback cancelFollowCallback);
    }
    interface DoctorInfoCallback{
        void getSuccess(DoctorInfoBean doctorInfoBean);
    }
    interface FollowDoctorCallback{
        void getSuccess(FollowDoctorBean followDoctorBean);
    }
    interface CancelFollowCallback{
        void getSuccess(FollowDoctorBean followDoctorBean);
    }
}
