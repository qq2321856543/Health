package com.wd.inquiry.presenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.inquiry.bean.FollowDoctorBean;
import com.wd.inquiry.icoolor.ICoolor_FollowDoctor;
import com.wd.inquiry.model.Model_FollowDoctor;

public class Presenter_FollowDoctor extends BasePresenter implements ICoolor_FollowDoctor.IPresenter {

    private Model_FollowDoctor model;

    public Presenter_FollowDoctor(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new Model_FollowDoctor();
    }

    @Override
    public void getFollowDoctor(int doctorId) {
        model.getFollowDoctor(doctorId, new ICoolor_FollowDoctor.FollowDoctorCallback() {
            @Override
            public void getSuccess(FollowDoctorBean followDoctorBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_FollowDoctor.IView)view).getFollowDoctorSuccess(followDoctorBean);
                }
            }
        });
    }

    @Override
    public void getCancelFollowDoctor(int doctorId) {
        model.getCancelFollowDoctor(doctorId, new ICoolor_FollowDoctor.CancelFollowCallback() {
            @Override
            public void getSuccess(FollowDoctorBean followDoctorBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_FollowDoctor.IView)view).getCancelFollowDoctorSuccess(followDoctorBean);
                }
            }
        });
    }
}
