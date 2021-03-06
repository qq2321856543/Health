package com.wd.login.activity.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.util.MD;
import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.common.base.util.util.RsaCoder;
import com.wd.common.base.util.util.SPUtils;
import com.wd.login.R;
import com.wd.login.R2;
import com.wd.login.activity.bean.LoginLoginBean;
import com.wd.login.activity.bean.LoginRegisterBean;
import com.wd.login.activity.bean.LoginResetPwdBean;
import com.wd.login.activity.bean.LoginSendEmailCodeBean;
import com.wd.login.activity.bean.LoginWxBean;
import com.wd.login.activity.bean.LogincheckCodeBean;
import com.wd.login.activity.contract.ILoginContract;
import com.wd.login.activity.presenter.LoginPresenter;
import com.wd.login.activity.util.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

import static java.util.regex.Pattern.compile;

//import com.alibaba.android.arouter.launcher.ARouter;

public class LoginLonginActivity extends BaseAcitvity implements ILoginContract.IView {

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
    private String s;

    @Override
    protected BasePresenter initPresenter() {
        return new LoginPresenter(this);

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
                Intent intent = new Intent(LoginLonginActivity.this, LoginForgetPassWordActivity.class);
                startActivity(intent);
            }
        });
        tvLoginToregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginLonginActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
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
        btLogin.setOnClickListener(new View.OnClickListener() {

            private String mpwd;

            @Override
            public void onClick(View v) {
                Boolean net = RetrofitUtil.getInstance().isWifi(LoginLonginActivity.this);
                if(net){
                    String email = etLoginEmail.getText().toString();
                    String pwd = etLoginPwd.getText().toString();
                    try {
                        mpwd = RsaCoder.encryptByPublicKey(pwd);
                        Log.i("MMM",mpwd+"");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(mpwd)){
                        if(matachPhone(email)){
                            BasePresenter presenter = getPresenter();
                            if(presenter instanceof ILoginContract.IPresenter){
                                ((ILoginContract.IPresenter)presenter).getLogin(email,mpwd);
                            }
                        }else {
                            Toast.makeText(LoginLonginActivity.this, "请输入正确的邮箱格式", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(LoginLonginActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginLonginActivity.this, "当前无网络", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ivLoginWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.wx_api = WXAPIFactory.createWXAPI(getApplicationContext(), Constants.APP_ID, true);
                Constants.wx_api.registerApp(Constants.APP_ID);
                final SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                Constants.wx_api.sendReq(req);
            }
        });
    }
//    //微信登录
//    @OnClick(R2.id.iv_login_wechat)
//    public void onClick(){
//        Constants.wx_api = WXAPIFactory.createWXAPI(getApplicationContext(), Constants.APP_ID, true);
//        Constants.wx_api.registerApp(Constants.APP_ID);
//        final SendAuth.Req req = new SendAuth.Req();
//        req.scope = "snsapi_userinfo";
//        req.state = "wechat_sdk_demo_test";
//        Constants.wx_api.sendReq(req);
//
//    }
    @Override
    public void onLogin(LoginLoginBean loginLoginBean) {
        String message = loginLoginBean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();

        if(message.equals("登录成功")){
            LoginLoginBean.ResultBean result = loginLoginBean.getResult();
            int id = result.getId();
            String sessionId = result.getSessionId();
            String headPic = result.getHeadPic();
            Log.i("nnn",""+sessionId);
            String name = result.getNickName();
            SPUtils.putString(this, SPUtils.USERINFO_NAME,SPUtils.USERINFO_KEY_USER_ID,id+"");
            SPUtils.putString(this, SPUtils.USERINFO_NAME,SPUtils.USERINFO_KEY_SESSION_ID,sessionId+"");
            SPUtils.putString(this,SPUtils.USERINFO_NAME,"head",headPic);
            SPUtils.putString(this,SPUtils.USERINFO_NAME,"name",name);

//            new Intent(LoginLonginActivity.this,)
            //极光IM密码公钥解密
            try {
                s = RsaCoder.decryptByPublicKey(result.getJiGuangPwd());
            } catch (Exception e) {
                e.printStackTrace();
             }
            Log.i("xxx","极光IM帐号，帐号："+result.getUserName()+";密码："+result.getJiGuangPwd()+";解密后的密码:"+s+";MD5加密后的密码:"+MD.MD5(s));
            //极光IM密码MD5加密 并登录
            JMessageClient.login(result.getUserName(), MD.MD5(s), new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {
                    if (i==0){
                        Log.i("xxx","极光IM登录成功");
                        Toast.makeText(LoginLonginActivity.this, "极光IM登录成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Log.i("xxx","极光IM登录失败："+i+"///"+s);
                        Toast.makeText(LoginLonginActivity.this, "极光IM登录失败", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            //跳转HomeFragmentActivity
            ARouter.getInstance().build("/home/HomeFragmentActivity").navigation();
        }
    }

    @Override
    public void onCheckCode(LogincheckCodeBean logincheckCodeBean) {

    }

    @Override
    public void onResetPwd(LoginResetPwdBean loginResetPwdBean) {

    }

    @Override
    public void onWxLogin(LoginWxBean loginWxBean) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onSendEmailCode(LoginSendEmailCodeBean loginSendEmailCodeBean) {

    }

    @Override
    public void onRegister(LoginRegisterBean loginRegisterBean) {

    }


}
