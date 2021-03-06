package com.wd.inquiry.model;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.inquiry.base.InquiryApis;
import com.wd.inquiry.bean.ConsultDoctorBean;
import com.wd.inquiry.bean.CurrentInquiryRecordBean;
import com.wd.inquiry.bean.DoctorListBean;
import com.wd.inquiry.icoolor.ICoolor_DoctorList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Model_DoctorList implements ICoolor_DoctorList.IModel {
    @Override
    public void getDoctorList(int deptId, int condition, int sortBy, int page, int count, ICoolor_DoctorList.DoctorListCallback doctorListCallback) {
        getApis().getDoctorList(deptId,condition,sortBy,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DoctorListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DoctorListBean doctorListBean) {
                        doctorListCallback.getSuccess(doctorListBean);
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
    public void getConsultDoctor(int doctorId, ICoolor_DoctorList.ConsultDoctorCallback consultDoctorCallback) {
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

    @Override
    public void getCurrentInquiryRecord(ICoolor_DoctorList.CurrentInquiryRecorCallback currentInquiryRecorCallback) {
        getApis().getCurrentInquiryRecord()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CurrentInquiryRecordBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CurrentInquiryRecordBean currentInquiryRecordBean) {
                        currentInquiryRecorCallback.getSuccess(currentInquiryRecordBean);

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
