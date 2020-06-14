package com.wd.wallet.model;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.wallet.bean.WalletAliPayBean;
import com.wd.wallet.bean.WalletDrawCashBean;
import com.wd.wallet.bean.WalletWxPayBean;
import com.wd.wallet.contract.IRechargeContract;
import com.wd.wallet.util.WalletApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2020/6/2
 * Author: 王冠华
 * Description:
 */
public class RechargeModel implements IRechargeContract.IModel {
    public static WalletApis createrRetrofit(){
        return RetrofitUtil.getInstance().getRetrofitServie(WalletApis.class);
    }


    @Override
    public void onGetWeChatPay(double money, int payType, IWeChatCallBack iWeChatCallBack) {
        createrRetrofit().getWxPay(money, payType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WalletWxPayBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WalletWxPayBean walletWxPayBean) {
                        if(iWeChatCallBack!=null){
                            iWeChatCallBack.onWeChatPay(walletWxPayBean);
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
    public void onGetAliPay(double money, int payType, IAliCallBack iAliCallBack) {
        createrRetrofit().getAliPay(money, payType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WalletAliPayBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WalletAliPayBean walletAliPayBean) {
                        if(iAliCallBack!=null){
                            iAliCallBack.onAliPay(walletAliPayBean);
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
    public void onGetDrawCash(int money, IDrawCashCallBack iDrawCashCallBack) {
        createrRetrofit().getDrawCash(money)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WalletDrawCashBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WalletDrawCashBean walletDrawCashBean) {
                        if(iDrawCashCallBack!=null){
                            iDrawCashCallBack.onDrawCash(walletDrawCashBean);
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
