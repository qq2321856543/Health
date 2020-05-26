package com.wd.patient.model;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.patient.bean.SearchBean;
import com.wd.patient.contract.PatientSearchContract;
import com.wd.patient.network.PatientApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.model
 * @ClassName: PatientSearchModel
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/5/24 19:22
 */
public class PatientSearchModel implements PatientSearchContract.IModel {
    public static PatientApis createrRetrofit(){
        return RetrofitUtil.getInstance().getRetrofitServie(PatientApis.class);
    }
    @Override
    public void getSearch(String keyWord, final searchCallBack callBack) {
        createrRetrofit().getSearch(keyWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
callBack.onSuccess(searchBean);
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
