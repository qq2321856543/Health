package com.wd.inquiry.model;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.inquiry.base.InquiryApis;
import com.wd.inquiry.bean.FollowDoctorBean;
import com.wd.inquiry.icoolor.ICoolor_FollowDoctor;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Model_FollowDoctor implements ICoolor_FollowDoctor.IModel {
    @Override
    public void getFollowDoctor(int doctorId, ICoolor_FollowDoctor.FollowDoctorCallback followDoctorCallback) {
        getApis().getFollowDoctor(doctorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowDoctorBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowDoctorBean followDoctorBean) {
                        followDoctorCallback.getSuccess(followDoctorBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getCancelFollowDoctor(int doctorId, ICoolor_FollowDoctor.CancelFollowCallback cancelFollowCallback) {
        getApis().getCancelFollow(doctorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FollowDoctorBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FollowDoctorBean followDoctorBean) {
                        cancelFollowCallback.getSuccess(followDoctorBean);
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
