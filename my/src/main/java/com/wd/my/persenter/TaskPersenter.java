package com.wd.my.persenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;

import com.wd.my.bean.AddArchivesBean;
import com.wd.my.bean.SignInBean;
import com.wd.my.bean.TaskBean;
import com.wd.my.contract.TaskContract;
import com.wd.my.model.TaskModel;


public class TaskPersenter extends BasePresenter implements TaskContract.IPersenter {

    private TaskModel mTaskModel;
    private TaskContract.IView mView;

    public TaskPersenter(IBaseView baseView) {
        super(baseView);
    }

    @Override
    public void initModel() {
        mTaskModel = new TaskModel();
        IBaseView iView = getView();
        if (iView != null && iView instanceof TaskContract.IView) {
            mView = (TaskContract.IView) iView;
        }
    }

    @Override
    public void getTask() {
        mTaskModel.getTask(new TaskContract.IModel.GetTaskCallBack() {
            @Override
            public void getTaskSuccess(TaskBean taskBean) {
                mView.getTaskSuccess(taskBean);
            }

            @Override
            public void getTaskFailed(String msg) {
                mView.getTaskFailed(msg);
            }
        });
    }

    @Override
    public void doTask(int taskId) {
        mTaskModel.doTask(taskId, new TaskContract.IModel.DoTaskCallBack() {
            @Override
            public void doTaskSuccess(AddArchivesBean addArchivesBean) {
                mView.doTaskSuccess(addArchivesBean);
            }

            @Override
            public void doTaskFailed(String msg) {
                mView.doTaskFailed(msg);
            }
        });
    }

    @Override
    public void receiveReward(int taskId) {
        mTaskModel.receiveReward(taskId, new TaskContract.IModel.ReceiveRewardCallBack() {
            @Override
            public void receiveRewardSuccess(AddArchivesBean addArchivesBean) {
                mView.receiveRewardSuccess(addArchivesBean);
            }

            @Override
            public void receiveRewardFailed(String msg) {
                mView.receiveRewardFailed(msg);
            }
        });
    }

    @Override
    public void getSignInDay() {
        mTaskModel.getSignInDay(new TaskContract.IModel.GetSignInDayCallBack() {
            @Override
            public void getSignInDaySuccess(SignInBean signInBean) {
                mView.getSignInDaySuccess(signInBean);
            }

            @Override
            public void getSignInDayFailed(String msg) {
                mView.getSignInDayFailed(msg);
            }
        });
    }
}
