package com.wd.my.model;


import com.wd.my.bean.AddArchivesBean;
import com.wd.my.bean.SignInBean;
import com.wd.my.bean.TaskBean;
import com.wd.my.contract.TaskContract;
import com.wd.my.util.CreatApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @ProjectName: WeiDuHealth
 * @Package: com.wd.minemodule.contract
 * @ClassName: GetUserInfoContract
 * @Description:
 * @Author: 李泽晋
 * @CreateDate: 2020.5.21 0021 11:00
 * @UpdateUser:
 * @UpdateDate: 2020.5.21 0021 11:00
 * @UpdateRemark:
 * @Version: 1.0
 */
public class TaskModel implements TaskContract.IModel {
    @Override
    public void getTask(final TaskContract.IModel.GetTaskCallBack callBack) {
        CreatApis.creatClass().getTask()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TaskBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TaskBean taskBean) {
                        callBack.getTaskSuccess(taskBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.getTaskFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void doTask(int taskId, final DoTaskCallBack callBack) {
        CreatApis.creatClass().doTask(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddArchivesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddArchivesBean addArchivesBean) {
                        callBack.doTaskSuccess(addArchivesBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.doTaskFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void receiveReward(int taskId, final ReceiveRewardCallBack callBack) {
        CreatApis.creatClass().receiveReward(taskId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddArchivesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddArchivesBean addArchivesBean) {
                        callBack.receiveRewardSuccess(addArchivesBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.receiveRewardFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getSignInDay(final GetSignInDayCallBack callBack) {
        CreatApis.creatClass().getSignDay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignInBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SignInBean signInBean) {
                        callBack.getSignInDaySuccess(signInBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.getSignInDayFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
