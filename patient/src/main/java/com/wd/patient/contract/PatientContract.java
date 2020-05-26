package com.wd.patient.contract;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.patient.bean.BingXiangBean;
import com.wd.patient.bean.BingYouQuanBean;
import com.wd.patient.bean.KeLieBean;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient
 * @ClassName: PatientContract
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/5/21 22:29
 */
public interface PatientContract {
    interface IView extends IBaseView {
        void onPatientSuccess(BingYouQuanBean bingYouQuanBean);
        void onPatientFailure(String str);

        void onSuccess(BingXiangBean bingXiangBean);
        void onFailure(String str);

        void onKeLieSuccess(KeLieBean keLieBean);
        void onKeLieFailure(String str);
    }

    interface IPresenter {
        void getPatient(int departmentId, int page, int count);

        void getBingXiang(int sickCircleId);

        void getKeLie();
    }

    interface IModel {
        void getPatient(int departmentId, int page, int count,patientCallBack callBack);
        void getBingXiang(int sickCircleId,bingXiangCallBack callBack);
        void getKeLie(keLieCallBack callBack);

        interface patientCallBack {
            void onPatientSuccess(BingYouQuanBean bingYouQuanBean);
            void onPatientFailure(String str);
        }


        interface bingXiangCallBack {
            void onSuccess(BingXiangBean bingXiangBean);
            void onFailure(String str);
        }

        interface keLieCallBack {
            void onKeLieSuccess(KeLieBean keLieBean);
            void onKeLieFailure(String str);
        }

    }

}
