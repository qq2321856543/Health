package com.wd.my.persenter;


import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.my.bean.ConsultationBean;
import com.wd.my.contract.HistoryConsultationContract;
import com.wd.my.model.HistoryConsultationModel;

public class HistoryConsultationPeresenter extends BasePresenter implements HistoryConsultationContract.IPersenter {

    private HistoryConsultationModel mHistoryConsultationModel;
    private HistoryConsultationContract.IView mView;

    public HistoryConsultationPeresenter(IBaseView iBaseView) {
        super(iBaseView);
    }


    @Override
    public void initModel() {
        mHistoryConsultationModel = new HistoryConsultationModel();
        IBaseView iView = getView();
        if (iView != null && iView instanceof HistoryConsultationContract.IView) {
            mView = (HistoryConsultationContract.IView) iView;
        }
    }
    @Override
    public void getHistoryConsultation(int page,int count) {
        mHistoryConsultationModel.getHistoryConsultation(page,count,new HistoryConsultationContract.IModel.GetHistoryConsultationCallBack() {
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
}
