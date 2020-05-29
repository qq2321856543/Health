package com.wd.home.model;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.home.bean.HomePageSearchBean;
import com.wd.home.bean.PopularSearchesBean;
import com.wd.home.contract.SearchContrace;
import com.wd.home.utils.HomeApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchModerl implements SearchContrace.IModer {
    public static HomeApis createrRetrofit(){
        return RetrofitUtil.getInstance().getRetrofitServie(HomeApis.class);
    }
    @Override
    public void onPopularSearch(final CallBack callBack) {
        createrRetrofit().popularSearch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PopularSearchesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PopularSearchesBean popularSearchesBean) {
                        if(callBack!=null){
                            callBack.onPopularSearchSuccess(popularSearchesBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBack!=null){
                            callBack.onPopularSearchFaliure(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onHomePageSearch(String keyWord, final ICallBack callBack) {
        createrRetrofit().homePageSearch(keyWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomePageSearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomePageSearchBean popularSearchesBean) {
                        if(callBack!=null){
                            callBack.onHomePageSearchSuccess(popularSearchesBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBack!=null){
                            callBack.onHomePageSearchFaliure(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
