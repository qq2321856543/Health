package com.wd.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.wd.common.base.util.Base.BaseFragment;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.activity.HomeInformationActivity;
import com.wd.home.adapter.HomeHealthListAdapter;
import com.wd.home.adapter.HomeHealthTitleAdapter;
import com.wd.home.bean.HomeBannerBean;
import com.wd.home.bean.HomeDetailBean;
import com.wd.home.bean.HomeFindListBean;
import com.wd.home.bean.HomePlateListBean;
import com.wd.home.bean.HomeSearchBean;
import com.wd.home.contract.IHomeContract;
import com.wd.home.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Time: 2020/5/22
 * Author: 王冠华
 * Description:
 */
public class HomeHomeFragment extends BaseFragment implements IHomeContract.IView {
    @BindView(R2.id.xb_home)
    XBanner xbHome;
    @BindView(R2.id.iv_home_head)
    SimpleDraweeView ivHomeHead;
    @BindView(R2.id.et_home_search)
    EditText etHomeSearch;

    @BindView(R2.id.rv_home_health)
    RecyclerView rvHomeHealth;
    Unbinder unbinder;
    ArrayList<String> str = new ArrayList<>();
    @BindView(R2.id.rv_home_title)
    RecyclerView rvHomeTitle;


    @Override
    protected BasePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        etHomeSearch.getBackground().setAlpha(30);
    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter instanceof IHomeContract.IPresenter) {
            ((IHomeContract.IPresenter) presenter).getBanner();
            ((IHomeContract.IPresenter) presenter).getPlateList();
            (( IHomeContract.IPresenter)presenter).getFindList(1,1,5);
        }

    }

    @Override
    public void onBanner(HomeBannerBean homeBannerBean) {
        List<HomeBannerBean.ResultBean> list = homeBannerBean.getResult();
        xbHome.setPointPosition(XBanner.RIGHT);
//        xbHome.setPageTransformer(Transformer.Accordion);
        xbHome.setBannerData(list);
        xbHome.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                HomeBannerBean.ResultBean bean = list.get(position);
                String jumpUrl = bean.getJumpUrl();
                String imageUrl = bean.getImageUrl();
                Glide.with(getActivity()).load(imageUrl).placeholder(R.mipmap.ic_launcher).into((ImageView) view);
            }
        });
    }

    @Override
    public void onSerach(HomeSearchBean homeSearchBean) {

    }

    @Override
    public void onPlateList(HomePlateListBean homePlateListBean) {
        List<HomePlateListBean.ResultBean> result = homePlateListBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        HomeHealthTitleAdapter adapter = new HomeHealthTitleAdapter(getActivity(), result);
        rvHomeTitle.setLayoutManager(linearLayoutManager);
        rvHomeTitle.setAdapter(adapter);
        adapter.Click(new HomeHealthTitleAdapter.onClick() {
            @Override
            public void setClick(int id) {
                BasePresenter presenter = getPresenter();
                if (presenter instanceof IHomeContract.IPresenter) {
                    (( IHomeContract.IPresenter)presenter).getFindList(id,1,5);
                }
            }
        });
    }

    @Override
    public void onFindList(HomeFindListBean homeFindListBean) {
        List<HomeFindListBean.ResultBean> list = homeFindListBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        HomeHealthListAdapter adapter = new HomeHealthListAdapter(getActivity(), list);
        rvHomeHealth.setLayoutManager(manager);
        rvHomeHealth.setAdapter(adapter);
        adapter.Click(new HomeHealthListAdapter.onClick() {
            @Override
            public void setClick(int id, String img) {
                Intent intent = new Intent(getActivity(), HomeInformationActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("img",img);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDetail(HomeDetailBean homeDetailBean) {

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
