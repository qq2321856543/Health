package com.wd.home.presenter;

import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.home.bean.HomeBannerBean;
import com.wd.home.bean.HomeDepartmentBean;
import com.wd.home.bean.HomeDetailBean;
import com.wd.home.bean.HomeDetailCollectionBean;
import com.wd.home.bean.HomeDetailDeleteBean;
import com.wd.home.bean.HomeDiseaseDetailBean;
import com.wd.home.bean.HomeDrugsDetailBean;
import com.wd.home.bean.HomeDrugsKnowledgeBean;
import com.wd.home.bean.HomeFindDiseaseBean;
import com.wd.home.bean.HomeFindDrugsCategoryBean;
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

    @Override
    public void getHomeDepartment() {
        model.onGetHomeDepartment(new IHomeContract.IModel.IHomeDepartmentCallBack() {
            @Override
            public void onHomeDepartment(HomeDepartmentBean homeDepartmentBean) {
                IBaseView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onHomeDepartment(homeDepartmentBean);
                }
            }
        });

    }

    @Override
    public void getFindDisease(int departmentId) {
        model.getFindDisease(departmentId, new IHomeContract.IModel.IFindDiseaseCallBack() {
            @Override
            public void onFindDisease(HomeFindDiseaseBean homeFindDiseaseBean) {
                IBaseView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onFindDisease(homeFindDiseaseBean);
                }
            }
        });
    }

    @Override
    public void getHomeDiseaseDetail(int id) {
        model.getHomeDiseaseDetail(id, new IHomeContract.IModel.IHomeDiseaseDetailCallBack() {
            @Override
            public void onHomeDiseaseDetail(HomeDiseaseDetailBean homeDiseaseDetailBean) {
                IBaseView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onHomeDiseaseDetail(homeDiseaseDetailBean);
                }
            }
        });
    }

    @Override
    public void getHomeDrugsCategory() {
        model.getHomeDrugsCategory(new IHomeContract.IModel.IHomeDrugsCategoryCallBack() {
            @Override
            public void onHomeDrugsCategory(HomeFindDrugsCategoryBean homeFindDrugsCategoryBean) {
                IBaseView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onHomeDrugsCategory(homeFindDrugsCategoryBean);
                }
            }
        });
    }

    @Override
    public void getHomeDrugsKnowledge(int drugsCategoryId, int page, int count) {
        model.getHomeDrugsKnowledge(drugsCategoryId, page, count, new IHomeContract.IModel.IHomeDrugsKnowledgeCallBack() {
            @Override
            public void onHomeDrugsKnowledge(HomeDrugsKnowledgeBean homeDrugsKnowledgeBean) {
                IBaseView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onHomeDrugsKnowledge(homeDrugsKnowledgeBean);
                }
            }
        });
    }

    @Override
    public void getHomeDrugsDetail(int id) {
        model.getHomeDrugsDetail(id, new IHomeContract.IModel.IHomeDrugsDetailCallBack() {
            @Override
            public void onHomeDrugsDetail(HomeDrugsDetailBean homeDrugsDetailBean) {
                IBaseView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onHomeDrugsDetail(homeDrugsDetailBean);
                }
            }
        });
    }

    @Override
    public void getDetailCollection(int infoId) {
        model.getDetailCollection(infoId, new IHomeContract.IModel.IDetailCollectionCallBack() {
            @Override
            public void onDetailCollection(HomeDetailCollectionBean homeDetailCollectionBean) {
                IBaseView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onDetailCollection(homeDetailCollectionBean);
                }
            }
        });
    }

    @Override
    public void getDetailCanelCollection(int infoId) {
        model.getDetailCanelCollection(infoId, new IHomeContract.IModel.IDetailCanelCollectionCallBack() {
            @Override
            public void onDetailCanelCollection(HomeDetailDeleteBean homeDetailDeleteBean) {
                IBaseView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onDetailCanelCollection(homeDetailDeleteBean);
                }
            }
        });
    }
}
