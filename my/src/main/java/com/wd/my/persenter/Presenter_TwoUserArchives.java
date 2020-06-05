package com.wd.my.persenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.my.bean.AddUserArchivesBean;
import com.wd.my.bean.UpImageBean;
import com.wd.my.contract.ICoolor_TwoUserArchives;
import com.wd.my.model.Model_TwoUserArchives;

import okhttp3.RequestBody;

/**
 * Created by Android Studio.
 * User: 郑昊菲
 * Date: 2020/6/3
 * Time: 17:58
 */
public class Presenter_TwoUserArchives extends BasePresenter implements ICoolor_TwoUserArchives.IPresenter {

    private Model_TwoUserArchives model;

    public Presenter_TwoUserArchives(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new Model_TwoUserArchives();
    }

    @Override
    public void getAddUserArchives(RequestBody body) {
        model.getAddUserArchives(body, new ICoolor_TwoUserArchives.AddUserArchivesCallback() {
            @Override
            public void getSuccess(AddUserArchivesBean addUserArchivesBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_TwoUserArchives.IVew)view).getAddUserArchives(addUserArchivesBean);
                }
            }
        });
    }

    @Override
    public void getUpImage(RequestBody body) {
        model.getUpImage(body, new ICoolor_TwoUserArchives.UpImageCallback() {
            @Override
            public void getSuccess(UpImageBean upImageBean) {
                IBaseView view = getView();
                if (view != null) {
                    ((ICoolor_TwoUserArchives.IVew)view).getUpImage(upImageBean);
                }
            }
        });
    }
}
