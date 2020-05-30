package com.wd.my.persenter;


import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.my.bean.ConsultationBean;
import com.wd.my.bean.EndInquiryBean;
import com.wd.my.contract.CurrentConsultationContract;
import com.wd.my.model.CurrentConsultationModel;

public class CurrentConsultationPeresenter extends BasePresenter implements CurrentConsultationContract.IPersenter {

    private CurrentConsultationModel mCurrentConsultationModel;
    private CurrentConsultationContract.IView mView;

    public CurrentConsultationPeresenter(IBaseView iBaseView) {
        super(iBaseView);
    }


    @Override
    public void initModel() {
        mCurrentConsultationModel = new CurrentConsultationModel();
        IBaseView iView = getView();
        if (iView != null && iView instanceof CurrentConsultationContract.IView) {
            mView = (CurrentConsultationContract.IView) iView;
        }
    }

    @Override
    public void getCurrentConsultation() {
        mCurrentConsultationModel.getCurrentConsultation(new CurrentConsultationContract.IModel.GetCurrentConsultationCallBack() {
            @Override
            public void getSuccess(ConsultationBean consultationBean) {
                mView.getSuccess(consultationBean);
            }

            @Override
            public void getFailed(String msg) {
                mView.getFailed(msg);
            }
        });
    }

    @Override
    public void getEndInquiry(int recordId) {
        mCurrentConsultationModel.getEndInquiry(recordId, new CurrentConsultationContract.IModel.EndInquiryCallBack() {
            @Override
            public void getSuccess(EndInquiryBean endInquiryBean) {
                mView.getEndInquirySuccess(endInquiryBean);
            }
        });
    }
}
