package com.wd.my.model;


import com.wd.my.bean.ConsultationBean;
import com.wd.my.contract.HistoryConsultationContract;
import com.wd.my.util.CreatApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HistoryConsultationModel implements HistoryConsultationContract.IModel{
    @Override
    public void getHistoryConsultation(int page,int count,final GetHistoryConsultationCallBack callBack) {
        CreatApis.creatClass().getHtistoryConsultation(page,count)
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
}
