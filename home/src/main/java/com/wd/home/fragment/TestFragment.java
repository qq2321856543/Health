package com.wd.home.fragment;

import android.view.View;

import com.wd.common.base.util.Base.BaseFragment;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.home.R;

/**
 * Time: 2020/5/22
 * Author: 王冠华
 * Description:
 */
public class TestFragment extends BaseFragment {
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment;
    }

    @Override
    protected void initView(View view) {
    //getBackground().setAlpha(30);
    }

    @Override
    protected void initData() {

    }
}
