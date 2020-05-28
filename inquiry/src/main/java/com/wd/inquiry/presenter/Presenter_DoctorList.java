package com.wd.inquiry.presenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.inquiry.bean.ConsultDoctorBean;
import com.wd.inquiry.bean.CurrentInquiryRecordBean;
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

    @Override
    public void getConsultDoctor(int doctorId) {
        model.getConsultDoctor(doctorId, new ICoolor_DoctorList.ConsultDoctorCallback() {
            @Override
            public void getSuccess(ConsultDoctorBean consultDoctorBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_DoctorList.IView)view).getConsultDoctorSuccess(consultDoctorBean);
                }
            }
        });
    }

    @Override
    public void getCurrentInquiryRecord() {
        model.getCurrentInquiryRecord(new ICoolor_DoctorList.CurrentInquiryRecorCallback() {
            @Override
            public void getSuccess(CurrentInquiryRecordBean currentInquiryRecordBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_DoctorList.IView)view).getCurrentInquiryRecordSuccess(currentInquiryRecordBean);
                }
            }
        });
    }
}
