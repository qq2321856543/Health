package com.wd.my.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.my.R;
import com.wd.my.R2;
import com.wd.my.bean.UpLoadBean;
import com.wd.my.bean.UserInfoBean;
import com.wd.my.util.CreatApis;
import com.wildma.pictureselector.PictureSelector;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;


public class UserInfoActivity extends BaseAcitvity {
    private static final String TAG = "UserInfoActivity";
    @BindView(R2.id.iv_userinfo_back)
    ImageView        ivUserinfoBack;
    @BindView(R2.id.iv_userinfo_headicon)
    SimpleDraweeView ivUserinfoHeadicon;
    @BindView(R2.id.tv_userinfo_username)
    TextView         tvUserinfoUsername;
    @BindView(R2.id.iv_userinfo_change_username)
    ImageView        ivUserinfoChangeUsername;
    @BindView(R2.id.iv_userinfo_sex)
    ImageView        ivUserinfoSex;
    @BindView(R2.id.iv_userinfo_change_sex)
    ImageView        ivUserinfoChangeSex;
    @BindView(R2.id.tv_userinfo_hight)
    TextView         tvUserinfoHight;
    @BindView(R2.id.tv_userinfo_weight)
    TextView         tvUserinfoWeight;
    @BindView(R2.id.tv_userinfo_years)
    TextView         tvUserinfoYears;
    @BindView(R2.id.iv_userinfo_email)
    TextView         ivUserinfoEmail;
    @BindView(R2.id.iv_userinfo_bindwx)
    ImageView        ivUserinfoBindwx;
    @BindView(R2.id.iv_userinfo_certification)
    ImageView        ivUserinfoCertification;
    @BindView(R2.id.iv_userinfo_bindyhk)
    ImageView        ivUserinfoBindyhk;
    @BindView(R2.id.iv_userinfo_tizheng)
    ImageView        ivUserinfoTizheng;
    @BindView(R2.id.tv_userinfo_bindwx)
    TextView         tvUserinfoBindwx;
    @BindView(R2.id.tv_userinfo_renzheng)
    TextView         tvUserinfoRenzheng;
    @BindView(R2.id.tv_userinfo_bindyhk)
    TextView         tvUserinfoBindyhk;
    private UserInfoBean.ResultBean mResult;


    @Override
    protected int getLayout() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return R.layout.activity_user_info;
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
        //头像
        String headPic = mResult.getHeadPic();
        ivUserinfoHeadicon.setImageURI(Uri.parse(headPic));
        //名称
        String nickName = mResult.getNickName();
        tvUserinfoUsername.setText(nickName);
        //邮箱
        String email = mResult.getEmail();
        ivUserinfoEmail.setText(email);
        //性别
        int sex = mResult.getSex();
        if (sex == 2) {
            ivUserinfoSex.setImageResource(R.mipmap.common_icon_girl_n);
        }
        //年龄
        int age = mResult.getAge();
        tvUserinfoYears.setText(String.valueOf(age));
        //身高
        int height = mResult.getHeight();
        tvUserinfoHight.setText(String.valueOf(height));
        //体重
        int weight = mResult.getWeight();
        tvUserinfoWeight.setText(String.valueOf(weight));
        //实名认证
        //绑定微信
        int whetherBingWeChat = mResult.getWhetherBingWeChat();
        if (whetherBingWeChat == 1) {
            tvUserinfoBindwx.setText("已绑定");
        }
        //绑定银行卡
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getUserInfoBean(UserInfoBean userInfoBean) {
        Log.d(TAG, "getUserInfoBean: "+userInfoBean.getMessage());
        mResult = userInfoBean.getResult();
    }

    @OnClick({R2.id.iv_userinfo_back, R2.id.iv_userinfo_headicon, R2.id.iv_userinfo_change_username
            , R2.id.iv_userinfo_change_sex, R2.id.iv_userinfo_tizheng, R2.id.iv_userinfo_bindwx
            , R2.id.iv_userinfo_certification, R2.id.iv_userinfo_bindyhk})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.iv_userinfo_back) {
            finish();
        } else if (view.getId() == R.id.iv_userinfo_headicon) {
            PictureSelector
                    .create(this, PictureSelector.SELECT_REQUEST_CODE)
                    .selectPicture(true, 200, 200, 1, 1);
        } else if (view.getId() == R.id.iv_userinfo_change_username) {

        } else if (view.getId() == R.id.iv_userinfo_change_sex) {

        } else if (view.getId() == R.id.iv_userinfo_tizheng) {

        } else if (view.getId() == R.id.iv_userinfo_bindwx) {

        } else if (view.getId() == R.id.iv_userinfo_certification) {

        } else if (view.getId() == R.id.iv_userinfo_bindyhk) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                String stringExtra = data.getStringExtra(PictureSelector.PICTURE_PATH);
                File file = new File(stringExtra);
                ArrayList<File> files = new ArrayList<>();
                files.add(file);
                HashMap<String, String> map = new HashMap<>();
                RequestBody requsetBody = RetrofitUtil.getInstance().getRequsetBody(files, map);
                CreatApis.creatClass().getUpLoadHeadPic(requsetBody)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<UpLoadBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(UpLoadBean upLoadBean) {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }

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