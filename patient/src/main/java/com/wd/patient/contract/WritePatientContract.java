package com.wd.patient.contract;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.patient.bean.IllnessBean;
import com.wd.patient.bean.KeLieBean;
import com.wd.patient.bean.PublishPatientBean;

import okhttp3.RequestBody;
import retrofit2.http.Field;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.contract
 * @ClassName: WritePatientContract
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/6/7 9:46
 */
public interface WritePatientContract {
    interface IView extends IBaseView {
        void onShowDepartmentSuccess(KeLieBean keLieBean);
        void onShowDepartmentFailure(String str);

        void onShowIllnessSuccess(IllnessBean illnessBean);
        void showIllnessFailure(String str);

        void onPublishPatientSuccess(PublishPatientBean publishPatientBean);
        void onPublishPatientFailure(String str);
    }

    interface IPresenter {
        void showDepartment();

        void showIllness(int departmentId);

        //        void publishPatient(String title,int departmentId,String disease,String detail,String treatmentHospital,String treatmentStartTime,String treatmentEndTime, String treatmentProcess, int amount);
        void publishPatient(RequestBody requestBody);
    }

    interface IModel {
        void showDepartment(showDepartmentCallback callback);

        void showIllness(int departmentId,showIllnessCallBack callBack);

        //        void publishPatient(String title,int departmentId,String disease,String detail,String treatmentHospital,String treatmentStartTime,String treatmentEndTime, String treatmentProcess, int amount,publishPatientCallBack callBack);
        void publishPatient(RequestBody requestBody,publishPatientCallBack callBack);
        interface showDepartmentCallback {
            void onShowDepartmentSuccess(KeLieBean keLieBean);
            void onShowDepartmentFailure(String str);
        }

        interface showIllnessCallBack {
            void onShowIllnessSuccess(IllnessBean illnessBean);
            void showIllnessFailure(String str);
        }

        interface publishPatientCallBack {
            void onPublishPatientSuccess(PublishPatientBean publishPatientBean);
            void onPublishPatientFailure(String str);
        }

    }
}
