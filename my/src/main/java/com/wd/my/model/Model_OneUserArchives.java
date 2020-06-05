package com.wd.my.model;

import com.wd.my.bean.DeleteUserArchivesBean;
import com.wd.my.bean.FindUserArchivesBean;
import com.wd.my.bean.UpdateUserArchivesBean;
import com.wd.my.contract.ICoolor_OneUserArchives;
import com.wd.my.util.CreatApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Android Studio.
 * User: 郑昊菲
 * Date: 2020/6/3
 * Time: 14:46
 */
public class Model_OneUserArchives implements ICoolor_OneUserArchives.IModel {
    @Override
    public void getFindUserArchives(ICoolor_OneUserArchives.FindUserArchivesCallback findUserArchivesCallback) {
        CreatApis.creatClass().getFindUserArchives()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FindUserArchivesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FindUserArchivesBean findUserArchivesBean) {
                        findUserArchivesCallback.getSuccess(findUserArchivesBean);

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
    public void getUpdateUserArchives(int archivesId, String diseaseMain, String diseaseNow, String diseaseBefore, String treatmentHospitalRecent, String treatmentProcess, String treatmentStartTime, String treatmentEndTime, ICoolor_OneUserArchives.UpdateUserArchivesCallback updateUserArchivesCallback) {
        CreatApis.creatClass().getUpdateUserArchives(archivesId,diseaseMain,diseaseNow,diseaseBefore,treatmentHospitalRecent,treatmentProcess,treatmentStartTime,treatmentEndTime)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpdateUserArchivesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpdateUserArchivesBean updateUserArchivesBean) {
                        updateUserArchivesCallback.getSuccess(updateUserArchivesBean);

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
    public void getDeleteUserArchives(int archivesId, ICoolor_OneUserArchives.DeleteUserArchivesCallback deleteUserArchivesCallback) {
        CreatApis.creatClass().getDeleteUserArchives(archivesId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DeleteUserArchivesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DeleteUserArchivesBean deleteUserArchivesBean) {
                        deleteUserArchivesCallback.getSuccess(deleteUserArchivesBean);

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
