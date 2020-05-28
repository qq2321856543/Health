package com.wd.inquiry.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.inquiry.R;
import com.wd.inquiry.R2;
import com.wd.inquiry.adapter.DoctorInfoAdapter;
import com.wd.inquiry.bean.DoctorInfoBean;
import com.wd.inquiry.bean.FollowDoctorBean;
import com.wd.inquiry.icoolor.ICoolor_DoctorInfo;
import com.wd.inquiry.presenter.Presenter_DoctorInfo;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DoctorInfoActivity extends BaseAcitvity implements ICoolor_DoctorInfo.IView, View.OnClickListener {

    @BindView(R2.id.iv_back)
    ImageView iv_back;
    @BindView(R2.id.iv_touxiang)
    ImageView iv_touxiang;
    @BindView(R2.id.iv_xin)
    ImageView iv_xin;
    @BindView(R2.id.tv_name)
    TextView tv_name;
    @BindView(R2.id.tv_type)
    TextView tv_type;
    @BindView(R2.id.tv_location)
    TextView tv_location;
    @BindView(R2.id.tv_haoping)
    TextView tv_haoping;
    @BindView(R2.id.tv_size)
    TextView tv_size;
    @BindView(R2.id.tv_pinglunsize)
    TextView tv_pinglunsize;
    @BindView(R2.id.tv_one)
    TextView tv_one;
    @BindView(R2.id.tv_two)
    TextView tv_two;
    @BindView(R2.id.tv_three)
    TextView tv_three;
    @BindView(R2.id.tv_jianli)
    TextView tv_jianli;
    @BindView(R2.id.tv_lingyu)
    TextView tv_lingyu;
    @BindView(R2.id.rv)
    RecyclerView rv;
    @BindView(R2.id.tv_gengduo)
    TextView tv_gengduo;
    @BindView(R2.id.tv_bi)
    TextView tv_bi;
    @BindView(R2.id.bt_zixun)
    Button bt_zixun;
    private DoctorInfoAdapter doctorInfoAdapter;
    List<DoctorInfoBean.ResultBean.CommentListBean> arrayList = new ArrayList<>();
    private List<DoctorInfoBean.ResultBean.CommentListBean> commentList;
    private DoctorInfoBean.ResultBean result;

    @Override
    protected BasePresenter initPresenter() {
        return new Presenter_DoctorInfo(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_doctor_info;
    }

    @Override
    protected void initView() {
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        doctorInfoAdapter = new DoctorInfoAdapter(this);
        rv.setAdapter(doctorInfoAdapter);
    }

    @Override
    protected void initData() {
        iv_back.setOnClickListener(this);
        tv_gengduo.setOnClickListener(this);
        iv_xin.setOnClickListener(this);
        //医生id
        int id = getIntent().getIntExtra("id", 0);

        BasePresenter presenter = getPresenter();
        if (presenter != null) {
            ((ICoolor_DoctorInfo.IPresenter)presenter).getDoctorInfo(id);
        }
    }

    @Override
    public void getDoctorInfoSuccess(DoctorInfoBean doctorInfoBean) {
        result = doctorInfoBean.getResult();
        //更新UI
        Glide.with(DoctorInfoActivity.this).load(doctorInfoBean.getResult().getImagePic()).into(iv_touxiang);
        tv_name.setText(doctorInfoBean.getResult().getDoctorName());
        tv_type.setText(doctorInfoBean.getResult().getJobTitle());
        tv_location.setText(doctorInfoBean.getResult().getInauguralHospital());
        tv_haoping.setText("好评率 "+doctorInfoBean.getResult().getPraise());
        tv_size.setText("服务患者数 "+doctorInfoBean.getResult().getServerNum());
        if (doctorInfoBean.getResult().getDoctorReceiveGiftList().size()!=0){
            //礼物数量
        }
        tv_pinglunsize.setText("（"+doctorInfoBean.getResult().getCommentList().size()+"条评论）");
        tv_jianli.setText(doctorInfoBean.getResult().getPersonalProfile());
        tv_lingyu.setText(doctorInfoBean.getResult().getGoodField());
        tv_bi.setText(doctorInfoBean.getResult().getServicePrice()+"H币/次");
        if (doctorInfoBean.getResult().getFollowFlag()==1){
            iv_xin.setImageResource(R.mipmap.common_icon_attention_small_s);
        }else {
            iv_xin.setImageResource(R.mipmap.common_icon_attention_small_n);
        }
        commentList = doctorInfoBean.getResult().getCommentList();
        if (commentList.size()!=0){
            if (commentList.size()<=3){
                doctorInfoAdapter.setData(commentList);

            }else {
                for (int i = 0; i < 3; i++) {
                    arrayList.add(commentList.get(i));
                }
                doctorInfoAdapter.setData(arrayList);
                tv_gengduo.setText("点击查看更多评论");
            }

        }

    }

    @Override
    public void getFollowDoctorSuccess(FollowDoctorBean followDoctorBean) {
        if (followDoctorBean.getMessage().equals("关注成功")){
            Toast.makeText(this, ""+followDoctorBean.getMessage(), Toast.LENGTH_SHORT).show();
            iv_xin.setImageResource(R.mipmap.common_icon_attention_small_s);
            BasePresenter presenter = getPresenter();
            if (presenter != null) {
                ((ICoolor_DoctorInfo.IPresenter)presenter).getDoctorInfo(result.getDoctorId());
            }
        }else {
            Toast.makeText(this, ""+followDoctorBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getCancelFollowDoctorSuccess(FollowDoctorBean followDoctorBean) {
        if (followDoctorBean.getMessage().equals("取消关注成功")){
            Toast.makeText(this, ""+followDoctorBean.getMessage(), Toast.LENGTH_SHORT).show();
            iv_xin.setImageResource(R.mipmap.common_icon_attention_small_n);
            BasePresenter presenter = getPresenter();
            if (presenter != null) {
                ((ICoolor_DoctorInfo.IPresenter)presenter).getDoctorInfo(result.getDoctorId());
            }
        }else {
            Toast.makeText(this, ""+followDoctorBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.iv_back){
            finish();
        }else if (id==R.id.tv_gengduo){
            EventBus.getDefault().postSticky(commentList);
            Intent intent = new Intent(DoctorInfoActivity.this, CommentListActivity.class);
            startActivity(intent);
        }else if (id==R.id.iv_xin){
            if (result.getFollowFlag()==1){
                //关注
                BasePresenter presenter = getPresenter();
                if (presenter != null) {
                    ((ICoolor_DoctorInfo.IPresenter)presenter).getCancelFollowDoctor(result.getDoctorId());
                }
            }else {
                //未关注
                BasePresenter presenter = getPresenter();
                if (presenter != null) {
                    ((ICoolor_DoctorInfo.IPresenter)presenter).getFollowDoctor(result.getDoctorId());
                }
            }
        }
    }
}
