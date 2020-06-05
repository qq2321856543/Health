package com.wd.my.contract;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.my.bean.DeleteUserArchivesBean;
import com.wd.my.bean.FindUserArchivesBean;
import com.wd.my.bean.UpdateUserArchivesBean;

/**
 * Created by Android Studio.
 * User: 郑昊菲
 * Date: 2020/6/3
 * Time: 14:41
 */
public interface ICoolor_OneUserArchives {
    interface IVew extends IBaseView{
        void getFindUserArchives(FindUserArchivesBean findUserArchivesBean);
        void getUpdateUserArchives(UpdateUserArchivesBean updateUserArchivesBean);
        void getDeleteUserArchives(DeleteUserArchivesBean deleteUserArchivesBean);
    }
    interface IPresenter{
        void getFindUserArchives();
        void getUpdateUserArchives(int archivesId,String diseaseMain,String diseaseNow,String diseaseBefore,String treatmentHospitalRecent,String treatmentProcess,String treatmentStartTime,String treatmentEndTime);
        void getDeleteUserArchives(int archivesId);
    }
    interface IModel{
        void getFindUserArchives(FindUserArchivesCallback findUserArchivesCallback);
        void getUpdateUserArchives(int archivesId,String diseaseMain,String diseaseNow,String diseaseBefore,String treatmentHospitalRecent,String treatmentProcess,String treatmentStartTime,String treatmentEndTime,UpdateUserArchivesCallback updateUserArchivesCallback);
        void getDeleteUserArchives(int archivesId,DeleteUserArchivesCallback deleteUserArchivesCallback);
    }
    interface FindUserArchivesCallback{
        void getSuccess(FindUserArchivesBean findUserArchivesBean);
    }
    interface UpdateUserArchivesCallback{
        void getSuccess(UpdateUserArchivesBean updateUserArchivesBean);
    }
    interface DeleteUserArchivesCallback{
        void getSuccess(DeleteUserArchivesBean deleteUserArchivesBean);
    }
}
