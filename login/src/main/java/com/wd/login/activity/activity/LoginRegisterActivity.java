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
import com.wd.login.R;
import com.wd.login.R2;
import com.wd.login.activity.bean.LoginLoginBean;
import com.wd.login.activity.bean.LoginRegisterBean;
import com.wd.login.activity.bean.LoginResetPwdBean;
import com.wd.login.activity.bean.LoginSendEmailCodeBean;
import com.wd.login.activity.bean.LogincheckCodeBean;
import com.wd.login.activity.contract.ILoginContract;
import com.wd.login.activity.presenter.LoginPresenter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.regex.Pattern.compile;

public class LoginRegisterActivity extends BaseAcitvity implements ILoginContract.IView {


    @BindView(R2.id.et_register_email)
    EditText etRegisterEmail;
    @BindView(R2.id.bt_register_verification)
    Button btRegisterVerification;
    @BindView(R2.id.et_register_verification)
    EditText etRegisterVerification;
    @BindView(R2.id.et_register_pwd)
    EditText etRegisterPwd;
    @BindView(R2.id.iv_register_show_pwd_1)
    ImageView ivRegisterShowPwd1;
    @BindView(R2.id.et_register_pwd_again)
    EditText etRegisterPwdAgain;
    @BindView(R2.id.iv_register_show_pwd_2)
    ImageView ivRegisterShowPwd2;
    @BindView(R2.id.et_register_invitatiion)
    EditText etRegisterInvitatiion;
    @BindView(R2.id.bt_register)
    Button btRegister;

    @Override
    protected BasePresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login_register;
    }

    @Override
    protected void initView() {

    }
    public static boolean matachPhone(String str){
        Pattern compile = compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher matcher = compile.matcher(str);
        return matcher.matches();
    }
    @Override
    protected void initData() {
        btRegisterVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasePresenter presenter = getPresenter();
                if(presenter instanceof ILoginContract.IPresenter){
                    String email = etRegisterEmail.getText().toString();
                    ((ILoginContract.IPresenter)presenter).getSendEmailCode(email);
                }
            }
        });
        btRegister.setOnClickListener(new View.OnClickListener() {

            private String pwd;

            @Override
            public void onClick(View v) {
                Boolean net = RetrofitUtil.getInstance().isWifi(LoginRegisterActivity.this);
                if(net){

                    String email = etRegisterEmail.getText().toString();
                    String pwdOne = etRegisterPwd.getText().toString();
                    String pwdTwo = etRegisterPwdAgain.getText().toString();
                    try {
                        pwd = RsaCoder.encryptByPublicKey(pwdOne);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //邀请码
                    String invitatiionCode = etRegisterInvitatiion.getText().toString();
                    //验证码
                    String verificationCode = etRegisterVerification.getText().toString();
                    if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(pwdOne)&&!TextUtils.isEmpty(pwdTwo)&&!TextUtils.isEmpty(verificationCode)){
                        if(matachPhone(email)){
                            BasePresenter presenter = getPresenter();
                            if(presenter instanceof ILoginContract.IPresenter){
                                ((ILoginContract.IPresenter)presenter).getRegister(email,verificationCode,pwd,pwd,invitatiionCode);
                            }
                        }else {
                            Toast.makeText(LoginRegisterActivity.this, "邮箱输入有误", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(LoginRegisterActivity.this, "请将信息填写完整", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginRegisterActivity.this, "当前状态无网络", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onSendEmailCode(LoginSendEmailCodeBean loginSendEmailCodeBean) {
        String message = loginSendEmailCodeBean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegister(LoginRegisterBean loginRegisterBean) {
        String message = loginRegisterBean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        if(message.equals("注册成功")){
            Intent intent = new Intent(LoginRegisterActivity.this, LoginLonginActivity.class);
            startActivity(intent);
            finish();
        }else {

        }
    }

    @Override
    public void onLogin(LoginLoginBean loginLoginBean) {

    }

    @Override
    public void onCheckCode(LogincheckCodeBean logincheckCodeBean) {

    }

    @Override
    public void onResetPwd(LoginResetPwdBean loginResetPwdBean) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
