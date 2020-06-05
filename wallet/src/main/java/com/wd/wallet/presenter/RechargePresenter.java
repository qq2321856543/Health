package com.wd.wallet.presenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.wallet.bean.WalletAliPayBean;
import com.wd.wallet.bean.WalletDrawCashBean;
import com.wd.wallet.bean.WalletWxPayBean;
import com.wd.wallet.contract.IRechargeContract;
import com.wd.wallet.model.RechargeModel;

/**
 * Time: 2020/6/2
 * Author: 王冠华
 * Description:
 */
public class RechargePresenter extends BasePresenter implements IRechargeContract.IPresenter {

    private RechargeModel model;

    public RechargePresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new RechargeModel();
    }

    @Override
    public void getWeChatPay(double money, int payType) {
        model.onGetWeChatPay(money, payType, new IRechargeContract.IModel.IWeChatCallBack() {
            @Override
            public void onWeChatPay(WalletWxPayBean walletWxPayBean) {
                IBaseView view = getView();
                if(view instanceof IRechargeContract.IView){
                    IRechargeContract.IView iView= (IRechargeContract.IView) view;
                    iView.onWeChatPay(walletWxPayBean);
                }
            }
        });
    }

    @Override
    public void getAliPay(double money, int payType) {
        model.onGetAliPay(money, payType, new IRechargeContract.IModel.IAliCallBack() {
            @Override
            public void onAliPay(WalletAliPayBean walletAliPayBean) {
                IBaseView view = getView();
                if(view instanceof IRechargeContract.IView){
                    IRechargeContract.IView iView= (IRechargeContract.IView) view;
                    iView.onAliPay(walletAliPayBean);
                }
            }
        });
    }

    @Override
    public void getDrawCash(int money) {
        model.onGetDrawCash(money, new IRechargeContract.IModel.IDrawCashCallBack() {
            @Override
            public void onDrawCash(WalletDrawCashBean walletDrawCashBean) {
                IBaseView view = getView();
                if(view instanceof IRechargeContract.IView){
                    IRechargeContract.IView iView= (IRechargeContract.IView) view;
                    iView.onDrawCash(walletDrawCashBean);
                }
            }
        });
    }
}
