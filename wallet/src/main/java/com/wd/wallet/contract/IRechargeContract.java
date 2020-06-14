package com.wd.wallet.contract;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.wallet.bean.WalletAliPayBean;
import com.wd.wallet.bean.WalletDrawCashBean;
import com.wd.wallet.bean.WalletWxPayBean;

/**
 * Time: 2020/6/2
 * Author: 王冠华
 * Description:
 */
public interface IRechargeContract {
    interface IView extends IBaseView{
        void onWeChatPay(WalletWxPayBean walletWxPayBean);
        void onAliPay(WalletAliPayBean walletAliPayBean);
        void onDrawCash(WalletDrawCashBean walletDrawCashBean);
    }
    interface IPresenter{
        void getWeChatPay(double money,int payType);
        void getAliPay(double money,int payType);
        void getDrawCash(int money);
    }
    interface IModel{
        void onGetWeChatPay(double money,int payType,IWeChatCallBack iWeChatCallBack);
        void onGetAliPay(double money,int payType,IAliCallBack iAliCallBack);
        void onGetDrawCash(int money,IDrawCashCallBack iDrawCashCallBack);
        interface IWeChatCallBack{
            void onWeChatPay(WalletWxPayBean walletWxPayBean);
        }
        interface IAliCallBack{
            void onAliPay(WalletAliPayBean walletAliPayBean);
        }
        interface IDrawCashCallBack{
            void onDrawCash(WalletDrawCashBean walletDrawCashBean);
        }
    }
}
