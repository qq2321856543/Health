package com.wd.inquiry.icoolor;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.inquiry.bean.ConsultDoctorBean;
import com.wd.inquiry.bean.CurrentInquiryRecordBean;
import com.wd.inquiry.bean.DoctorListBean;
import com.wd.inquiry.bean.FindDepartmentBean;

public interface ICoolor_DoctorList {
    interface IView extends IBaseView{
        //查询问诊医生列表
        void getDoctorListSuccess(DoctorListBean doctorListBean);
        //咨询医生
        void getConsultDoctorSuccess(ConsultDoctorBean consultDoctorBean);
        //当前问诊医生
        void  getCurrentInquiryRecordSuccess(CurrentInquiryRecordBean currentInquiryRecordBean);
    }
    interface IPresenter{
        void getDoctorList(int deptId, int condition, int sortBy,int page,int count);
        void getConsultDoctor(int doctorId);
        void getCurrentInquiryRecord();
    }
    interface IModel{
        void getDoctorList(int deptId, int condition, int sortBy,int page,int count,DoctorListCallback doctorListCallback);
        void getConsultDoctor(int doctorId, ConsultDoctorCallback consultDoctorCallback);
        void getCurrentInquiryRecord(CurrentInquiryRecorCallback currentInquiryRecorCallback);

    }
    interface DoctorListCallback{
        void getSuccess(DoctorListBean doctorListBean);
    }
    interface ConsultDoctorCallback{
        void getSuccess(ConsultDoctorBean consultDoctorBean);
    }
    interface CurrentInquiryRecorCallback{
        void getSuccess(CurrentInquiryRecordBean currentInquiryRecordBean);
    }
}
