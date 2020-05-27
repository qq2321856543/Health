package com.wd.inquiry.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.inquiry.R;

public class DoctorInfoActivity extends BaseAcitvity {


    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_doctor_info;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //医生id
        int id = getIntent().getIntExtra("id", 0);

    }
}
