package com.wd.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.wd.common.base.util.Base.BaseFragment;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.adapter.HomeDiseasesLeftAdapter;
import com.wd.home.adapter.HomeDiseasesRightAdapter;
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
public class HomeDiseasesFragment extends BaseFragment implements IHomeContract.IView {
    @BindView(R2.id.rv_knowledge_diseases_left)
    RecyclerView rvLeft;
    @BindView(R2.id.rv_knowledge_diseases_right)
    RecyclerView rvRight;
    Unbinder unbinder;

    @Override
    protected BasePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_diseases;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter instanceof IHomeContract.IPresenter) {
            ((IHomeContract.IPresenter)presenter).getHomeDepartment();
            ((IHomeContract.IPresenter)presenter).getFindDisease(7);
        }

    }

    @Override
    public void onHomeDepartment(HomeDepartmentBean homeDepartmentBean) {
        List<HomeDepartmentBean.ResultBean> list = homeDepartmentBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        HomeDiseasesLeftAdapter adapter = new HomeDiseasesLeftAdapter(getActivity(), list);
        rvLeft.setLayoutManager(manager);
        rvLeft.setAdapter(adapter);
        adapter.Click(new HomeDiseasesLeftAdapter.onClick() {
            @Override
            public void setClick(int id) {
                BasePresenter presenter = getPresenter();
                if (presenter instanceof IHomeContract.IPresenter) {
                    ((IHomeContract.IPresenter)presenter).getFindDisease(id);

                }
            }
        });
    }

    @Override
    public void onFindDisease(HomeFindDiseaseBean homeFindDiseaseBean) {
        List<HomeFindDiseaseBean.ResultBean> list = homeFindDiseaseBean.getResult();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        HomeDiseasesRightAdapter adapter = new HomeDiseasesRightAdapter(getActivity(), list);
        rvRight.setLayoutManager(manager);
        rvRight.setAdapter(adapter);
    }

    @Override
    public void onHomeDiseaseDetail(HomeDiseaseDetailBean homeDiseaseDetailBean) {

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
    public void onHomeDrugsCategory(HomeFindDrugsCategoryBean homeFindDrugsCategoryBean) {

    }

    @Override
    public void onHomeDrugsKnowledge(HomeDrugsKnowledgeBean homeDrugsKnowledgeBean) {

    }

    @Override
    public void onHomeDrugsDetail(HomeDrugsDetailBean homeDrugsDetailBean) {

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
