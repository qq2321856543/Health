package com.wd.patient.contract;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.patient.bean.SearchBean;
import com.wd.patient.bean.UserPatientBean;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.contract
 * @ClassName: PatientSearchContract
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/5/24 19:20
 */
public interface ShowUserPatientContract {
    interface IView extends IBaseView {
        void onSuccess(UserPatientBean userPatientBean);
        void onFailure(String str);
    }

    interface IPresenter {
        void showUserPatient(int patientUserId,int page,int count);
    }

    interface IModel {
        void showUserPatient(int patientUserId,int page,int count,showUserPatientCallBack callBack);
        interface showUserPatientCallBack {
            void onSuccess(UserPatientBean userPatientBean);
            void onFailure(String str);
        }
    }
}
