package com.wd.home.presenter;


import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.home.bean.HomePageSearchBean;
import com.wd.home.bean.PopularSearchesBean;
import com.wd.home.contract.IHomeContract;
import com.wd.home.contract.SearchContrace;
import com.wd.home.model.SearchModerl;

public class SearchPresenter extends BasePresenter implements SearchContrace.IPresenter {

    private SearchModerl searchModerl;

    public SearchPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    public void initModel() {
        searchModerl = new SearchModerl();
    }

    @Override
    public void onPopularSearch() {
        searchModerl.onPopularSearch(new SearchContrace.IModer.CallBack() {
            @Override
            public void onPopularSearchSuccess(PopularSearchesBean bean) {
                IBaseView view = getView();
                if(view instanceof SearchContrace.IView){
                    ((SearchContrace.IView) view).onPopularSearchSuccess(bean);
                }
            }

            @Override
            public void onPopularSearchFaliure(String str) {
                IBaseView view = getView();
                if(view instanceof SearchContrace.IView){
                    ((SearchContrace.IView) view).onPopularSearchFaliure(str);
                }
            }
        });
    }

    @Override
    public void onHomePageSearch(String keyWord) {
        searchModerl.onHomePageSearch(keyWord, new SearchContrace.IModer.ICallBack() {
            @Override
            public void onHomePageSearchSuccess(HomePageSearchBean bean) {
                IBaseView view = getView();
                if(view instanceof SearchContrace.IView){
                    ((SearchContrace.IView) view).onHomePageSearchSuccess(bean);
                }
            }

            @Override
            public void onHomePageSearchFaliure(String str) {
                IBaseView view = getView();
                if(view instanceof SearchContrace.IView){
                    ((SearchContrace.IView) view).onHomePageSearchFaliure(str);
                }
            }
        });
    }
}
