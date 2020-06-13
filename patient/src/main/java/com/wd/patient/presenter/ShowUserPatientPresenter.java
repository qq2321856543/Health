package com.wd.patient.presenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.patient.bean.SearchBean;
import com.wd.patient.bean.UserPatientBean;
import com.wd.patient.contract.PatientSearchContract;
import com.wd.patient.contract.ShowUserPatientContract;
import com.wd.patient.model.PatientSearchModel;
import com.wd.patient.model.ShowUserPatientModel;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.presenter
 * @ClassName: PatientSearchPresenter
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/5/24 19:24
 */
public class ShowUserPatientPresenter extends BasePresenter implements ShowUserPatientContract.IPresenter {


    private ShowUserPatientModel mModel;

    public ShowUserPatientPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        mModel = new ShowUserPatientModel();
    }


    @Override
    public void showUserPatient(int patientUserId, int page, int count) {
        mModel.showUserPatient(patientUserId, page, count, new ShowUserPatientContract.IModel.showUserPatientCallBack() {
            @Override
            public void onSuccess(UserPatientBean userPatientBean) {
                IBaseView view = getView();
                if (view instanceof ShowUserPatientContract.IView) {
                    ((ShowUserPatientContract.IView)view).onSuccess(userPatientBean);
                }
            }

            @Override
            public void onFailure(String str) {

            }
        });
    }
}
