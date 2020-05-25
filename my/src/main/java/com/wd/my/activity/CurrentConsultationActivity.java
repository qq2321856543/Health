package com.wd.my.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.my.R;
import com.wd.my.R2;
import com.wd.my.bean.ConsultationBean;
import com.wd.my.contract.CurrentConsultationContract;
import com.wd.my.persenter.CurrentConsultationPeresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CurrentConsultationActivity extends BaseAcitvity implements CurrentConsultationContract.IView {

    private ImageView ivCurrentBack;
    private LinearLayout lly_current_nomessage;

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
        BasePresenter persener = getPresenter();
        if (persener != null && persener instanceof CurrentConsultationPeresenter) {
            ((CurrentConsultationPeresenter) persener).getCurrentConsultation();
        }
    }

    @Override
    protected void initData() {
        ivCurrentBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void getSuccess(ConsultationBean consultationBean) {

    }

    @Override
    public void getFailed(String msg) {

    }

}
