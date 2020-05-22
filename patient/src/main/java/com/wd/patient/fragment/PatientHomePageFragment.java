package com.wd.patient.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wd.common.base.util.Base.BaseFragment;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.patient.R;
import com.wd.patient.R2;
import com.wd.patient.adapter.ButtonAdapter;
import com.wd.patient.adapter.KeLieAdapter;
import com.wd.patient.bean.BingXiangBean;
import com.wd.patient.bean.BingYouQuanBean;
import com.wd.patient.bean.KeLieBean;
import com.wd.patient.contract.PatientContract;
import com.wd.patient.presenter.PatientPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.fragment
 * @ClassName: PatientHomePageFragment
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/5/20 20:34
 */
public class PatientHomePageFragment extends BaseFragment implements PatientContract.IView {
    @BindView(R2.id.aaa)
    ImageView aaa;
    @BindView(R2.id.hongdian)
    ImageView hongdian;
    @BindView(R2.id.top_one)
    RelativeLayout topOne;
    @BindView(R2.id.aaa_two)
    ImageView aaaTwo;
    @BindView(R2.id.hongdian_two)
    ImageView hongdianTwo;
    @BindView(R2.id.top_two)
    RelativeLayout topTwo;
    @BindView(R2.id.re_one)
    RecyclerView reOne;
    @BindView(R2.id.re)
    RecyclerView re;

    private int juli=0;
    private ButtonAdapter buttonAdapter;
    private KeLieAdapter keLieAdapter;

    @Override
    protected BasePresenter initPresenter() {
        return new PatientPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.patient_fragment_homepage;
    }

    @Override
    protected void initView(View view) {

        //注册EventBus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        BasePresenter presenter = getPresenter();
        if (presenter instanceof PatientPresenter) {
            ((PatientPresenter)presenter).getPatient(2,1,10);
            ((PatientPresenter) presenter).getKeLie();
        }
    }

    @Override
    protected void initData() {
        //默认第二个布局消失
        topOne.setVisibility(View.VISIBLE);
        topTwo.setVisibility(View.GONE);

        LinearLayoutManager two = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        re.setLayoutManager(two);
        buttonAdapter = new ButtonAdapter(getContext());
        re.setAdapter(buttonAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        reOne.setLayoutManager(linearLayoutManager);
        keLieAdapter = new KeLieAdapter(getContext());
        reOne.setAdapter(keLieAdapter);




        //添加滑动监听
        re.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("xxxxxx","竖着的距离"+dy);
                if (dy<0){
                    juli+=dy;
                    //上滑监听
                    pan();
                }else{
                    juli+=dy;
                    //下滑监听
                    pan();
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDepartmentId(Integer i) {
        BasePresenter presenter = getPresenter();
        if (presenter instanceof PatientPresenter) {
            ((PatientPresenter)presenter).getPatient(i,1,10);
        }
    }


    @Override
    public void onPatientSuccess(BingYouQuanBean bingYouQuanBean) {
        if (bingYouQuanBean != null) {
            buttonAdapter.setData(bingYouQuanBean.getResult());
        }
    }

    @Override
    public void onPatientFailure(String str) {

    }

    @Override
    public void onSuccess(BingXiangBean bingXiangBean) {

    }

    @Override
    public void onFailure(String str) {

    }

    @Override
    public void onKeLieSuccess(KeLieBean keLieBean) {
        if (keLieBean != null) {
            keLieAdapter.setData(keLieBean.getResult());
        }
    }

    @Override
    public void onKeLieFailure(String str) {

    }



    //设置滑动改变
    private void pan() {
        Log.i("zhen_ju_li",juli+"");
        if(juli>40){
            topOne.setVisibility(View.GONE);
            topTwo.setVisibility(View.VISIBLE);
            reOne.setVisibility(View.GONE);
        }else{
            topTwo.setVisibility(View.GONE);
            topOne.setVisibility(View.VISIBLE);
            reOne.setVisibility(View.VISIBLE);
        }
    }
}
