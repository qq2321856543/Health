package com.wd.home.model;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.home.bean.HomeBannerBean;
import com.wd.home.bean.HomeFindListBean;
import com.wd.home.bean.HomePlateListBean;
import com.wd.home.bean.HomeSearchBean;
import com.wd.home.contract.IHomeContract;
import com.wd.home.utils.HomeApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2020/5/22
 * Author: 王冠华
 * Description:
 */
public class HomeModel implements IHomeContract.IModel {
    public static HomeApis createrRetrofit(){
        return RetrofitUtil.getInstance().getRetrofitServie(HomeApis.class);
    }
    @Override
    public void onGetBanner(IBannerCallBack iBannerCallBack) {
        createrRetrofit().getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBannerBean homeBannerBean) {
                        if(iBannerCallBack!=null){
                            iBannerCallBack.onBanner(homeBannerBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onGetSearch(String keyWord, ISerachCallBack iSerachCallBack) {
        createrRetrofit().getHomeSearch(keyWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeSearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeSearchBean homeSearchBean) {
                        if(iSerachCallBack!=null){
                            iSerachCallBack.onSerach(homeSearchBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onGetPlateList(IPlateListCallBack iPlateListCallBack) {
        createrRetrofit().getHomePlateList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomePlateListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomePlateListBean homePlateListBean) {
                        if(iPlateListCallBack!=null){
                            iPlateListCallBack.onPlateList(homePlateListBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onGetFindList(int plateId, int page, int count, IFindListCallBack iFindListCallBack) {
        createrRetrofit().getHomeList(plateId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeFindListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeFindListBean homeFindListBean) {
                        if(iFindListCallBack!=null){
                            iFindListCallBack.onFindList(homeFindListBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
