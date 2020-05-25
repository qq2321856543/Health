package com.wd.my.contract;


import com.wd.common.base.util.Base.IBaseView;
import com.wd.my.bean.SignInBean;
import com.wd.my.bean.UserInfoBean;

public interface GetUserInfoContract {
    interface IView extends IBaseView {
        void getUserInfoSuccess(UserInfoBean userInfoBean);
        void getUserInfoFailed(String msg);
        //查询是否签到
        void getSignInSuccess(SignInBean signInBean);
        void getSignInFailed(String msg);
        //签到
        void signInSuccess(SignInBean signInBean);
        void signInFailed(String msg);
    }
    interface IPersenter{
        void getUserInfo();
        //
        void getSignInDay();
        //
        void signIn();
    }
    interface IModel{
        void getUserInfo(GetUserInfoCallBack callBack);
        interface GetUserInfoCallBack{
            void getUserInfoSuccess(UserInfoBean userInfoBean);
            void getUserInfoFailed(String msg);
        }
        //
        void getSignInDay(GetSignInDayCallBack callBack);
        interface GetSignInDayCallBack{
            void getSignInSuccess(SignInBean signInBean);
            void getSignInFailed(String msg);
        }
        //
        void signIn(SignInCallBack callBack);
        interface SignInCallBack{
            void signInSuccess(SignInBean signInBean);
            void signInFailed(String msg);
        }
    }
}
