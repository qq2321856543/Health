package com.wd.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.util.SPUtils;
import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.adapter.HomeFindListAdapter;
import com.wd.home.adapter.HomeHealthListAdapter;
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

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeMoreActivity extends BaseAcitvity implements IHomeContract.IView {


    @BindView(R2.id.iv_more_head)
    SimpleDraweeView ivMoreHead;
    @BindView(R2.id.tv_more_title)
    TextView tvMoreTitle;
    @BindView(R2.id.iv_more_message)
    ImageView ivMoreMessage;

    @BindView(R2.id.rv_more)
    RecyclerView rvMore;

    @Override
    protected BasePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home_more;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void init(HomePlateListBean.ResultBean bean){
        int id = bean.getId();
        String name = bean.getName();
        tvMoreTitle.setText(name);
        BasePresenter presenter = getPresenter();
        if (presenter instanceof IHomeContract.IPresenter) {
            ((IHomeContract.IPresenter) presenter).getFindList(id, 2, 10);
        }
    }
    @Override
    protected void initData() {
        String head = SPUtils.getString(this, SPUtils.USERINFO_NAME, "head");
        Uri uri = Uri.parse(head);
        ivMoreHead.setImageURI(uri);
        ivMoreHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/my/MyMyMainActivity").navigation();
            }
        });
        tvMoreTitle.setText("健康养生");
        BasePresenter presenter = getPresenter();
        if (presenter instanceof IHomeContract.IPresenter) {
            ((IHomeContract.IPresenter) presenter).getFindList(1, 2, 10);
        }
    }

    @Override
    public void onFindList(HomeFindListBean homeFindListBean) {
        List<HomeFindListBean.ResultBean> list = homeFindListBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(HomeMoreActivity.this, RecyclerView.VERTICAL, false);
        HomeFindListAdapter adapter = new HomeFindListAdapter(HomeMoreActivity.this, list);
        rvMore.setLayoutManager(manager);
        rvMore.setAdapter(adapter);
        adapter.setOnClick(new HomeFindListAdapter.SetOnClick() {
            @Override
            public void getId(int id, String img) {
                Intent intent = new Intent(HomeMoreActivity.this, HomeInformationActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("img", img);
                startActivity(intent);
            }
        });
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
