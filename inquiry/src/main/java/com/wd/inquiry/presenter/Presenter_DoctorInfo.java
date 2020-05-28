package com.wd.inquiry.presenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.inquiry.bean.DoctorInfoBean;
import com.wd.inquiry.bean.FollowDoctorBean;
import com.wd.inquiry.icoolor.ICoolor_DoctorInfo;
import com.wd.inquiry.model.Model_DoctorInfo;

public class Presenter_DoctorInfo extends BasePresenter implements ICoolor_DoctorInfo.IPresenter {

    private Model_DoctorInfo model;

    public Presenter_DoctorInfo(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new Model_DoctorInfo();
    }

    @Override
    public void getDoctorInfo(int doctorId) {
        model.getDoctorInfo(doctorId, new ICoolor_DoctorInfo.DoctorInfoCallback() {
            @Override
            public void getSuccess(DoctorInfoBean doctorInfoBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_DoctorInfo.IView)view).getDoctorInfoSuccess(doctorInfoBean);
                }
            }
        });
    }

    @Override
    public void getFollowDoctor(int doctorId) {
        model.getFollowDoctor(doctorId, new ICoolor_DoctorInfo.FollowDoctorCallback() {
            @Override
            public void getSuccess(FollowDoctorBean followDoctorBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_DoctorInfo.IView)view).getFollowDoctorSuccess(followDoctorBean);
                }
            }
        });
    }

    @Override
    public void getCancelFollowDoctor(int doctorId) {
        model.getCancelFollowDoctor(doctorId, new ICoolor_DoctorInfo.CancelFollowCallback() {
            @Override
            public void getSuccess(FollowDoctorBean followDoctorBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_DoctorInfo.IView)view).getCancelFollowDoctorSuccess(followDoctorBean);
                }
            }
        });
    }
}
