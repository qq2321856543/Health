package com.wd.inquiry.presenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.inquiry.bean.ConsultDoctorBean;
import com.wd.inquiry.icoolor.ICoolor_ConsultDoctor;
import com.wd.inquiry.model.Model_ConsultDoctor;

public class Presenter_ConsultDoctor extends BasePresenter implements ICoolor_ConsultDoctor.IPresenter {

    private Model_ConsultDoctor model;

    public Presenter_ConsultDoctor(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new Model_ConsultDoctor();
    }

    @Override
    public void getConsultDoctor(int doctorId) {
        model.getConsultDoctor(doctorId, new ICoolor_ConsultDoctor.ConsultDoctorCallback() {
            @Override
            public void getSuccess(ConsultDoctorBean consultDoctorBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_ConsultDoctor.IView)view).getConsultDoctorSuccess(consultDoctorBean);
                }
            }
        });
    }
}
