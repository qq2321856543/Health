package com.wd.my.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.my.R;
import com.wd.my.R2;
import com.wd.my.bean.ConsultationBean;
import com.wd.my.bean.EndInquiryBean;
import com.wd.my.contract.CurrentConsultationContract;
import com.wd.my.persenter.CurrentConsultationPeresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CurrentConsultationActivity extends BaseAcitvity implements CurrentConsultationContract.IView, View.OnClickListener {

    private ImageView ivCurrentBack;
    private LinearLayout lly_current_nomessage;
    private ImageView iv_max;
    private Button bt_left;
    private Button bt_right;
    private TextView tv_name;
    private TextView tv_type;
    private TextView tv_keshi;
    private TextView tv_shijian;
    private RelativeLayout rl;
    private ConsultationBean.ResultBean result;

    @Override
    protected BasePresenter initPresenter() {
        return new CurrentConsultationPeresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_current_consultation;
    }

    @Override
    protected void initView() {
        ivCurrentBack = findViewById(R.id.iv_current_back);
        lly_current_nomessage = findViewById(R.id.lly_current_nomessage);
        iv_max = findViewById(R.id.iv_max);
        bt_left = findViewById(R.id.bt_left);
        bt_right = findViewById(R.id.bt_right);
        tv_name = findViewById(R.id.tv_name);
        tv_type = findViewById(R.id.tv_type);
        tv_keshi = findViewById(R.id.tv_keshi);
        tv_shijian = findViewById(R.id.tv_shijian);
        rl = findViewById(R.id.rl);
        BasePresenter persener = getPresenter();
        if (persener != null && persener instanceof CurrentConsultationPeresenter) {
            ((CurrentConsultationPeresenter) persener).getCurrentConsultation();
        }
    }

    @Override
    protected void initData() {
        bt_left.setOnClickListener(this);
        bt_right.setOnClickListener(this);
        ivCurrentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void getSuccess(ConsultationBean consultationBean) {
        result = consultationBean.getResult();

        if (consultationBean.getResult()!=null){
            lly_current_nomessage.setVisibility(View.GONE);
            rl.setVisibility(View.VISIBLE);
            Glide.with(CurrentConsultationActivity.this).load(consultationBean.getResult().getImagePic()).into(iv_max);
            tv_name.setText(consultationBean.getResult().getDoctorName());
            tv_type.setText(consultationBean.getResult().getJobTitle());
            tv_keshi.setText(consultationBean.getResult().getDepartment());
            Date date = new Date(consultationBean.getResult().getInquiryTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(date);
            String[] split = format.split("-");
            tv_shijian.setText("问诊时间 "+split[0]+"."+split[1]+"."+split[2]);
        }else {
            lly_current_nomessage.setVisibility(View.VISIBLE);
            rl.setVisibility(View.GONE);
        }
    }

    @Override
    public void getFailed(String msg) {

    }

    @Override
    public void getEndInquirySuccess(EndInquiryBean endInquiryBean) {
        Toast.makeText(this, ""+endInquiryBean.getMessage(), Toast.LENGTH_SHORT).show();
        lly_current_nomessage.setVisibility(View.VISIBLE);
        rl.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.bt_left){
            ARouter.getInstance().build("/inquiry/SpeakActivity").withString("UserName",result.getUserName()).withString("doctorname",result.getDoctorName()).navigation();

        }else if (v.getId()==R.id.bt_right){
            BasePresenter presenter = getPresenter();
            if (presenter != null) {
                ((CurrentConsultationContract.IPersenter)presenter).getEndInquiry(result.getRecordId());
            }
        }
    }
}
