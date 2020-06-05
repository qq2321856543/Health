package com.wd.my.persenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.my.bean.DeleteUserArchivesBean;
import com.wd.my.bean.FindUserArchivesBean;
import com.wd.my.bean.UpdateUserArchivesBean;
import com.wd.my.contract.ICoolor_OneUserArchives;
import com.wd.my.model.Model_OneUserArchives;

/**
 * Created by Android Studio.
 * User: 郑昊菲
 * Date: 2020/6/3
 * Time: 14:49
 */
public class Presenter_OneUserArchives extends BasePresenter implements ICoolor_OneUserArchives.IPresenter {

    private Model_OneUserArchives model;

    public Presenter_OneUserArchives(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new Model_OneUserArchives();
    }

    @Override
    public void getFindUserArchives() {
        model.getFindUserArchives(new ICoolor_OneUserArchives.FindUserArchivesCallback() {
            @Override
            public void getSuccess(FindUserArchivesBean findUserArchivesBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_OneUserArchives.IVew)view).getFindUserArchives(findUserArchivesBean);
                }
            }
        });
    }

    @Override
    public void getUpdateUserArchives(int archivesId, String diseaseMain, String diseaseNow, String diseaseBefore, String treatmentHospitalRecent, String treatmentProcess, String treatmentStartTime, String treatmentEndTime) {
        model.getUpdateUserArchives(archivesId, diseaseMain, diseaseNow, diseaseBefore, treatmentHospitalRecent, treatmentProcess, treatmentStartTime, treatmentEndTime, new ICoolor_OneUserArchives.UpdateUserArchivesCallback() {
            @Override
            public void getSuccess(UpdateUserArchivesBean updateUserArchivesBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_OneUserArchives.IVew)view).getUpdateUserArchives(updateUserArchivesBean);
                }
            }
        });
    }

    @Override
    public void getDeleteUserArchives(int archivesId) {
        model.getDeleteUserArchives(archivesId, new ICoolor_OneUserArchives.DeleteUserArchivesCallback() {
            @Override
            public void getSuccess(DeleteUserArchivesBean deleteUserArchivesBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_OneUserArchives.IVew)view).getDeleteUserArchives(deleteUserArchivesBean);
                }
            }
        });
    }
}
