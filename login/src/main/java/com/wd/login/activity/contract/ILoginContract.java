package com.wd.login.activity.contract;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.login.activity.bean.LoginLoginBean;
import com.wd.login.activity.bean.LoginRegisterBean;
import com.wd.login.activity.bean.LoginResetPwdBean;
import com.wd.login.activity.bean.LoginSendEmailCodeBean;
import com.wd.login.activity.bean.LoginWxBean;
import com.wd.login.activity.bean.LogincheckCodeBean;

/**
 * Time: 2020/5/21
 * Author: 王冠华
 * Description:
 */
public  interface ILoginContract {
    interface IView extends IBaseView{
        void onSendEmailCode(LoginSendEmailCodeBean loginSendEmailCodeBean);
        void onRegister(LoginRegisterBean loginRegisterBean);
        void onLogin(LoginLoginBean loginLoginBean);
        void onCheckCode(LogincheckCodeBean logincheckCodeBean);
        void onResetPwd(LoginResetPwdBean loginResetPwdBean);
        void onWxLogin(LoginWxBean loginWxBean);
    }
    interface IPresenter{
        void getSendEmailCode(String email);
        void getRegister(String email,String code,String pwd1,String pwd2,String invitationCode);
        void getLogin(String email,String pwd);
        void getCheckCode(String email,String pwd);
        void getResetPwd(String email,String pwd1,String pwd2);
        void getWxLogin(String code);
    }
    interface IModel{
        void onGetSendEmailCode(String email,ISendEmailCodeCallBack iSendEmailCodeCallBack);
        void onGetRegister(String email,String code,String pwd1,String pwd2,String invitationCode,IRegisterCallBack iRegisterCallBack);
        void onGetLogin(String email,String pwd,ILoginCallBack iLoginCallBack);
        void onGetCheckCode(String email,String pwd,ICheckCodeCallBack iCheckCodeCallBack);
        void onGetResetPwd(String email,String pwd1,String pwd2,IResetPwdCallBack iResetPwdCallBack);
        void onGetWxLogin(String code,IWxLoinCallBack iWxLoinCallBack);
        interface ISendEmailCodeCallBack{
            void onSendEmailCode(LoginSendEmailCodeBean loginSendEmailCodeBean);
        }
        interface IRegisterCallBack{
            void onRegister(LoginRegisterBean loginRegisterBean);
        }
        interface ILoginCallBack{
            void onLogin(LoginLoginBean loginLoginBean);
        }
        interface ICheckCodeCallBack{
            void onCheckCode(LogincheckCodeBean logincheckCodeBean);
        }
        interface IResetPwdCallBack{
            void onResetPwd(LoginResetPwdBean loginResetPwdBean);
        }
        interface IWxLoinCallBack{
            void onWxLogin(LoginWxBean loginWxBean);
        }
    }
}
