package com.wd.inquiry.presenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.inquiry.bean.DoctorListBean;
import com.wd.inquiry.icoolor.ICoolor_DoctorList;
import com.wd.inquiry.model.Model_DoctorList;

public class Presenter_DoctorList extends BasePresenter implements ICoolor_DoctorList.IPresenter {

    private Model_DoctorList model;

    public Presenter_DoctorList(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new Model_DoctorList();
    }

    @Override
    public void getDoctorList(int deptId, int condition, int sortBy, int page, int count) {
        model.getDoctorList(deptId, condition, sortBy, page, count, new ICoolor_DoctorList.DoctorListCallback() {
            @Override
            public void getSuccess(DoctorListBean doctorListBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_DoctorList.IView)view).getDoctorListSuccess(doctorListBean);
                }
            }
        });
    }
}
