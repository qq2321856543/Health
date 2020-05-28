package com.wd.home.model;

import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.home.bean.HomeBannerBean;
import com.wd.home.bean.HomeDepartmentBean;
import com.wd.home.bean.HomeDetailBean;
import com.wd.home.bean.HomeDiseaseDetailBean;
import com.wd.home.bean.HomeDrugsDetailBean;
import com.wd.home.bean.HomeDrugsKnowledgeBean;
import com.wd.home.bean.HomeFindDiseaseBean;
import com.wd.home.bean.HomeFindDrugsCategoryBean;
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

    @Override
    public void onGetDetail(int infoId, IDetailCallBack iDetailCallBack) {
        createrRetrofit().getHomeDetail(infoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeDetailBean homeDetailBean) {
                        if(iDetailCallBack!=null){
                            iDetailCallBack.onDetail(homeDetailBean);
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
    public void onGetHomeDepartment(IHomeDepartmentCallBack iHomeDepartmentCallBack) {
        createrRetrofit().getHomeDepartment()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeDepartmentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeDepartmentBean homeDepartmentBean) {
                        if(iHomeDepartmentCallBack!=null){
                            iHomeDepartmentCallBack.onHomeDepartment(homeDepartmentBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getFindDisease(int departmentId, IFindDiseaseCallBack iFindDiseaseCallBack) {
        createrRetrofit().getFindDisease(departmentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeFindDiseaseBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeFindDiseaseBean homeFindDiseaseBean) {
                        if(iFindDiseaseCallBack!=null){
                            iFindDiseaseCallBack.onFindDisease(homeFindDiseaseBean);
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
    public void getHomeDiseaseDetail(int id, IHomeDiseaseDetailCallBack iHomeDiseaseDetailCallBack) {
        createrRetrofit().getHomeDiseaseDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeDiseaseDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeDiseaseDetailBean homeDiseaseDetailBean) {
                        if (iHomeDiseaseDetailCallBack!=null){
                            iHomeDiseaseDetailCallBack.onHomeDiseaseDetail(homeDiseaseDetailBean);
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
    public void getHomeDrugsCategory(IHomeDrugsCategoryCallBack iHomeDrugsCategoryCallBack) {
        createrRetrofit().getHomeDrugsCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeFindDrugsCategoryBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeFindDrugsCategoryBean homeFindDrugsCategoryBean) {
                        if (iHomeDrugsCategoryCallBack!=null){
                            iHomeDrugsCategoryCallBack.onHomeDrugsCategory(homeFindDrugsCategoryBean);
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
    public void getHomeDrugsKnowledge(int drugsCategoryId, int page, int count, IHomeDrugsKnowledgeCallBack iHomeDrugsKnowledgeCallBack) {
        createrRetrofit().getHomeDrugsKnowledge(drugsCategoryId, page, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeDrugsKnowledgeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeDrugsKnowledgeBean homeDrugsKnowledgeBean) {
                        if(iHomeDrugsKnowledgeCallBack!=null){
                            iHomeDrugsKnowledgeCallBack.onHomeDrugsKnowledge(homeDrugsKnowledgeBean);
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
    public void getHomeDrugsDetail(int id, IHomeDrugsDetailCallBack iHomeDrugsDetailCallBack) {
        createrRetrofit().getHomeDrugsDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeDrugsDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeDrugsDetailBean homeDrugsDetailBean) {
                        if(iHomeDrugsDetailCallBack!=null){
                            iHomeDrugsDetailCallBack.onHomeDrugsDetail(homeDrugsDetailBean);
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
