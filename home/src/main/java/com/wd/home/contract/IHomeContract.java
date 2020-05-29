package com.wd.home.contract;

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
        void onDetail(HomeDetailBean homeDetailBean);
        void onHomeDepartment(HomeDepartmentBean homeDepartmentBean);
        void onFindDisease(HomeFindDiseaseBean homeFindDiseaseBean);
        void onHomeDiseaseDetail(HomeDiseaseDetailBean homeDiseaseDetailBean);
        void onHomeDrugsCategory(HomeFindDrugsCategoryBean homeFindDrugsCategoryBean);
        void onHomeDrugsKnowledge(HomeDrugsKnowledgeBean homeDrugsKnowledgeBean);
        void onHomeDrugsDetail(HomeDrugsDetailBean homeDrugsDetailBean);
        void onDetailCollection(HomeDetailCollectionBean homeDetailCollectionBean);
        void onDetailCanelCollection(HomeDetailDeleteBean homeDetailDeleteBean);
    }
    interface IPresenter{
        void getBanner();
        void getSearch(String keyWord);
        void getPlateList();
        void getFindList(int plateId,int page,int count);
        void getDetail(int infoId);
        void getHomeDepartment();
        void getFindDisease(int departmentId);
        void getHomeDiseaseDetail(int id);
        void getHomeDrugsCategory();
        void getHomeDrugsKnowledge(int drugsCategoryId,int page,int count);
        void getHomeDrugsDetail(int id);
        void getDetailCollection(int infoId);
        void getDetailCanelCollection(int infoId);
    }
    interface IModel{
        void onGetBanner(IBannerCallBack iBannerCallBack);
        void onGetSearch(String keyWord,ISerachCallBack iSerachCallBack);
        void onGetPlateList(IPlateListCallBack iPlateListCallBack);
        void onGetFindList(int plateId,int page,int count,IFindListCallBack iFindListCallBack);
        void onGetDetail(int infoId,IDetailCallBack iDetailCallBack);
        void onGetHomeDepartment(IHomeDepartmentCallBack iHomeDepartmentCallBack);
        void getFindDisease(int departmentId,IFindDiseaseCallBack iFindDiseaseCallBack);
        void getHomeDiseaseDetail(int id,IHomeDiseaseDetailCallBack iHomeDiseaseDetailCallBack);
        void getHomeDrugsCategory(IHomeDrugsCategoryCallBack iHomeDrugsCategoryCallBack);
        void getHomeDrugsKnowledge(int drugsCategoryId,int page,int count,IHomeDrugsKnowledgeCallBack iHomeDrugsKnowledgeCallBack);
        void getHomeDrugsDetail(int id,IHomeDrugsDetailCallBack iHomeDrugsDetailCallBack);
        void getDetailCollection(int infoId,IDetailCollectionCallBack iDetailCollectionCallBack);
        void getDetailCanelCollection(int infoId,IDetailCanelCollectionCallBack iDetailCanelCollectionCallBack);
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
        interface IDetailCallBack{
            void onDetail(HomeDetailBean homeDetailBean);
        }
        interface IHomeDepartmentCallBack{
            void onHomeDepartment(HomeDepartmentBean homeDepartmentBean);
        }
        interface IFindDiseaseCallBack{
            void onFindDisease(HomeFindDiseaseBean homeFindDiseaseBean);
        }
        interface IHomeDiseaseDetailCallBack{
            void onHomeDiseaseDetail(HomeDiseaseDetailBean homeDiseaseDetailBean);
        }
        interface IHomeDrugsCategoryCallBack{
            void onHomeDrugsCategory(HomeFindDrugsCategoryBean homeFindDrugsCategoryBean);
        }
        interface IHomeDrugsKnowledgeCallBack{
            void onHomeDrugsKnowledge(HomeDrugsKnowledgeBean homeDrugsKnowledgeBean);
        }
        interface IHomeDrugsDetailCallBack{
            void onHomeDrugsDetail(HomeDrugsDetailBean homeDrugsDetailBean);
        }
        interface IDetailCollectionCallBack{
            void onDetailCollection(HomeDetailCollectionBean homeDetailCollectionBean);
        }
        interface IDetailCanelCollectionCallBack{
            void onDetailCanelCollection(HomeDetailDeleteBean homeDetailDeleteBean);
        }
    }
}
