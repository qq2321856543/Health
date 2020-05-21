package com.wd.login.activity.model;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.login.activity.bean.LoginLoginBean;
import com.wd.login.activity.bean.LoginRegisterBean;
import com.wd.login.activity.bean.LoginResetPwdBean;
import com.wd.login.activity.bean.LoginSendEmailCodeBean;
import com.wd.login.activity.bean.LogincheckCodeBean;
import com.wd.login.activity.contract.ILoginContract;
import com.wd.login.activity.util.LoginApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2020/5/21
 * Author: 王冠华
 * Description:
 */
public class LoginModel implements ILoginContract.IModel{
    public static LoginApis createrRetrofit(){
        return RetrofitUtil.getInstance().getRetrofitServie(LoginApis.class);
    }
    @Override
    public void onGetSendEmailCode(String email, ISendEmailCodeCallBack iSendEmailCodeCallBack) {
        createrRetrofit().getSendEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginSendEmailCodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginSendEmailCodeBean loginSendEmailCodeBean) {
                        if(iSendEmailCodeCallBack!=null){
                            iSendEmailCodeCallBack.onSendEmailCode(loginSendEmailCodeBean);
                        }
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
    public void onGetRegister(String email, String code, String pwd1, String pwd2, String invitationCode, IRegisterCallBack iRegisterCallBack) {
        createrRetrofit().getRegister(email, code, pwd1, pwd2, invitationCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginRegisterBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginRegisterBean loginRegisterBean) {
                        if(iRegisterCallBack!=null){
                            iRegisterCallBack.onRegister(loginRegisterBean);
                        }
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
    public void onGetLogin(String email, String pwd, ILoginCallBack iLoginCallBack) {
        createrRetrofit().getLogin(email, pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginLoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginLoginBean loginLoginBean) {
                        if(iLoginCallBack!=null){
                            iLoginCallBack.onLogin(loginLoginBean);
                        }
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
    public void onGetCheckCode(String email, String pwd, ICheckCodeCallBack iCheckCodeCallBack) {
        createrRetrofit().getCheckCode(email,pwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogincheckCodeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LogincheckCodeBean logincheckCodeBean) {
                        if(iCheckCodeCallBack!=null){
                            iCheckCodeCallBack.onCheckCode(logincheckCodeBean);
                        }
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
    public void onGetResetPwd(String email, String pwd1, String pwd2, IResetPwdCallBack iResetPwdCallBack) {
        createrRetrofit().getResetPwd(email, pwd1, pwd2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginResetPwdBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginResetPwdBean loginResetPwdBean) {
                        if(iResetPwdCallBack!=null){
                            iResetPwdCallBack.onResetPwd(loginResetPwdBean);
                        }
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
