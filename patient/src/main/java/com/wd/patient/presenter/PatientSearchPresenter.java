package com.wd.patient.presenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.patient.bean.SearchBean;
import com.wd.patient.contract.PatientSearchContract;
import com.wd.patient.model.PatientSearchModel;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.presenter
 * @ClassName: PatientSearchPresenter
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/5/24 19:24
 */
public class PatientSearchPresenter extends BasePresenter implements PatientSearchContract.IPresenter {

    private PatientSearchModel mModel;

    public PatientSearchPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        mModel = new PatientSearchModel();
    }

    @Override
    public void getSearch(String keyWord) {
        mModel.getSearch(keyWord, new PatientSearchContract.IModel.searchCallBack() {
            @Override
            public void onSuccess(SearchBean searchBean) {
                IBaseView view = getView();
                if (view instanceof PatientSearchContract.IView) {
                    ((PatientSearchContract.IView)view).onSuccess(searchBean);
                }
            }

            @Override
            public void onFailure(String str) {

            }
        });
    }
}
