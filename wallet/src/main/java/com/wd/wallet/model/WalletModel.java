package com.wd.wallet.model;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.wallet.bean.WalletRecordBean;
import com.wd.wallet.bean.WalletUserBean;
import com.wd.wallet.contract.WalletContract;
import com.wd.wallet.util.WalletApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2020/6/1
 * Author: 王冠华
 * Description:
 */
public class WalletModel implements WalletContract.IModel {
    public static WalletApis createrRetrofit(){
        return RetrofitUtil.getInstance().getRetrofitServie(WalletApis.class);
    }
    @Override
    public void onGetUserMoney(IUserMoneyCallBack iUserMoneyCallBack) {
        createrRetrofit().getUserMoney()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WalletUserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WalletUserBean walletUserBean) {
                        if(iUserMoneyCallBack!=null){
                            iUserMoneyCallBack.onUserMoney(walletUserBean);
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
    public void onGetRecord(int page, int count, IRecordCallBack iRecordCallBack) {
        createrRetrofit().getRecord(page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WalletRecordBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WalletRecordBean walletRecordBean) {
                        if(iRecordCallBack!=null){
                            iRecordCallBack.onRecord(walletRecordBean);
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
