package com.wd.home.contract;

import com.wd.common.base.util.Base.IBaseView;
import com.wd.home.bean.HomeBannerBean;
import com.wd.home.bean.HomeFindListBean;
import com.wd.home.bean.HomePlateListBean;
import com.wd.home.bean.HomeSearchBean;

/**
 * Time: 2020/5/22
 * Author: 王冠华
 * Description:
 */
public interface IHomeContract {
    interface IView extends IBaseView{
        void onBanner(HomeBannerBean homeBannerBean);
        void onSerach(HomeSearchBean homeSearchBean);
        void onPlateList(HomePlateListBean homePlateListBean);
        void onFindList(HomeFindListBean homeFindListBean);
    }
    interface IPresenter{
        void getBanner();
        void getSearch(String keyWord);
        void getPlateList();
        void getFindList(int plateId,int page,int count);
    }
    interface IModel{
        void onGetBanner(IBannerCallBack iBannerCallBack);
        void onGetSearch(String keyWord,ISerachCallBack iSerachCallBack);
        void onGetPlateList(IPlateListCallBack iPlateListCallBack);
        void onGetFindList(int plateId,int page,int count,IFindListCallBack iFindListCallBack);
        interface IBannerCallBack{
            void onBanner(HomeBannerBean homeBannerBean);
        }
        interface ISerachCallBack{
            void onSerach(HomeSearchBean homeSearchBean);
        }
        interface IPlateListCallBack{
            void onPlateList(HomePlateListBean homePlateListBean);
        }
        interface IFindListCallBack{
            void onFindList(HomeFindListBean homeFindListBean);
        }
    }
}
