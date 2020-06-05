package com.wd.wallet.presenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.wallet.bean.WalletDoctorBean;
import com.wd.wallet.bean.WalletRecordBean;
import com.wd.wallet.bean.WalletUserBean;
import com.wd.wallet.contract.WalletContract;
import com.wd.wallet.model.WalletModel;

/**
 * Time: 2020/6/1
 * Author: 王冠华
 * Description:
 */
public class WalletPresenter extends BasePresenter implements WalletContract.IPresenter {

    private WalletModel model;

    public WalletPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new WalletModel();
    }

    @Override
    public void getUserMoney() {
        model.onGetUserMoney(new WalletContract.IModel.IUserMoneyCallBack() {
            @Override
            public void onUserMoney(WalletUserBean walletUserBean) {
                IBaseView view = getView();
                if(view instanceof WalletContract.IView){
                    WalletContract.IView iView= (WalletContract.IView) view;
                    iView.onUserMoney(walletUserBean);
                }
            }
        });
    }

    @Override
    public void getRecord(int page, int count) {
        model.onGetRecord(page, count, new WalletContract.IModel.IRecordCallBack() {
            @Override
            public void onRecord(WalletRecordBean walletRecordBean) {
                IBaseView view = getView();
                if(view instanceof WalletContract.IView){
                    WalletContract.IView iView= (WalletContract.IView) view;
                    iView.onRecord(walletRecordBean);
                }
            }
        });
    }

    @Override
    public void getDoctor(int deptId, int condition, int page, int count) {
        model.onGetDoctor(deptId, condition, page, count, new WalletContract.IModel.IDoctorCallBack() {
            @Override
            public void onDoctor(WalletDoctorBean walletDoctorBean) {
                IBaseView view = getView();
                if(view instanceof WalletContract.IView){
                    WalletContract.IView iView= (WalletContract.IView) view;
                    iView.onDoctor(walletDoctorBean);
                }
            }
        });
    }
}
