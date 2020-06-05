package com.wd.my.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.my.R;
import com.wd.my.R2;
import com.wd.my.bean.DeleteUserArchivesBean;
import com.wd.my.bean.FindUserArchivesBean;
import com.wd.my.bean.UpdateUserArchivesBean;
import com.wd.my.contract.ICoolor_OneUserArchives;
import com.wd.my.persenter.Presenter_OneUserArchives;

import java.io.File;
import java.net.URI;

import butterknife.BindView;

public class UserArchivesOneMainActivity extends BaseAcitvity implements ICoolor_OneUserArchives.IVew, View.OnClickListener {
    @BindView(R2.id.iv_back)
    ImageView iv_back;
    @BindView(R2.id.et_zheng)
    TextView et_zheng;
    @BindView(R2.id.et_xian)
    TextView et_xian;
    @BindView(R2.id.et_wang)
    TextView et_wang;
    @BindView(R2.id.et_zui)
    TextView et_zui;
    @BindView(R2.id.tv_shijian)
    TextView tv_shijian;
    @BindView(R2.id.tv_str)
    TextView tv_str;
    @BindView(R2.id.ll)
    LinearLayout ll;
    @BindView(R2.id.lly_history_nomessage)
    LinearLayout lly_history_nomessage;
    @BindView(R2.id.rl3)
    RelativeLayout rl3;

    @BindView(R2.id.bt_left)
    Button bt_left;
    @BindView(R2.id.bt_right)
    Button bt_right;
    @BindView(R2.id.bt_add)
    Button bt_add;
    private FindUserArchivesBean.ResultBean result;


    @Override
    protected BasePresenter initPresenter() {
        return new Presenter_OneUserArchives(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_user_archives_one_main;
    }

    @Override
    protected void initView() {
        BasePresenter presenter = getPresenter();
        if (presenter != null) {
            ((ICoolor_OneUserArchives.IPresenter)presenter).getFindUserArchives();
        }
    }

    @Override
    protected void initData() {
        bt_add.setOnClickListener(this);
        bt_left.setOnClickListener(this);
        bt_right.setOnClickListener(this);
    }

    @Override
    public void getFindUserArchives(FindUserArchivesBean findUserArchivesBean) {
        if (findUserArchivesBean.getMessage().equals("档案为空，快去添加吧！")){
            rl3.setVisibility(View.GONE);
            lly_history_nomessage.setVisibility(View.VISIBLE);
        }else {
            result = findUserArchivesBean.getResult();
            lly_history_nomessage.setVisibility(View.GONE);
            rl3.setVisibility(View.VISIBLE);
            et_zheng.setText(findUserArchivesBean.getResult().getDiseaseMain());
            et_xian.setText(findUserArchivesBean.getResult().getDiseaseNow());
            et_wang.setText(findUserArchivesBean.getResult().getDiseaseBefore());
            et_zui.setText(findUserArchivesBean.getResult().getTreatmentHospitalRecent());
            tv_shijian.setText("");
            tv_str.setText(findUserArchivesBean.getResult().getTreatmentProcess());
            if (findUserArchivesBean.getResult().getPicture()!=null){

            String picture = findUserArchivesBean.getResult().getPicture();
            String[] split = picture.split(",");
            for (int i = 0; i < split.length; i++) {
                ImageView imageView = new ImageView(UserArchivesOneMainActivity.this);
                Glide.with(UserArchivesOneMainActivity.this).load(split[i]).into(imageView);
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(5,0,5,7);
                ll.addView(imageView,layoutParams);
            }
            }

        }
    }

    @Override
    public void getUpdateUserArchives(UpdateUserArchivesBean updateUserArchivesBean) {

    }

    @Override
    public void getDeleteUserArchives(DeleteUserArchivesBean deleteUserArchivesBean) {
        Toast.makeText(this, ""+deleteUserArchivesBean.getMessage(), Toast.LENGTH_SHORT).show();
        rl3.setVisibility(View.GONE);
        lly_history_nomessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.bt_add){
            Intent intent = new Intent(UserArchivesOneMainActivity.this, UserArchivesActivity.class);
            startActivity(intent);
        }else if (id==R.id.bt_left){
            BasePresenter presenter = getPresenter();
            if (presenter != null) {
                ((ICoolor_OneUserArchives.IPresenter)presenter).getDeleteUserArchives(result.getId());
            }
        }else if (id==R.id.bt_right){
            Intent intent = new Intent(UserArchivesOneMainActivity.this, UserArchivesActivity.class);
            startActivity(intent);
        }
    }
}
