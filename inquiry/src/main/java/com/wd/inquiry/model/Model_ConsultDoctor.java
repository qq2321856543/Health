package com.wd.inquiry.model;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.inquiry.base.InquiryApis;
import com.wd.inquiry.bean.ConsultDoctorBean;
import com.wd.inquiry.bean.DoctorListBean;
import com.wd.inquiry.icoolor.ICoolor_ConsultDoctor;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Model_ConsultDoctor implements ICoolor_ConsultDoctor.IModel {
    @Override
    public void getConsultDoctor(int doctorId, ICoolor_ConsultDoctor.ConsultDoctorCallback consultDoctorCallback) {
        getApis().getConsultDoctor(doctorId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<ConsultDoctorBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ConsultDoctorBean consultDoctorBean) {
                consultDoctorCallback.getSuccess(consultDoctorBean);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    public static InquiryApis getApis(){
        return RetrofitUtil.getInstance().getRetrofitServie(InquiryApis.class);
    }
}
