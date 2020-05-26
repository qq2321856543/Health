package com.wd.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.fragment.HomeDiseasesFragment;
import com.wd.home.fragment.HomeDrugFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeKnowledgeActivity extends BaseAcitvity {


    @BindView(R2.id.iv_knowledge_head)
    SimpleDraweeView ivKnowledgeHead;
    @BindView(R2.id.iv_knowledge_message)
    ImageView ivKnowledgeMessage;
    @BindView(R2.id.tb_knowledge)
    TabLayout tb;
    @BindView(R2.id.vp_knowledge)
    ViewPager vp;
    ArrayList<Fragment> list = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home_knowledge;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        HomeDiseasesFragment diseasesFragment = new HomeDiseasesFragment();
        HomeDrugFragment drugFragment = new HomeDrugFragment();
       list.add(diseasesFragment);
       list.add(drugFragment);
       data.add("常见病症");
       data.add("常见药品");
       tb.addTab(tb.newTab().setText(data.get(0)));
       tb.addTab(tb.newTab().setText(data.get(1)));
        MyViewPage page = new MyViewPage(getSupportFragmentManager());
        Intent intent = getIntent();
        int id = intent.getIntExtra("page", 0);
        vp.setCurrentItem(id);
        vp.setAdapter(page);
        tb.setupWithViewPager(vp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    public class MyViewPage extends FragmentPagerAdapter {
        public MyViewPage(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return data.get(position);

        }
    }
}
