package com.wd.my.contract;


import com.wd.common.base.util.Base.IBaseView;
import com.wd.my.bean.ConsultationBean;

public interface CurrentConsultationContract {
    interface IView extends IBaseView {
        void getSuccess(ConsultationBean consultationBean);
        void getFailed(String msg);
    }
    interface IPersenter{
        void getCurrentConsultation();
    }
    interface IModel{
        void getCurrentConsultation(GetCurrentConsultationCallBack callBack);
        interface GetCurrentConsultationCallBack{
            void getSuccess(ConsultationBean consultationBean);
            void getFailed(String msg);
        }
    }
}
