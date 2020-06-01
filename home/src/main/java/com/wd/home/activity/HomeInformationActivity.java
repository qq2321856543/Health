package com.wd.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.home.R;
import com.wd.home.R2;
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
    int i=1;
    private int collection;
    private HomeDetailBean.ResultBean result;

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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
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

        //点击收藏
        ivCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeInformationActivity.this, "是否"+result.getWhetherCollection(), Toast.LENGTH_SHORT).show();
                if(result.getWhetherCollection()==1){
                    Toast.makeText(HomeInformationActivity.this, "1", Toast.LENGTH_SHORT).show();
                    BasePresenter presenter = getPresenter();
                    if(presenter instanceof IHomeContract.IPresenter){
                        ((IHomeContract.IPresenter)presenter).getDetailCollection(id);
                    }
                }else {
                    Toast.makeText(HomeInformationActivity.this, "2", Toast.LENGTH_SHORT).show();
                    BasePresenter presenter = getPresenter();
                    if(presenter instanceof IHomeContract.IPresenter){
                        ((IHomeContract.IPresenter)presenter).getDetailCanelCollection(id);
                    }
                }

            }
        });
//        if(result.getWhetherCollection()==1){
//            ivCollection.setImageResource(R.mipmap.home_collection_null);
//        }else if(result.getWhetherCollection()==2){
//            ivCollection.setImageResource(R.mipmap.home_collection_have);
//        }
    }
    @Override
    public void onDetail(HomeDetailBean homeDetailBean) {
        result = homeDetailBean.getResult();
        int id = result.getId();
        tvTitle.setText(result.getTitle());
        tvAuthor.setText(result.getSource());
        Date date = new Date(result.getReleaseTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM");
        String format = simpleDateFormat.format(date);
        tvTime.setText(format);
        tvContent.setText(result.getContent());
        Uri uri = Uri.parse(img);
        iv.setImageURI(uri);
        collection = result.getWhetherCollection();


    }
    @Override
    public void onDetailCollection(HomeDetailCollectionBean homeDetailCollectionBean) {
        String message = homeDetailCollectionBean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        if(message.equals("资讯收藏成功")){
            //成功更换图片
            if(result.getWhetherCollection()==0){
                ivCollection.setImageResource(R.mipmap.home_collection_null);
            }else if(result.getWhetherCollection()==1){
                ivCollection.setImageResource(R.mipmap.home_collection_have);
            }
            HomeDetailBean bean = new HomeDetailBean();
        }
    }

    @Override
    public void onDetailCanelCollection(HomeDetailDeleteBean homeDetailDeleteBean) {
        String message = homeDetailDeleteBean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        if(message.equals("取消成功")){
            //成功更换图片
            if(collection==0){
                ivCollection.setImageResource(R.mipmap.home_collection_null);
            }else if(collection==1){
                ivCollection.setImageResource(R.mipmap.home_collection_have);
            }
            HomeDetailBean bean = new HomeDetailBean();
            HomeDetailBean.ResultBean result = bean.getResult();
        }
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
