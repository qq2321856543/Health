package com.wd.home.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.util.SPUtils;
import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.adapter.SymptomDetailsAdapter;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeDiseasesDetailActivity extends BaseAcitvity implements IHomeContract.IView {


    @BindView(R2.id.iv_home_head)
    SimpleDraweeView ivHomeHead;
    @BindView(R2.id.rl1)
    RelativeLayout rl1;
    @BindView(R2.id.tv_1)
    TextView tv1;
    @BindView(R2.id.tv_name)
    TextView tvName;
    @BindView(R2.id.rv_bz)
    RecyclerView rv;
    private List<HomeDiseaseDetailBean.ResultBean> list;

    @Override
    protected BasePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_symptom_details;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        String head = SPUtils.getString(this, SPUtils.USERINFO_NAME, "head");
        Uri uri = Uri.parse(head);
        ivHomeHead.setImageURI(uri);
        ivHomeHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/my/MyMyMainActivity").navigation();
            }
        });
        Intent intent = getIntent();
        int id = intent.getIntExtra("diseaseId",0);
        String name = intent.getStringExtra("name");
        tvName.setText(name);
        BasePresenter presenter = getPresenter();
        if(presenter instanceof IHomeContract.IPresenter){
            ((IHomeContract.IPresenter) presenter).getHomeDiseaseDetail(id);
        }
    }

    @Override
    public void onHomeDiseaseDetail(HomeDiseaseDetailBean homeDiseaseDetailBean) {
        HomeDiseaseDetailBean.ResultBean result = homeDiseaseDetailBean.getResult();
        list = new ArrayList<HomeDiseaseDetailBean.ResultBean>();
        list.add(result);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        SymptomDetailsAdapter symptomDetailsAdapter = new SymptomDetailsAdapter(this, list);
        rv.setAdapter(symptomDetailsAdapter);
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
    public void onHomeDrugsCategory(HomeFindDrugsCategoryBean homeFindDrugsCategoryBean) {

    }

    @Override
    public void onHomeDrugsKnowledge(HomeDrugsKnowledgeBean homeDrugsKnowledgeBean) {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
