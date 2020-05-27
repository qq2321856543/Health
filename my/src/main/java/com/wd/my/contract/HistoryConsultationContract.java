package com.wd.my.contract;


import com.wd.common.base.util.Base.IBaseView;
import com.wd.my.bean.ConsultationBean;

public interface HistoryConsultationContract {
    interface IView extends IBaseView {
        void getSuccess(ConsultationBean consultationBean);
        void getFailed(String msg);
    }
    interface IPersenter{
        void getHistoryConsultation(int page, int count);
    }
    interface IModel{
        void getHistoryConsultation(int page, int count, GetHistoryConsultationCallBack callBack);
        interface GetHistoryConsultationCallBack{
            void getSuccess(ConsultationBean consultationBean);
            void getFailed(String msg);
        }
    }
}
