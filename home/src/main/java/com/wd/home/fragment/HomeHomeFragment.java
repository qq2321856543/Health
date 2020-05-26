package com.wd.home.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.wd.common.base.util.Base.BaseFragment;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.util.SPUtils;
import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.activity.HomeInformationActivity;
import com.wd.home.activity.HomeKnowledgeActivity;
import com.wd.home.adapter.HomeConsultAdapter;
import com.wd.home.adapter.HomeHealthListAdapter;
import com.wd.home.adapter.HomeHealthTitleAdapter;
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
    @BindView(R2.id.rv_home_consult)
    RecyclerView rvHomeConsult;
    @BindView(R2.id.iv_home_diseases)
    ImageView ivHomeDiseases;
    @BindView(R2.id.iv_home_drug)
    ImageView ivHomeDrug;


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
            ((IHomeContract.IPresenter) presenter).getFindList(1, 1, 5);
            ((IHomeContract.IPresenter) presenter).getHomeDepartment();
        }
        ivHomeDiseases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeKnowledgeActivity.class);
                intent.putExtra("page",0);
                startActivity(intent);
            }
        });
        ivHomeDrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeKnowledgeActivity.class);
                intent.putExtra("page",1);
                startActivity(intent);
            }
        });
        String head = SPUtils.getString(getActivity(), SPUtils.USERINFO_NAME, "head");
        Uri uri = Uri.parse(head);
        ivHomeHead.setImageURI(uri);
        ivHomeHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/my/MyMyMainActivity").navigation();
            }
        });
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
                    ((IHomeContract.IPresenter) presenter).getFindList(id, 1, 5);
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
                intent.putExtra("id", id);
                intent.putExtra("img", img);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDetail(HomeDetailBean homeDetailBean) {

    }

    @Override
    public void onHomeDepartment(HomeDepartmentBean homeDepartmentBean) {
        List<HomeDepartmentBean.ResultBean> list = homeDepartmentBean.getResult();
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        HomeConsultAdapter adapter = new HomeConsultAdapter(getActivity(), list);
        rvHomeConsult.setLayoutManager(manager);
        rvHomeConsult.setAdapter(adapter);
        adapter.Click(new HomeConsultAdapter.onClick() {
            @Override
            public void setClick(int id) {
                ARouter.getInstance().build("/inquiry/InquiryMainActivity") .withInt("id",id).navigation();

            }
        });
    }

    @Override
    public void onFindDisease(HomeFindDiseaseBean homeFindDiseaseBean) {

    }

    @Override
    public void onHomeDiseaseDetail(HomeDiseaseDetailBean homeDiseaseDetailBean) {

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
