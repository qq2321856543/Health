package com.wd.inquiry.icoolor;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.inquiry.bean.ConsultDoctorBean;
import com.wd.inquiry.bean.DoctorListBean;

public interface ICoolor_ConsultDoctor {
    interface IView extends IBaseView{
        //查询问诊医生列表
        void getConsultDoctorSuccess(ConsultDoctorBean consultDoctorBean);
    }
    interface IPresenter{
        void getConsultDoctor(int doctorId);
    }
    interface IModel{
        void getConsultDoctor(int doctorId, ConsultDoctorCallback consultDoctorCallback);
    }
    interface ConsultDoctorCallback{
        void getSuccess(ConsultDoctorBean consultDoctorBean);
    }
}
