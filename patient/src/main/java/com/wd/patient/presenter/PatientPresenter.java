package com.wd.patient.presenter;

import android.util.Log;
import android.widget.Toast;

import com.wd.common.base.util.Base.BaseApplication;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.patient.bean.BingXiangBean;
import com.wd.patient.bean.BingYouQuanBean;
import com.wd.patient.bean.KeLieBean;
import com.wd.patient.contract.PatientContract;
import com.wd.patient.model.PatientModel;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.presenter
 * @ClassName: PatientPresenter
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/5/21 22:33
 */
public class PatientPresenter extends BasePresenter implements PatientContract.IPresenter {

    private PatientModel mModel;

    public PatientPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        mModel = new PatientModel();
    }

    @Override
    public void getPatient(int departmentId, int page, int count) {
        mModel.getPatient(departmentId, page, count, new PatientContract.IModel.patientCallBack() {
            @Override
            public void onPatientSuccess(BingYouQuanBean bingYouQuanBean) {
                IBaseView view = getView();
                if (view instanceof PatientContract.IView) {
                    ((PatientContract.IView)view).onPatientSuccess(bingYouQuanBean);
                }
            }

            @Override
            public void onPatientFailure(String str) {

            }
        });
    }

    @Override
    public void getBingXiang(int sickCircleId) {
        mModel.getBingXiang(sickCircleId, new PatientContract.IModel.bingXiangCallBack() {
            @Override
            public void onSuccess(BingXiangBean bingXiangBean) {
                IBaseView view = getView();
                if (view instanceof PatientContract.IView) {
                    ((PatientContract.IView)view).onSuccess(bingXiangBean);
                }
            }

            @Override
            public void onFailure(String str) {

            }
        });
    }

    @Override
    public void getKeLie() {
        mModel.getKeLie(new PatientContract.IModel.keLieCallBack() {
            @Override
            public void onKeLieSuccess(KeLieBean keLieBean) {
                IBaseView view = getView();
                if (view instanceof PatientContract.IView) {
                    ((PatientContract.IView)view).onKeLieSuccess(keLieBean);
                }
            }

            @Override
            public void onKeLieFailure(String str) {

            }
        });
    }
}
