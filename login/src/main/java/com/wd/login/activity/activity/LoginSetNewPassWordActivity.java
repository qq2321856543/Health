package com.wd.login.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.common.base.util.util.RsaCoder;
import com.wd.common.base.util.util.SPUtils;
import com.wd.login.R;
import com.wd.login.R2;
import com.wd.login.activity.bean.LoginLoginBean;
import com.wd.login.activity.bean.LoginRegisterBean;
import com.wd.login.activity.bean.LoginResetPwdBean;
import com.wd.login.activity.bean.LoginSendEmailCodeBean;
import com.wd.login.activity.bean.LogincheckCodeBean;
import com.wd.login.activity.contract.ILoginContract;
import com.wd.login.activity.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginSetNewPassWordActivity extends BaseAcitvity implements ILoginContract.IView {


    @BindView(R2.id.iv_set_back)
    ImageView ivSetBack;
    @BindView(R2.id.et_set_email)
    EditText etSetEmail;
    @BindView(R2.id.iv_set_show_pwd)
    ImageView ivSetShowPwd;
    @BindView(R2.id.et_set_verification)
    EditText etSetVerification;
    @BindView(R2.id.iv_set_show_pwd2)
    ImageView ivSetShowPwd2;
    @BindView(R2.id.bt_set_new_pwd)
    Button btSetNewPwd;

    @Override
    protected BasePresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login_set_new_pass_word;
    }

    @Override
    protected void initView() {
        ivSetBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        btSetNewPwd.setOnClickListener(new View.OnClickListener() {

            private String mpwd1;
            private String mpwd2;

            @Override
            public void onClick(View v) {
                if(RetrofitUtil.getInstance().isWifi(LoginSetNewPassWordActivity.this)){
                    String email = SPUtils.getString(LoginSetNewPassWordActivity.this, "email", "email");
                    String pwd1 = etSetEmail.getText().toString();
                    try {
                        mpwd1 = RsaCoder.encryptByPublicKey(pwd1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String pwd2 = etSetVerification.getText().toString();
                    try {
                        mpwd2 = RsaCoder.encryptByPublicKey(pwd2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(!TextUtils.isEmpty(pwd1)&&!TextUtils.isEmpty(pwd2)&&pwd1.equals(pwd2)){
                        BasePresenter presenter = getPresenter();
                        if(presenter instanceof ILoginContract.IPresenter){
                            ((ILoginContract.IPresenter)presenter).getResetPwd(email,mpwd1,mpwd1);
                        }
                    }else {
                        Toast.makeText(LoginSetNewPassWordActivity.this, "输入有误", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginSetNewPassWordActivity.this, "当前无网络", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onResetPwd(LoginResetPwdBean loginResetPwdBean) {
        String message = loginResetPwdBean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        if(message.equals("密码重置成功")){
            Intent intent = new Intent(LoginSetNewPassWordActivity.this, LoginLonginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onSendEmailCode(LoginSendEmailCodeBean loginSendEmailCodeBean) {

    }

    @Override
    public void onRegister(LoginRegisterBean loginRegisterBean) {

    }

    @Override
    public void onLogin(LoginLoginBean loginLoginBean) {

    }

    @Override
    public void onCheckCode(LogincheckCodeBean logincheckCodeBean) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
