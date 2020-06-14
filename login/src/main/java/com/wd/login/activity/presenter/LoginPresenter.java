package com.wd.login.activity.presenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.login.activity.bean.LoginLoginBean;
import com.wd.login.activity.bean.LoginRegisterBean;
import com.wd.login.activity.bean.LoginResetPwdBean;
import com.wd.login.activity.bean.LoginSendEmailCodeBean;
import com.wd.login.activity.bean.LoginWxBean;
import com.wd.login.activity.bean.LogincheckCodeBean;
import com.wd.login.activity.contract.ILoginContract;
import com.wd.login.activity.model.LoginModel;

/**
 * Time: 2020/5/21
 * Author: 王冠华
 * Description:
 */
public class LoginPresenter extends BasePresenter implements ILoginContract.IPresenter {

    private LoginModel model;

    public LoginPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new LoginModel();
    }

    @Override
    public void getSendEmailCode(String email) {
        model.onGetSendEmailCode(email, new ILoginContract.IModel.ISendEmailCodeCallBack() {
            @Override
            public void onSendEmailCode(LoginSendEmailCodeBean loginSendEmailCodeBean) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.IView){
                    ILoginContract.IView iView= (ILoginContract.IView) view;
                    iView.onSendEmailCode(loginSendEmailCodeBean);
                }
            }
        });
    }

    @Override
    public void getRegister(String email, String code, String pwd1, String pwd2, String invitationCode) {
        model.onGetRegister(email, code, pwd1, pwd2, invitationCode, new ILoginContract.IModel.IRegisterCallBack() {
            @Override
            public void onRegister(LoginRegisterBean loginRegisterBean) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.IView){
                    ILoginContract.IView iView= (ILoginContract.IView) view;
                    iView.onRegister(loginRegisterBean);
                }
            }
        });
    }

    @Override
    public void getLogin(String email, String pwd) {
        model.onGetLogin(email, pwd, new ILoginContract.IModel.ILoginCallBack() {
            @Override
            public void onLogin(LoginLoginBean loginLoginBean) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.IView){
                    ILoginContract.IView iView= (ILoginContract.IView) view;
                    iView.onLogin(loginLoginBean);
                }
            }
        });
    }

    @Override
    public void getCheckCode(String email, String pwd) {
        model.onGetCheckCode(email, pwd, new ILoginContract.IModel.ICheckCodeCallBack() {
            @Override
            public void onCheckCode(LogincheckCodeBean logincheckCodeBean) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.IView){
                    ILoginContract.IView iView= (ILoginContract.IView) view;
                    iView.onCheckCode(logincheckCodeBean);
                }
            }
        });
    }

    @Override
    public void getResetPwd(String email, String pwd1, String pwd2) {
        model.onGetResetPwd(email, pwd1, pwd2, new ILoginContract.IModel.IResetPwdCallBack() {
            @Override
            public void onResetPwd(LoginResetPwdBean loginResetPwdBean) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.IView){
                    ILoginContract.IView iView= (ILoginContract.IView) view;
                    iView.onResetPwd(loginResetPwdBean);
                }
            }
        });
    }

    @Override
    public void getWxLogin(String code) {
        model.onGetWxLogin(code, new ILoginContract.IModel.IWxLoinCallBack() {
            @Override
            public void onWxLogin(LoginWxBean loginWxBean) {
                IBaseView view = getView();
                if(view instanceof ILoginContract.IView){
                    ILoginContract.IView iView= (ILoginContract.IView) view;
                    iView.onWxLogin(loginWxBean);
                }
            }
        });
    }
}
