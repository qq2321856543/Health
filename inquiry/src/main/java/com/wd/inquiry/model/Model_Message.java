package com.wd.inquiry.model;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.inquiry.base.InquiryApis;
import com.wd.inquiry.bean.CurrentInquiryRecordBean;
import com.wd.inquiry.bean.InquiryRecordListBean;
import com.wd.inquiry.bean.PushMessageBean;
import com.wd.inquiry.icoolor.ICoolor_Message;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Model_Message implements ICoolor_Message.IModel {
    @Override
    public void getPushMessage(int inquiryId, String content, int type, int doctorId, ICoolor_Message.PushMessageCallback pushMessageCallback) {
        getApis().getPushMessage(inquiryId,content,type,doctorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PushMessageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PushMessageBean pushMessageBean) {
                        pushMessageCallback.getSuccess(pushMessageBean);

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
    public void getInquiryRecordList(int inquiryId, int page, int count, ICoolor_Message.InquiryRecordListCallback inquiryRecordListCallback) {
        getApis().getInquiryRecordList(inquiryId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<InquiryRecordListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(InquiryRecordListBean inquiryRecordListBean) {
                        inquiryRecordListCallback.getSuccess(inquiryRecordListBean);

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
    public void getCurrentInquiryRecord(ICoolor_Message.CurrentInquiryRecorCallback currentInquiryRecorCallback) {
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
