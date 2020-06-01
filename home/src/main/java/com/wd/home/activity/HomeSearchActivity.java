package com.wd.home.activity;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.adapter.DiseaseSearchVoListAdapter;
import com.wd.home.adapter.DoctorSearchVoListAdapter;
import com.wd.home.adapter.DrugsSearchVoListAdapter;
import com.wd.home.bean.HomePageSearchBean;
import com.wd.home.bean.PopularSearchesBean;
import com.wd.home.contract.SearchContrace;
import com.wd.home.presenter.SearchPresenter;
import com.wd.home.search.BaseRecycleAdapter;
import com.wd.home.search.DbDao;
import com.wd.home.search.SeachRecordAdapter;
import com.wd.home.search.ShowButtonLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeSearchActivity extends BaseAcitvity implements SearchContrace.IView {
    @BindView(R2.id.btn_serarch)
    TextView mbtn_serarch;
    @BindView(R2.id.et_search)
    EditText met_search;
    @BindView(R2.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R2.id.mShowBtnLayout)
    ShowButtonLayout mShowBtnLayout;
    @BindView(R2.id.home_iv_black)
    ImageView mhome_iv_black;
    @BindView(R2.id.rl3)
    RelativeLayout mrl3;
    @BindView(R2.id.rl4)
    RelativeLayout mrl4;
    @BindView(R2.id.rl2)
    RelativeLayout mrl2;
    @BindView(R2.id.home_rv1)
    RecyclerView mhome_rv1;
    @BindView(R2.id.home_rv2)
    RecyclerView mhome_rv2;
    @BindView(R2.id.home_rv3)
    RecyclerView mhome_rv3;
    @BindView(R2.id.home_rl)
    RelativeLayout mhome_rl;
    @BindView(R2.id.home_tv_wss)
    TextView mhome_tv_wss;
    @BindView(R2.id.tv_ys)
    TextView tv_ys;
    @BindView(R2.id.tv_yp)
    TextView tv_yp;
    @BindView(R2.id.tv_bz)
    TextView tv_bz;

    private BaseRecycleAdapter mAdapter;

    private DbDao mDbDao;


    @Override
    protected BasePresenter initPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }
    @OnClick(R2.id.home_iv_black)
    public void home_iv_black(){
        finish();
    }
    @SuppressLint("WrongConstant")
    @Override
    protected void initView() {
        met_search.getBackground().setAlpha(30);
        mDbDao =new DbDao(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> strings = mDbDao.queryData("");
        if(strings.size()==0){
            mrl2.setVisibility(8);
        }
        mAdapter =new SeachRecordAdapter(mDbDao.queryData(""),this);
        mAdapter.setRvItemOnclickListener(new BaseRecycleAdapter.RvItemOnclickListener() {
            @Override
            public void RvItemOnclick(int position) {
                mDbDao.delete(mDbDao.queryData("").get(position));

                mAdapter.updata(mDbDao.queryData(""));


            }
        });

        mRecyclerView.setAdapter(mAdapter);
        //事件监听
        mbtn_serarch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (met_search.getText().toString().trim().length() != 0){
                    boolean hasData = mDbDao.hasData(met_search.getText().toString().trim());
                    if (!hasData){
                        mDbDao.insertData(met_search.getText().toString().trim());
                    }else {
                        Toast.makeText(HomeSearchActivity.this, "该内容已在历史记录中", Toast.LENGTH_SHORT).show();
                    }

                    //
                    mAdapter.updata(mDbDao.queryData(""));

                    String s = met_search.getText().toString();
                    BasePresenter presenter = getPresenter();
                    if(presenter instanceof SearchPresenter){
                        ((SearchPresenter) presenter).onHomePageSearch(s);
                    }

                }else {
                    Toast.makeText(HomeSearchActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if(presenter instanceof SearchPresenter){
            ((SearchPresenter) presenter).onPopularSearch();
        }
    }

    @Override
    public void onPopularSearchSuccess(PopularSearchesBean bean) {
        List<PopularSearchesBean.ResultBean> result = bean.getResult();
        for (int i = 0; i < result.size(); i++) {
            TextView view = (TextView) LayoutInflater.from(this).inflate(R.layout.hot_search_tv, mShowBtnLayout, false);
            view.setText(result.get(i).getName());
            view.setTag(result.get(i).getName());
            view.setOnClickListener(new View.OnClickListener() {//设置点击事件
                @Override
                public void onClick(View view) {
                    String keyword = (String) view.getTag();
                    Toast.makeText(HomeSearchActivity.this, keyword, Toast.LENGTH_LONG).show();
                }
            });
            mShowBtnLayout.addView(view);//添加到该view中
        }
    }

    @Override
    public void onPopularSearchFaliure(String str) {

    }

    @SuppressLint("WrongConstant")
    @Override
    public void onHomePageSearchSuccess(HomePageSearchBean bean) {
        HomePageSearchBean.ResultBean result = bean.getResult();
        List<HomePageSearchBean.ResultBean.DiseaseSearchVoListBean> diseaseSearchVoList = result.getDiseaseSearchVoList();
        List<HomePageSearchBean.ResultBean.DoctorSearchVoListBean> doctorSearchVoList = result.getDoctorSearchVoList();
        List<HomePageSearchBean.ResultBean.DrugsSearchVoListBean> drugsSearchVoList = result.getDrugsSearchVoList();
        if(diseaseSearchVoList.size()==0&&doctorSearchVoList.size()==0&&drugsSearchVoList.size()==0){
            mrl3.setVisibility(0);
            mrl2.setVisibility(8);
            mrl4.setVisibility(8);
            mhome_rl.setVisibility(8);
            String s = met_search.getText().toString();
            mhome_tv_wss.setText("抱歉!没有找到'"+s+"'相关信息");
            Log.i("xxx","222");
        }else{
            Log.i("xxx","1111");
            mhome_rl.setVisibility(0);
            mrl2.setVisibility(8);
            mrl4.setVisibility(8);
            mrl3.setVisibility(8);
            if(diseaseSearchVoList.size()!=0){
                mhome_rv1.setVisibility(0);
                tv_ys.setVisibility(0);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HomeSearchActivity.this, RecyclerView.VERTICAL, false);
                mhome_rv1.setLayoutManager(linearLayoutManager);
                DiseaseSearchVoListAdapter diseaseSearchVoListAdapter = new DiseaseSearchVoListAdapter(HomeSearchActivity.this, diseaseSearchVoList);
                mhome_rv1.setAdapter(diseaseSearchVoListAdapter);
            }else{
                mhome_rv1.setVisibility(8);
                tv_ys.setVisibility(8);
            }
            if(doctorSearchVoList.size()!=0){
                mhome_rv2.setVisibility(0);
                tv_yp.setVisibility(0);
                LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(HomeSearchActivity.this, RecyclerView.VERTICAL, false);
                mhome_rv2.setLayoutManager(linearLayoutManager1);
                DoctorSearchVoListAdapter doctorSearchVoListAdapter = new DoctorSearchVoListAdapter(HomeSearchActivity.this, doctorSearchVoList);
                mhome_rv2.setAdapter(doctorSearchVoListAdapter);
            }else{
                mhome_rv2.setVisibility(8);
                tv_yp.setVisibility(8);
            }
            if(drugsSearchVoList.size()!=0){
                mhome_rv3.setVisibility(0);
                tv_bz.setVisibility(0);
                LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(HomeSearchActivity.this, RecyclerView.VERTICAL, false);
                mhome_rv3.setLayoutManager(linearLayoutManager2);
                DrugsSearchVoListAdapter drugsSearchVoListAdapter = new DrugsSearchVoListAdapter(HomeSearchActivity.this, drugsSearchVoList);
                mhome_rv3.setAdapter(drugsSearchVoListAdapter);
            }else{
                mhome_rv3.setVisibility(8);
                tv_bz.setVisibility(8);
            }




        }
    }

    @Override
    public void onHomePageSearchFaliure(String str) {

    }
}
