package com.wd.my.model;

import com.wd.my.bean.ConsultationBean;
import com.wd.my.bean.EndInquiryBean;
import com.wd.my.contract.CurrentConsultationContract;
import com.wd.my.util.CreatApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class CurrentConsultationModel implements CurrentConsultationContract.IModel {
    @Override
    public void getCurrentConsultation(final GetCurrentConsultationCallBack callBack) {
        CreatApis.creatClass().getCurrentConsultation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ConsultationBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ConsultationBean consultationBean) {
                        callBack.getSuccess(consultationBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.getFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getEndInquiry(int recordId, EndInquiryCallBack endInquiryCallBack) {
        CreatApis.creatClass().getEndInquiry(recordId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<EndInquiryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(EndInquiryBean endInquiryBean) {
                        endInquiryCallBack.getSuccess(endInquiryBean);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
