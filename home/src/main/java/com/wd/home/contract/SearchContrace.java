package com.wd.home.contract;


import com.wd.common.base.util.Base.IBaseView;
import com.wd.home.bean.HomePageSearchBean;
import com.wd.home.bean.PopularSearchesBean;

public interface SearchContrace {
    interface IView extends IBaseView {
        void onPopularSearchSuccess(PopularSearchesBean bean);
        void onPopularSearchFaliure(String str);

        void onHomePageSearchSuccess(HomePageSearchBean bean);
        void onHomePageSearchFaliure(String str);
    }

    interface IModer{
        void onPopularSearch(CallBack callBack);
        interface CallBack{
            void onPopularSearchSuccess(PopularSearchesBean bean);
            void onPopularSearchFaliure(String str);
        }

        void onHomePageSearch(String keyWord, ICallBack callBack);
        interface ICallBack{
            void onHomePageSearchSuccess(HomePageSearchBean bean);
            void onHomePageSearchFaliure(String str);
        }
    }

    interface IPresenter{
        void onPopularSearch();
        void onHomePageSearch(String keyWord);
    }
}
