package com.wd.my.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.util.HttpUtil;
import com.wd.my.R;
import com.wd.my.R2;
import com.wd.my.bean.SignInBean;
import com.wd.my.bean.UserInfoBean;
import com.wd.my.contract.GetUserInfoContract;
import com.wd.my.persenter.GetUserInfoPersenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashSet;

import butterknife.BindView;
import butterknife.OnClick;
@Route(path = "/my/MyMyMainActivity")
public class MyMyMainActivity extends BaseAcitvity implements GetUserInfoContract.IView {
    @BindView(R2.id.iv_mine_back)
    ImageView        ivMineBack;
    @BindView(R2.id.iv_mine_notice)
    ImageView        ivMineNotice;
    @BindView(R2.id.iv_mine_headicon)
    SimpleDraweeView ivMineHeadicon;
    @BindView(R2.id.tv_mine_username)
    TextView tvMineUsername;
    @BindView(R2.id.bt_mine_qiandao)
    Button btMineQiandao;
    @BindView(R2.id.iv_mine_next_ing)
    ImageView        ivMineNextIng;
    @BindView(R2.id.iv_mine_next_hestory)
    ImageView        ivMineNextHestory;
    @BindView(R2.id.layout_mine_alpha)
    LinearLayout layoutMineAlpha;
    @BindView(R2.id.iv_mine_myinfo)
    ImageView        ivMineMyinfo;
    @BindView(R2.id.iv_mine_mywallet)
    ImageView        ivMineMywallet;
    @BindView(R2.id.iv_mine_mycollection)
    ImageView    ivMineMycollection;
    @BindView(R2.id.iv_mine_myadvice)
    ImageView    ivMineMyadvice;
    @BindView(R2.id.iv_mine_myvideo)
    ImageView    ivMineMyvideo;
    @BindView(R2.id.iv_mine_mycircle)
    ImageView    ivMineMycircle;
    @BindView(R2.id.iv_mine_myattention)
    ImageView    ivMineMyattention;
    @BindView(R2.id.iv_mine_mytask)
    ImageView    ivMineMytask;
    @BindView(R2.id.iv_mine_myset)
    ImageView    ivMineMyset;
    @Override
    protected BasePresenter initPresenter() {
        return new GetUserInfoPersenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_my_main;
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        //设置透明度
        layoutMineAlpha.getBackground().setAlpha(90);
        //请求
        BasePresenter persener = getPresenter();
        if (persener != null && persener instanceof GetUserInfoPersenter) {
            ((GetUserInfoPersenter)persener).getUserInfo();
            ((GetUserInfoPersenter)persener).getSignInDay();
        }
    }

    @Override
    protected void initData() {

    }
    @OnClick({R2.id.iv_mine_back, R2.id.iv_mine_notice, R2.id.bt_mine_qiandao, R2.id.iv_mine_next_ing
            , R2.id.iv_mine_next_hestory, R2.id.iv_mine_myinfo, R2.id.iv_mine_mywallet, R2.id.iv_mine_mycollection
            , R2.id.iv_mine_myadvice, R2.id.iv_mine_myvideo, R2.id.iv_mine_mycircle, R2.id.iv_mine_myattention,
            R2.id.iv_mine_mytask, R2.id.iv_mine_myset})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.iv_mine_back) {
            finish();
        }else if(view.getId() == R.id.iv_mine_notice) {

        }else if(view.getId() == R.id.iv_mine_notice) {

        }else if(view.getId() == R.id.bt_mine_qiandao) {
            BasePresenter persener = getPresenter();
            if (persener != null && persener instanceof GetUserInfoPersenter) {
                ((GetUserInfoPersenter)persener).signIn();
            }
        }else if(view.getId() == R.id.iv_mine_next_ing) {
            intent.setClass(this, CurrentConsultationActivity.class);
            startActivity(intent);
        }else if(view.getId() == R.id.iv_mine_next_hestory) {
            intent.setClass(this, HistoryConsultationActivity.class);
            startActivity(intent);
        }else if(view.getId() == R.id.iv_mine_myinfo) {

        }else if(view.getId() == R.id.iv_mine_mywallet) {

        }else if(view.getId() == R.id.iv_mine_mycollection) {

        }else if(view.getId() == R.id.iv_mine_myadvice) {

        }else if(view.getId() == R.id.iv_mine_myvideo) {

        }else if(view.getId() == R.id.iv_mine_mycircle) {

        }else if(view.getId() == R.id.iv_mine_myattention) {

        }else if(view.getId() == R.id.iv_mine_mytask) {

        }else if(view.getId() == R.id.iv_mine_myset) {
            intent.setClass(this, SetActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void getUserInfoSuccess(UserInfoBean userInfoBean) {
        Toast.makeText(this, userInfoBean.getMessage(), Toast.LENGTH_SHORT).show();
        EventBus.getDefault().postSticky(userInfoBean);
        //
        UserInfoBean.ResultBean result = userInfoBean.getResult();
        String headPic = result.getHeadPic();
        ivMineHeadicon.setImageURI(Uri.parse(headPic));
        //
        String nickName = result.getNickName();
        tvMineUsername.setText(nickName);
    }

    @Override
    public void getUserInfoFailed(String msg) {

    }

    @Override
    public void getSignInSuccess(SignInBean signInBean) {
        int result = signInBean.getResult();
        if(result==1){
            btMineQiandao.setText("已签到");
        }
    }

    @Override
    public void getSignInFailed(String msg) {

    }

    @Override
    public void signInSuccess(SignInBean signInBean) {
        Toast.makeText(this, signInBean.getMessage(), Toast.LENGTH_SHORT).show();
        BasePresenter persener = getPresenter();
        if (persener != null && persener instanceof GetUserInfoPersenter) {
            ((GetUserInfoPersenter)persener).getSignInDay();
        }
    }

    @Override
    public void signInFailed(String msg) {

    }
}
