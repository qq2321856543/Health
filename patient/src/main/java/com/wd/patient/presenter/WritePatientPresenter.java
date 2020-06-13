package com.wd.patient.presenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.patient.bean.IllnessBean;
import com.wd.patient.bean.KeLieBean;
import com.wd.patient.bean.PublishPatientBean;
import com.wd.patient.contract.WritePatientContract;
import com.wd.patient.model.WritePatientModel;

import okhttp3.RequestBody;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.presenter
 * @ClassName: WritePatientPresenter
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/6/7 9:56
 */
public class WritePatientPresenter extends BasePresenter implements WritePatientContract.IPresenter {

    private WritePatientModel mModel;

    public WritePatientPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        mModel = new WritePatientModel();
    }

    @Override
    public void showDepartment() {
        mModel.showDepartment(new WritePatientContract.IModel.showDepartmentCallback() {
            @Override
            public void onShowDepartmentSuccess(KeLieBean keLieBean) {
                IBaseView view = getView();
                if (view instanceof WritePatientContract.IView) {
                    ((WritePatientContract.IView)view).onShowDepartmentSuccess(keLieBean);
                }
            }

            @Override
            public void onShowDepartmentFailure(String str) {

            }
        });
    }

    @Override
    public void showIllness(int departmentId) {
        mModel.showIllness(departmentId, new WritePatientContract.IModel.showIllnessCallBack() {
            @Override
            public void onShowIllnessSuccess(IllnessBean illnessBean) {
                IBaseView view = getView();
                if (view instanceof WritePatientContract.IView) {
                    ((WritePatientContract.IView)view).onShowIllnessSuccess(illnessBean);
                }
            }

            @Override
            public void showIllnessFailure(String str) {

            }
        });
    }

    @Override
    public void publishPatient(RequestBody requestBody) {
        mModel.publishPatient(requestBody, new WritePatientContract.IModel.publishPatientCallBack() {
            @Override
            public void onPublishPatientSuccess(PublishPatientBean publishPatientBean) {
                IBaseView view = getView();
                if (view instanceof WritePatientContract.IView) {
                    ((WritePatientContract.IView)view).onPublishPatientSuccess(publishPatientBean);
                }
            }

            @Override
            public void onPublishPatientFailure(String str) {

            }
        });
    }
}
