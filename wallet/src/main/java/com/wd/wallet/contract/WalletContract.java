package com.wd.wallet.contract;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.wallet.bean.WalletDoctorBean;
import com.wd.wallet.bean.WalletRecordBean;
import com.wd.wallet.bean.WalletUserBean;

/**
 * Time: 2020/6/1
 * Author: 王冠华
 * Description:
 */
public interface WalletContract {
    interface IView extends IBaseView{
        void onUserMoney(WalletUserBean walletUserBean);
        void onRecord(WalletRecordBean walletRecordBean);
        void onDoctor(WalletDoctorBean walletDoctorBean);
    }
    interface IPresenter{
        void getUserMoney();
        void getRecord(int page,int count);
        void getDoctor(int deptId,int condition,int page,int count);
    }
    interface IModel{
        void onGetUserMoney(IUserMoneyCallBack iUserMoneyCallBack);
        void onGetRecord(int page,int count,IRecordCallBack iRecordCallBack);
        void onGetDoctor(int deptId,int condition,int page,int count,IDoctorCallBack iDoctorCallBack);
        interface IUserMoneyCallBack{
            void onUserMoney(WalletUserBean walletUserBean);
        }
        interface IRecordCallBack{
            void onRecord(WalletRecordBean walletRecordBean);
        }
        interface IDoctorCallBack{
            void onDoctor(WalletDoctorBean walletDoctorBean);
        }
    }
}
