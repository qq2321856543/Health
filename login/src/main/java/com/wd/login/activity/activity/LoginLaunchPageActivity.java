package com.wd.login.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.login.R;
import com.wd.login.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginLaunchPageActivity extends BaseAcitvity {

    @BindView(R2.id.iv_launch)
    ImageView ivLaunch;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login_launch_page;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.iv_launch)
    public void onViewClicked() {
        Intent intent = new Intent(LoginLaunchPageActivity.this, GuidePageActivity.class);
        startActivity(intent);
        finish();
    }
}
