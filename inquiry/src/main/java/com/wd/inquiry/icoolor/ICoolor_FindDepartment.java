package com.wd.inquiry.icoolor;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.inquiry.bean.FindDepartmentBean;

public interface ICoolor_FindDepartment {
    interface IView extends IBaseView{
        //查询科室列表
        void getFindDepartmentSuccess(FindDepartmentBean findDepartmentBean);
    }
    interface IPresenter{
        void getFindDepartmentSuccess();
    }
    interface IModel{
        void getFindDepartmentSuccess(FindDepartmentCallback findDepartmentCallback);
    }
    interface FindDepartmentCallback{
        void getSuccess(FindDepartmentBean findDepartmentBean);
    }
}
