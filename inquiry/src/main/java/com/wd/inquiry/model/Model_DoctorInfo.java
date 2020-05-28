package com.wd.inquiry.model;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.inquiry.base.InquiryApis;
import com.wd.inquiry.bean.DoctorInfoBean;
import com.wd.inquiry.bean.FollowDoctorBean;
import com.wd.inquiry.icoolor.ICoolor_DoctorInfo;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Model_DoctorInfo implements ICoolor_DoctorInfo.IModel {
    @Override
    public void getDoctorInfo(int doctorId, ICoolor_DoctorInfo.DoctorInfoCallback doctorInfoCallback) {
        getApis().getDoctorInfo(doctorId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<DoctorInfoBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(DoctorInfoBean doctorInfoBean) {
                doctorInfoCallback.getSuccess(doctorInfoBean);
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
    public void getFollowDoctor(int doctorId, ICoolor_DoctorInfo.FollowDoctorCallback followDoctorCallback) {
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
    public void getCancelFollowDoctor(int doctorId, ICoolor_DoctorInfo.CancelFollowCallback cancelFollowCallback) {
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
