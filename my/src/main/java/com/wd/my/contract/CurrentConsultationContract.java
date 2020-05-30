package com.wd.my.contract;


import com.wd.common.base.util.Base.IBaseView;
import com.wd.my.bean.ConsultationBean;
import com.wd.my.bean.EndInquiryBean;

public interface CurrentConsultationContract {
    interface IView extends IBaseView {
        void getSuccess(ConsultationBean consultationBean);
        void getFailed(String msg);
        void  getEndInquirySuccess(EndInquiryBean endInquiryBean);
    }
    interface IPersenter{
        void getCurrentConsultation();
        void  getEndInquiry(int recordId);

    }
    interface IModel{
        void getCurrentConsultation(GetCurrentConsultationCallBack callBack);
        void  getEndInquiry(int recordId,EndInquiryCallBack endInquiryCallBack);

        interface GetCurrentConsultationCallBack{
            void getSuccess(ConsultationBean consultationBean);
            void getFailed(String msg);
        }
        interface EndInquiryCallBack{
            void getSuccess(EndInquiryBean endInquiryBean);
        }
    }
}
