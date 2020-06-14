package com.wd.patient.model;

import android.widget.Toast;

import com.wd.common.base.util.Base.BaseApplication;
import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.patient.bean.BingXiangBean;
import com.wd.patient.bean.BingYouQuanBean;
import com.wd.patient.bean.KeLieBean;
import com.wd.patient.contract.PatientContract;
import com.wd.patient.network.PatientApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.model
 * @ClassName: PatientModel
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/5/21 22:32
 */
public class PatientModel implements PatientContract.IModel {
    @Override
    public void getPatient(int departmentId, int page, int count, final patientCallBack callBack) {
        createrRetrofit().getBing(departmentId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BingYouQuanBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BingYouQuanBean bingXiangBean) {
                        callBack.onPatientSuccess(bingXiangBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                            e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getBingXiang(int sickCircleId, final bingXiangCallBack callBack) {
        createrRetrofit().getBingXiang(sickCircleId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BingXiangBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BingXiangBean bingXiangBean) {
                        callBack.onSuccess(bingXiangBean);
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
    public void getKeLie(final keLieCallBack callBack) {
        createrRetrofit().getKeLie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KeLieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KeLieBean bingXiangBean) {
                        callBack.onKeLieSuccess(bingXiangBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static PatientApis createrRetrofit(){
        return RetrofitUtil.getInstance().getRetrofitServie(PatientApis.class);
    }
}
