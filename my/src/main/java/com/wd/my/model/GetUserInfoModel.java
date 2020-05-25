package com.wd.my.model;


import com.wd.my.bean.SignInBean;
import com.wd.my.bean.UserInfoBean;
import com.wd.my.contract.GetUserInfoContract;
import com.wd.my.util.CreatApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class GetUserInfoModel implements GetUserInfoContract.IModel {
    @Override
    public void getUserInfo(final GetUserInfoCallBack callBack) {
        CreatApis.creatClass().getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserInfoBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        callBack.getUserInfoSuccess(userInfoBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.getUserInfoFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getSignInDay(final GetSignInDayCallBack callBack) {
        CreatApis.creatClass().getSignInDay()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignInBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SignInBean signInBean) {
                        callBack.getSignInSuccess(signInBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.getSignInFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void signIn(final SignInCallBack callBack) {
        CreatApis.creatClass().signIn()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SignInBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SignInBean signInBean) {
                        callBack.signInSuccess(signInBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.signInFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
