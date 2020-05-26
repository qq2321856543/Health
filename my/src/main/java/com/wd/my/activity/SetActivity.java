package com.wd.my.activity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.my.R;
import com.wd.my.R2;
import com.wd.my.bean.UserInfoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;


public class SetActivity extends BaseAcitvity {
    @BindView(R2.id.iv_set_back)
    ImageView        ivSetBack;
    @BindView(R2.id.iv_set_headicon)
    SimpleDraweeView ivSetHeadicon;
    @BindView(R2.id.tv_set_username)
    TextView         tvSetUsername;
    @BindView(R2.id.iv_set_userinfo)
    ImageView        ivSetUserinfo;
    @BindView(R2.id.iv_set_changepwd)
    ImageView        ivSetChangepwd;
    @BindView(R2.id.iv_set_cleanchache)
    ImageView        ivSetCleanchache;
    @BindView(R2.id.iv_set_light)
    ImageView        ivSetLight;
    @BindView(R2.id.iv_set_checkversion)
    ImageView        ivSetCheckversion;
    @BindView(R2.id.iv_set_helpcenter)
    ImageView        ivSetHelpcenter;
    @BindView(R2.id.iv_set_aboutus)
    ImageView        ivSetAboutus;
    @BindView(R2.id.iv_set_invitefriends)
    ImageView        ivSetInvitefriends;
    @BindView(R2.id.tv_set_quitlogin)
    TextView         tvSetQuitlogin;
    private UserInfoBean.ResultBean mResult;

    @Override
    protected int getLayout() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return R.layout.activity_set;
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
//        if (mResult.getHeadPic()==null){
//
//        }else {
//
//            ivSetHeadicon.setImageURI(Uri.parse(mResult.getHeadPic()));
//        }
//        if (mResult.getNickName()==null){
//            tvSetUsername.setText("哈哈");
//
//        }else {
//            tvSetUsername.setText(mResult.getNickName());
//        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getUserInfoBean(final UserInfoBean userInfoBean) {
        mResult = userInfoBean.getResult();
    }

    @OnClick({R2.id.iv_set_back, R2.id.iv_set_userinfo, R2.id.iv_set_changepwd, R2.id.iv_set_cleanchache
            , R2.id.iv_set_light, R2.id.iv_set_checkversion, R2.id.iv_set_helpcenter, R2.id.iv_set_aboutus
            , R2.id.iv_set_invitefriends, R2.id.tv_set_quitlogin})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        if (view.getId() == R.id.iv_set_back) {
            finish();
        } else if (view.getId() == R.id.iv_set_userinfo) {
            intent.setClass(this, UserInfoActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.iv_set_changepwd) {

        } else if (view.getId() == R.id.iv_set_cleanchache) {

        } else if (view.getId() == R.id.iv_set_light) {

        } else if (view.getId() == R.id.iv_set_checkversion) {

        } else if (view.getId() == R.id.iv_set_helpcenter) {

        } else if (view.getId() == R.id.iv_set_aboutus) {

        } else if (view.getId() == R.id.iv_set_invitefriends) {

        } else if (view.getId() == R.id.tv_set_quitlogin) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
