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
import com.wd.my.contract.HistoryConsultationContract;
import com.wd.my.persenter.HistoryConsultationPeresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryConsultationActivity extends BaseAcitvity implements HistoryConsultationContract.IView {

    private ImageView ivHistoryBack;
    private LinearLayout llyHistoryNomessage;

    @Override
    protected BasePresenter initPresenter() {
        return new HistoryConsultationPeresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_history_consultation;
    }

    @Override
    protected void initView() {
        ivHistoryBack = findViewById(R.id.iv_history_back);
        llyHistoryNomessage = findViewById(R.id.lly_history_nomessage);

        BasePresenter persener = getPresenter();
        if (persener != null && persener instanceof HistoryConsultationPeresenter) {
            ((HistoryConsultationPeresenter) persener).getHistoryConsultation(1, 10);
        }
    }

    @Override
    protected void initData() {
        ivHistoryBack.setOnClickListener(new View.OnClickListener() {
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
