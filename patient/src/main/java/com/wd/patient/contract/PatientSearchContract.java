package com.wd.patient.contract;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.patient.bean.SearchBean;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.contract
 * @ClassName: PatientSearchContract
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/5/24 19:20
 */
public interface PatientSearchContract {
    interface IView extends IBaseView {
        void onSuccess(SearchBean searchBean);
        void onFailure(String str);
    }

    interface IPresenter {
        void getSearch(String keyWord);
    }

    interface IModel {
        void getSearch(String keyWord,searchCallBack callBack);
        interface searchCallBack {
            void onSuccess(SearchBean searchBean);
            void onFailure(String str);
        }
    }
}
