package com.wd.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.home.R;
import com.wd.home.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeCarouselActivity extends BaseAcitvity {


    @BindView(R2.id.wv)
    WebView wv;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_carousel_details;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String jumpUrl = intent.getStringExtra("jumpUrl");
        WebSettings settings = wv.getSettings();
        settings.setJavaScriptEnabled(true);
        //加载网页
        wv.loadUrl(jumpUrl);
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
