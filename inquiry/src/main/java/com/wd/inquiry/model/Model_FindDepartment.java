package com.wd.inquiry.model;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.inquiry.base.InquiryApis;
import com.wd.inquiry.bean.FindDepartmentBean;
import com.wd.inquiry.icoolor.ICoolor_FindDepartment;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Model_FindDepartment implements ICoolor_FindDepartment.IModel {
    @Override
    public void getFindDepartmentSuccess(ICoolor_FindDepartment.FindDepartmentCallback findDepartmentCallback) {
        getApis().getFindDepartment()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindDepartmentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindDepartmentBean findDepartmentBean) {
                        findDepartmentCallback.getSuccess(findDepartmentBean);
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
