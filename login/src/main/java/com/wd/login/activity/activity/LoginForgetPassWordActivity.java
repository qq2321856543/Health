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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.regex.Pattern.compile;

public class LoginForgetPassWordActivity extends BaseAcitvity implements ILoginContract.IView {


    @BindView(R2.id.iv_forget_back)
    ImageView ivForgetBack;
    @BindView(R2.id.et_forget_email)
    EditText etForgetEmail;
    @BindView(R2.id.bt_forget_verification)
    Button btForgetVerification;
    @BindView(R2.id.et_forget_verification)
    EditText etForgetVerification;
    @BindView(R2.id.bt_next)
    Button btNext;

    @Override
    protected BasePresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login_forget_pass_word;
    }

    @Override
    protected void initView() {
        ivForgetBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public static boolean matachPhone(String str){
        Pattern compile = compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher matcher = compile.matcher(str);
        return matcher.matches();
    }
    @Override
    protected void initData() {
        btForgetVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etForgetEmail.getText().toString();
                if (!TextUtils.isEmpty(email)) {
                    if(matachPhone(email)){
                        BasePresenter presenter = getPresenter();
                        if(presenter instanceof ILoginContract.IPresenter){
                            ((ILoginContract.IPresenter)presenter).getSendEmailCode(email);
                        }
                    }else {
                        Toast.makeText(LoginForgetPassWordActivity.this, "邮箱格式有误", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginForgetPassWordActivity.this, "请将信息填写完整", Toast.LENGTH_SHORT).show();
                }
            }
        });
            btNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = etForgetEmail.getText().toString();
                    SPUtils.putString(LoginForgetPassWordActivity.this,"email","email",email);
                    String code = etForgetVerification.getText().toString();
                    if (!TextUtils.isEmpty(code)&&!TextUtils.isEmpty(email)) {
                        if(matachPhone(email)){
                            BasePresenter presenter = getPresenter();
                            if(presenter instanceof ILoginContract.IPresenter){
                                ((ILoginContract.IPresenter)presenter).getCheckCode(email,code);
                            }
                        }else {
                            Toast.makeText(LoginForgetPassWordActivity.this, "邮箱格式有误", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(LoginForgetPassWordActivity.this, "请将信息填写完整", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    @Override
    public void onCheckCode(LogincheckCodeBean logincheckCodeBean) {
        String message = logincheckCodeBean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        if(message.equals("验证通过")){
            Intent intent = new Intent(LoginForgetPassWordActivity.this, LoginSetNewPassWordActivity.class);
            startActivity(intent);

        }
    }

    @Override
    public void onSendEmailCode(LoginSendEmailCodeBean loginSendEmailCodeBean) {
        String message = loginSendEmailCodeBean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRegister(LoginRegisterBean loginRegisterBean) {

    }

    @Override
    public void onLogin(LoginLoginBean loginLoginBean) {

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
