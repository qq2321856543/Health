package com.wd.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.bean.HomeBannerBean;
import com.wd.home.bean.HomeDetailBean;
import com.wd.home.bean.HomeFindListBean;
import com.wd.home.bean.HomePlateListBean;
import com.wd.home.bean.HomeSearchBean;
import com.wd.home.contract.IHomeContract;
import com.wd.home.presenter.HomePresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;

public class HomeInformationActivity extends BaseAcitvity implements IHomeContract.IView {
    @BindView(R2.id.iv_home_detail_back)
    ImageView ivBack;
    @BindView(R2.id.iv_home_detail_message)
    ImageView ivMessage;
    @BindView(R2.id.iv_home_detail_collection)
    ImageView ivCollection;
    @BindView(R2.id.iv_home_detail_share)
    ImageView ivShare;
    @BindView(R2.id.tv_home_detail_title)
    TextView tvTitle;
    @BindView(R2.id.tv_home_detail_author)
    TextView tvAuthor;
    @BindView(R2.id.tv_home_detail_time)
    TextView tvTime;
    @BindView(R2.id.iv_home_detail)
    SimpleDraweeView iv;
    @BindView(R2.id.tv_home_detail_content)
    TextView tvContent;
    private String img;

    @Override
    protected BasePresenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home_information;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        img = intent.getStringExtra("img");
        BasePresenter presenter = getPresenter();
        if(presenter instanceof IHomeContract.IPresenter){
            ((IHomeContract.IPresenter)presenter).getDetail(id);
        }
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public void onDetail(HomeDetailBean homeDetailBean) {
        HomeDetailBean.ResultBean result = homeDetailBean.getResult();
        tvTitle.setText(result.getTitle());
        tvAuthor.setText(result.getSource());
        Date date = new Date(result.getReleaseTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM");
        String format = simpleDateFormat.format(date);
        tvTime.setText(format);
        tvContent.setText(result.getContent());
        Uri uri = Uri.parse(img);
        iv.setImageURI(uri);
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


}
