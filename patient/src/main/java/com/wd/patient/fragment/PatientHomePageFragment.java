package com.wd.patient.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wd.common.base.util.Base.BaseFragment;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.patient.R;
import com.wd.patient.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.fragment
 * @ClassName: PatientHomePageFragment
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/5/20 20:34
 */
public class PatientHomePageFragment extends BaseFragment {
    @BindView(R2.id.aaa)
    ImageView aaa;
    @BindView(R2.id.hongdian)
    ImageView hongdian;
    @BindView(R2.id.top_one)
    RelativeLayout topOne;
    @BindView(R2.id.aaa_two)
    ImageView aaaTwo;
    @BindView(R2.id.hongdian_two)
    ImageView hongdianTwo;
    @BindView(R2.id.top_two)
    RelativeLayout topTwo;
    @BindView(R2.id.re_one)
    RecyclerView reOne;
    @BindView(R2.id.re)
    RecyclerView re;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.patient_fragment_homepage;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }


}
