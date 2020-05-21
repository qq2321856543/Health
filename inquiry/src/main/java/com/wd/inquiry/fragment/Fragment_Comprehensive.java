package com.wd.inquiry.fragment;

import android.view.View;

import com.wd.common.base.util.Base.BaseFragment;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.inquiry.R;
import com.wd.inquiry.bean.DoctorListBean;
import com.wd.inquiry.icoolor.ICoolor_DoctorList;

public class Fragment_Comprehensive extends BaseFragment implements ICoolor_DoctorList.IView {
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_comprehensive;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void getDoctorListSuccess(DoctorListBean doctorListBean) {

    }
}
