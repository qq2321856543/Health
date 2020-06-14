package com.wd.patient.model;

import com.wd.patient.bean.IllnessBean;
import com.wd.patient.bean.KeLieBean;
import com.wd.patient.bean.PublishPatientBean;
import com.wd.patient.contract.WritePatientContract;
import com.wd.patient.network.PatientApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.model
 * @ClassName: WritePatientModel
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/6/7 9:53
 */
public class WritePatientModel implements WritePatientContract.IModel {
    @Override
    public void showDepartment(showDepartmentCallback callback) {
        PatientApis.createrRetrofit().getKeLie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<KeLieBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(KeLieBean keLieBean) {
callback.onShowDepartmentSuccess(keLieBean);
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
    public void showIllness(int departmentId, showIllnessCallBack callBack) {
        PatientApis.createrRetrofit().showIllness(departmentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IllnessBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(IllnessBean illnessBean) {
                        callBack.onShowIllnessSuccess(illnessBean);
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
    public void publishPatient(RequestBody requestBody, publishPatientCallBack callBack) {
        PatientApis.createrRetrofit().publishPatient(requestBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PublishPatientBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PublishPatientBean publishPatientBean) {
                        callBack.onPublishPatientSuccess(publishPatientBean);
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
