package com.wd.my.contract;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.my.bean.AddUserArchivesBean;
import com.wd.my.bean.DeleteUserArchivesBean;
import com.wd.my.bean.FindUserArchivesBean;
import com.wd.my.bean.UpImageBean;
import com.wd.my.bean.UpdateUserArchivesBean;

import okhttp3.RequestBody;

/**
 * Created by Android Studio.
 * User: 郑昊菲
 * Date: 2020/6/3
 * Time: 14:41
 */
public interface ICoolor_TwoUserArchives {
    interface IVew extends IBaseView{
        void getAddUserArchives(AddUserArchivesBean addUserArchivesBean);
        void getUpImage(UpImageBean upImageBean);
    }
    interface IPresenter{
        void getAddUserArchives(RequestBody body);
        void getUpImage(RequestBody body);
    }
    interface IModel{
        void getAddUserArchives(RequestBody body,AddUserArchivesCallback addUserArchivesCallback);
        void getUpImage(RequestBody body,UpImageCallback upImageCallback);
    }
    interface AddUserArchivesCallback{
        void getSuccess(AddUserArchivesBean addUserArchivesBean);
    }
    interface UpImageCallback{
        void getSuccess(UpImageBean upImageBean);
    }
}
