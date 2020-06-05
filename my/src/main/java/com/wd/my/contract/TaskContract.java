package com.wd.my.contract;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.my.bean.AddArchivesBean;
import com.wd.my.bean.SignInBean;
import com.wd.my.bean.TaskBean;


public interface TaskContract {
    interface IView extends IBaseView {
        void getTaskSuccess(TaskBean taskBean);
        void getTaskFailed(String msg);
        //做任务
        void doTaskSuccess(AddArchivesBean addArchivesBean);
        void doTaskFailed(String msg);
        //领奖励
        void receiveRewardSuccess(AddArchivesBean addArchivesBean);
        void receiveRewardFailed(String msg);
        //获取连续签到天数
        void getSignInDaySuccess(SignInBean signInBean);
        void getSignInDayFailed(String msg);
    }
    interface IPersenter{
        void getTask();
        //
        void doTask(int taskId);
        //
        void receiveReward(int taskId);
        //
        void getSignInDay();
    }
    interface IModel{
        void getTask(GetTaskCallBack callBack);
        interface GetTaskCallBack{
            void getTaskSuccess(TaskBean taskBean);
            void getTaskFailed(String msg);
        }
        //
        void doTask(int taskId, DoTaskCallBack callBack);
        interface DoTaskCallBack{
            void doTaskSuccess(AddArchivesBean addArchivesBean);
            void doTaskFailed(String msg);
        }
        //
        void receiveReward(int taskId, ReceiveRewardCallBack callBack);
        interface ReceiveRewardCallBack{
            void receiveRewardSuccess(AddArchivesBean addArchivesBean);
            void receiveRewardFailed(String msg);
        }
        //
        void getSignInDay(GetSignInDayCallBack callBack);
        interface GetSignInDayCallBack{
            void getSignInDaySuccess(SignInBean signInBean);
            void getSignInDayFailed(String msg);
        }
    }
}
