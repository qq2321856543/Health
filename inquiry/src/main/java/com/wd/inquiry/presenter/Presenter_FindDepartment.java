package com.wd.inquiry.presenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.inquiry.bean.FindDepartmentBean;
import com.wd.inquiry.icoolor.ICoolor_FindDepartment;
import com.wd.inquiry.model.Model_FindDepartment;

public class Presenter_FindDepartment extends BasePresenter implements ICoolor_FindDepartment.IPresenter {

    private Model_FindDepartment model;

    public Presenter_FindDepartment(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new Model_FindDepartment();
    }

    @Override
    public void getFindDepartmentSuccess() {
        model.getFindDepartmentSuccess(new ICoolor_FindDepartment.FindDepartmentCallback() {
            @Override
            public void getSuccess(FindDepartmentBean findDepartmentBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_FindDepartment.IView)view).getFindDepartmentSuccess(findDepartmentBean);
                }
            }
        });
    }
}
