package com.wd.inquiry.icoolor;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.inquiry.bean.DoctorListBean;
import com.wd.inquiry.bean.FindDepartmentBean;

public interface ICoolor_DoctorList {
    interface IView extends IBaseView{
        //查询问诊医生列表
        void getDoctorListSuccess(DoctorListBean doctorListBean);
    }
    interface IPresenter{
        void getDoctorList(int deptId, int condition, int sortBy,int page,int count);
    }
    interface IModel{
        void getDoctorList(int deptId, int condition, int sortBy,int page,int count,DoctorListCallback doctorListCallback);
    }
    interface DoctorListCallback{
        void getSuccess(DoctorListBean doctorListBean);
    }
}
