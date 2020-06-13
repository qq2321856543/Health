package com.wd.patient.model;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.patient.bean.SearchBean;
import com.wd.patient.bean.UserPatientBean;
import com.wd.patient.contract.PatientSearchContract;
import com.wd.patient.contract.ShowUserPatientContract;
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
public class ShowUserPatientModel implements ShowUserPatientContract.IModel {

    @Override
    public void showUserPatient(int patientUserId, int page, int count, showUserPatientCallBack callBack) {
        PatientApis.createrRetrofit().showUserPatient(patientUserId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserPatientBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserPatientBean userPatientBean) {
                        callBack.onSuccess(userPatientBean);
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
