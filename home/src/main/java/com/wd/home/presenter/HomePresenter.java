package com.wd.home.presenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.home.bean.HomeBannerBean;
import com.wd.home.bean.HomeDetailBean;
import com.wd.home.bean.HomeFindListBean;
import com.wd.home.bean.HomePlateListBean;
import com.wd.home.bean.HomeSearchBean;
import com.wd.home.contract.IHomeContract;
import com.wd.home.model.HomeModel;

/**
 * Time: 2020/5/22
 * Author: 王冠华
 * Description:
 */
public class HomePresenter extends BasePresenter implements IHomeContract.IPresenter {

    private HomeModel model;

    public HomePresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    protected void initModel() {
        model = new HomeModel();
    }

    @Override
    public void getBanner() {
        model.onGetBanner(new IHomeContract.IModel.IBannerCallBack() {
            @Override
            public void onBanner(HomeBannerBean homeBannerBean) {
                IBaseView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onBanner(homeBannerBean);
                }
            }
        });
    }

    @Override
    public void getSearch(String keyWord) {
        model.onGetSearch(keyWord, new IHomeContract.IModel.ISerachCallBack() {
            @Override
            public void onSerach(HomeSearchBean homeSearchBean) {
                IBaseView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onSerach(homeSearchBean);
                }
            }
        });
    }

    @Override
    public void getPlateList() {
        model.onGetPlateList(new IHomeContract.IModel.IPlateListCallBack() {
            @Override
            public void onPlateList(HomePlateListBean homePlateListBean) {
                IBaseView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onPlateList(homePlateListBean);
                }
            }
        });
    }

    @Override
    public void getFindList(int plateId, int page, int count) {
        model.onGetFindList(plateId, page, count, new IHomeContract.IModel.IFindListCallBack() {
            @Override
            public void onFindList(HomeFindListBean homeFindListBean) {
                IBaseView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onFindList(homeFindListBean);
                }
            }
        });
    }

    @Override
    public void getDetail(int infoId) {
        model.onGetDetail(infoId, new IHomeContract.IModel.IDetailCallBack() {
            @Override
            public void onDetail(HomeDetailBean homeDetailBean) {
                IBaseView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onDetail(homeDetailBean);
                }
            }
        });
    }
}
