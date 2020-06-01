package com.wd.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wd.common.base.util.Base.BaseFragment;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.activity.HomeDrugDetailActivity;
import com.wd.home.adapter.HomeDiseasesLeftAdapter;
import com.wd.home.adapter.HomeDrugLeftAdapter;
import com.wd.home.adapter.HomeDrugRightAdapter;
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
import com.wd.home.presenter.HomePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Time: 2020/5/26
 * Author: 王冠华
 * Description:
 */
public class HomeDrugFragment extends BaseFragment implements IHomeContract.IView {
    @BindView(R2.id.rv_knowledge_drug_left)
    RecyclerView rvLeft;
    @BindView(R2.id.rv_knowledge_drug_right)
    RecyclerView rvRight;
    Unbinder unbinder;

    @Override
    protected BasePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_drug;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter instanceof IHomeContract.IPresenter) {
            ((IHomeContract.IPresenter)presenter).getHomeDrugsCategory();
            ((IHomeContract.IPresenter)presenter).getHomeDrugsKnowledge(1,1,12);
        }
    }
    @Override
    public void onHomeDrugsCategory(HomeFindDrugsCategoryBean homeFindDrugsCategoryBean) {
        List<HomeFindDrugsCategoryBean.ResultBean> list = homeFindDrugsCategoryBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        HomeDrugLeftAdapter adapter = new HomeDrugLeftAdapter(getActivity(), list);
        rvLeft.setLayoutManager(manager);
        rvLeft.setAdapter(adapter);
        adapter.Click(new HomeDrugLeftAdapter.onClick() {
            @Override
            public void setClick(int id, int position) {
                //点击变色
                for (HomeFindDrugsCategoryBean.ResultBean bean:list){
                    bean.setCheck(false);
                }
                list.get(position).setCheck(true);
                BasePresenter presenter = getPresenter();
                if (presenter instanceof IHomeContract.IPresenter) {
                    ((IHomeContract.IPresenter)presenter).getHomeDrugsKnowledge(id,1,12);
                }
                adapter.notifyDataSetChanged();
            }

        });

    }

    @Override
    public void onHomeDrugsKnowledge(HomeDrugsKnowledgeBean homeDrugsKnowledgeBean) {
        List<HomeDrugsKnowledgeBean.ResultBean> list = homeDrugsKnowledgeBean.getResult();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        HomeDrugRightAdapter adapter = new HomeDrugRightAdapter(getActivity(), list);
        rvRight.setLayoutManager(manager);
        rvRight.setAdapter(adapter);
        adapter.Click(new HomeDrugRightAdapter.onClick() {
            @Override
            public void setClick(int id, String name) {
                Intent intent = new Intent(getActivity(), HomeDrugDetailActivity.class);
                intent.putExtra("drugId",id);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onHomeDrugsDetail(HomeDrugsDetailBean homeDrugsDetailBean) {

    }

    @Override
    public void onDetailCollection(HomeDetailCollectionBean homeDetailCollectionBean) {

    }

    @Override
    public void onDetailCanelCollection(HomeDetailDeleteBean homeDetailDeleteBean) {

    }

    @Override
    public void onBanner(HomeBannerBean homeBannerBean) {

    }

    @Override
    public void onSerach(HomeSearchBean homeSearchBean) {

    }

    @Override
    public void onPlateList(HomePlateListBean homePlateListBean) {

    }

    @Override
    public void onFindList(HomeFindListBean homeFindListBean) {

    }

    @Override
    public void onDetail(HomeDetailBean homeDetailBean) {

    }

    @Override
    public void onHomeDepartment(HomeDepartmentBean homeDepartmentBean) {

    }

    @Override
    public void onFindDisease(HomeFindDiseaseBean homeFindDiseaseBean) {

    }

    @Override
    public void onHomeDiseaseDetail(HomeDiseaseDetailBean homeDiseaseDetailBean) {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
