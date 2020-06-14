package com.wd.inquiry.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.inquiry.R;
import com.wd.inquiry.R2;
import com.wd.inquiry.adapter.FindDepartmentAdapter;
import com.wd.inquiry.bean.FindDepartmentBean;
import com.wd.inquiry.fragment.Fragment_Comprehensive;
import com.wd.inquiry.fragment.Fragment_Praise;
import com.wd.inquiry.fragment.Fragment_Price;
import com.wd.inquiry.fragment.Fragment_Quantity;
import com.wd.inquiry.icoolor.ICoolor_FindDepartment;
import com.wd.inquiry.presenter.Presenter_FindDepartment;
import com.wd.patient.fragment.PatientHomePageFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jpush.im.android.api.JMessageClient;

public class InquiryMainActivity extends BaseAcitvity implements ICoolor_FindDepartment.IView {

    @BindView(R2.id.sdv)
    SimpleDraweeView sdv;
    @BindView(R2.id.iv_ling)
    ImageView iv_ling;
    @BindView(R2.id.rv)
    RecyclerView rv;
    @BindView(R2.id.tab)
    TabLayout tab;
    @BindView(R2.id.vp)
    ViewPager vp;
    ArrayList<Fragment> list = new ArrayList<>();
    ArrayList<String> tabs = new ArrayList<>();

    @Override
    protected BasePresenter initPresenter() {
        return new Presenter_FindDepartment(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_inquiry_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter != null) {
            ((ICoolor_FindDepartment.IPresenter)presenter).getFindDepartmentSuccess();
        }
        list.add(new Fragment_Comprehensive());
        list.add(new Fragment_Praise());
        list.add(new Fragment_Quantity());
        list.add(new Fragment_Price());
        tabs.add("综合");
        tabs.add("好评");
        tabs.add("咨询数");
        tabs.add("价格 ▼");
        FragmentPageAdap fragmentPageAdap = new FragmentPageAdap(getSupportFragmentManager());
        vp.setAdapter(fragmentPageAdap);
        tab.setupWithViewPager(vp);
    }

    @Override
    public void getFindDepartmentSuccess(FindDepartmentBean findDepartmentBean) {
        List<FindDepartmentBean.ResultBean> result = findDepartmentBean.getResult();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        rv.setLayoutManager(layoutManager);
        FindDepartmentAdapter findDepartmentAdapter = new FindDepartmentAdapter(this, result);
        rv.setAdapter(findDepartmentAdapter);
        findDepartmentAdapter.setOnclick(new FindDepartmentAdapter.Onclick() {
            @Override
            public void click(int id,int postion) {
                for (FindDepartmentBean.ResultBean resultBean:result){
                    resultBean.setIs(false);
                }
                result.get(postion).setIs(true);
                findDepartmentAdapter.notifyDataSetChanged();
            }
        });
    }
    public class FragmentPageAdap extends FragmentPagerAdapter{

        public FragmentPageAdap(FragmentManager fm) {
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
            return tabs.get(position);
        }
    }
}
