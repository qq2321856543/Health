package com.wd.login.activity.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.login.R;
import com.wd.login.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import com.alibaba.android.arouter.launcher.ARouter;

public class LoginLonginActivity extends BaseAcitvity {

    @BindView(R2.id.iv_logo)
    ImageView ivLogo;
    @BindView(R2.id.iv_mail)
    ImageView ivMail;
    @BindView(R2.id.et_login_email)
    EditText etLoginEmail;
    @BindView(R2.id.rl1)
    RelativeLayout rl1;
    @BindView(R2.id.iv_lock1)
    ImageView ivLock1;
    @BindView(R2.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R2.id.iv_login_show_pwd)
    ImageView ivLoginShowPwd;
    @BindView(R2.id.rl2)
    RelativeLayout rl2;
    @BindView(R2.id.bt_login)
    Button btLogin;
    @BindView(R2.id.rl)
    RelativeLayout rl;
    @BindView(R2.id.tv_login_forget_pwd)
    TextView tvLoginForgetPwd;
    @BindView(R2.id.tv_login_toregister)
    TextView tvLoginToregister;
    @BindView(R2.id.tv_orther)
    TextView tvOrther;
    @BindView(R2.id.iv_login_wechat)
    ImageView ivLoginWechat;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login_longin;
    }

    @Override
    protected void initView() {
//        btLogin.setOnClickListener(this);
//        ivLoginShowPwd.setOnClickListener(this);
//        ivLoginWechat.setOnClickListener(this);
        tvLoginForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginLonginActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
            }
        });
        tvLoginToregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginLonginActivity.this, LoginForgetPassWordActivity.class);
                startActivity(intent);
            }
        });
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


}
