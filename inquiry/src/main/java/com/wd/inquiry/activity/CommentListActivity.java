package com.wd.inquiry.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.inquiry.R;
import com.wd.inquiry.R2;
import com.wd.inquiry.adapter.DoctorInfoAdapter;
import com.wd.inquiry.bean.DoctorInfoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class CommentListActivity extends BaseAcitvity {

    @BindView(R2.id.iv_back)
    ImageView iv_back;
    @BindView(R2.id.rv)
    RecyclerView rv;
    private DoctorInfoAdapter doctorInfoAdapter;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_comment_list;
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
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData(List<DoctorInfoBean.ResultBean.CommentListBean> commentList){
        doctorInfoAdapter.setData(commentList);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
