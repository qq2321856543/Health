package com.wd.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.fragment.HomeHomeFragment;
import com.wd.home.fragment.TestFragment;
import com.wd.home.fragment.TestFragment2;
import com.wd.patient.fragment.PatientHomePageFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
@Route(path = "/home/HomeFragmentActivity")
public class HomeFragmentActivity extends BaseAcitvity {


    @BindView(R2.id.vp_home)
    ViewPager vpHome;
    @BindView(R2.id.tb_home)
    TabLayout tbHome;
    ArrayList<Fragment> list = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home_fragment;
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);
        data.add("首页");
        data.add("病友圈");
        data.add("小视频");
        HomeHomeFragment homeHomeFragment = new HomeHomeFragment();
        PatientHomePageFragment patientHomePageFragment = new PatientHomePageFragment();
        //TestFragment testFragment = new TestFragment();
        TestFragment2 fragment2 = new TestFragment2();
        list.add(homeHomeFragment);
        list.add(patientHomePageFragment);
        list.add(fragment2);
        tbHome.addTab(tbHome.newTab().setText(data.get(0)));
        tbHome.addTab(tbHome.newTab().setText(data.get(1)));
        tbHome.addTab(tbHome.newTab().setText(data.get(2)));
        MyViewPager pager = new MyViewPager(getSupportFragmentManager());
        vpHome.setAdapter(pager);
        tbHome.setupWithViewPager(vpHome);
    }

    @Override
    protected void initData() {

    }
    private class MyViewPager extends FragmentPagerAdapter{
        public MyViewPager(FragmentManager fm) {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
