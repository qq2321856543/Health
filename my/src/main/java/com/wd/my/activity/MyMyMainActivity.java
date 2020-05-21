package com.wd.my.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.util.HttpUtil;
import com.wd.my.R;
import com.wd.my.R2;

import java.util.ArrayList;
import java.util.HashSet;

import butterknife.BindView;

public class MyMyMainActivity extends BaseAcitvity {

    @BindView(R2.id.iv_touxiang)
    ImageView iv_touxiang;
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_my_main;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        iv_touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyMyMainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
