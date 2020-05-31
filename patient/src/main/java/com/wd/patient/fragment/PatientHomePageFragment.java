package com.wd.patient.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.wd.common.base.util.Base.BaseFragment;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.patient.R;
import com.wd.patient.R2;
import com.wd.patient.activity.PatientSearchActivity;
import com.wd.patient.adapter.ButtonAdapter;
import com.wd.patient.adapter.KeLieAdapter;
import com.wd.patient.bean.BingXiangBean;
import com.wd.patient.bean.BingYouQuanBean;
import com.wd.patient.bean.KeLieBean;
import com.wd.patient.contract.PatientContract;
import com.wd.patient.presenter.PatientPresenter;
import com.wd.patient.utils.SpacesItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R2.id.ll_gone)
    LinearLayout ll_gone;
    @BindView(R2.id.patient_fragment_search)
    ImageView search;
    @BindView(R2.id.patient_fragment_etsearch)
    EditText etSearch;
    private int juli = 0;
    private ButtonAdapter buttonAdapter;
    private KeLieAdapter keLieAdapter;
    private BasePresenter presenter;

    @Override
    protected BasePresenter initPresenter() {
        return new PatientPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.patient_fragment_homepage;
    }

    @Override
    public void onResume() {
        super.onResume();
        //注册EventBus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void initView(View view) {


        LinearLayoutManager two = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        re.setLayoutManager(two);
        buttonAdapter = new ButtonAdapter(getActivity());
        re.addItemDecoration(new SpacesItemDecoration(15));
        re.setAdapter(buttonAdapter);




        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        reOne.setLayoutManager(linearLayoutManager);
        keLieAdapter = new KeLieAdapter(getActivity());
        reOne.setAdapter(keLieAdapter);

    }



    @Override
    protected void initData() {

        presenter = getPresenter();
        if (presenter instanceof PatientPresenter) {
            Log.i("lcc", "病友圈");
            ((PatientPresenter) presenter).getKeLie();
        }

        //默认第二个布局消失
        topOne.setVisibility(View.VISIBLE);
        topTwo.setVisibility(View.GONE);




        //添加滑动监听
        re.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("xxxxxx", "竖着的距离" + dy);
                if (dy < 0) {
                    juli += dy;
                    //上滑监听
                    pan();
                } else {
                    juli += dy;
                    //下滑监听
                    pan();
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getDepartmentId(Integer i) {
        BasePresenter presenter = getPresenter();
        keLieAdapter.setTextChange(i);
        keLieAdapter.notifyDataSetChanged();
        if (presenter instanceof PatientPresenter) {
            ((PatientPresenter) presenter).getPatient(i, 1, 10);
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
            if (presenter instanceof PatientPresenter) {
                ((PatientPresenter) presenter).getPatient(keLieBean.getResult().get(0).getId(), 1, 10);
            }
        }
    }

    @Override
    public void onKeLieFailure(String str) {

    }


    //设置滑动改变
    private void pan() {
        Log.i("zhen_ju_li", juli + "");
        if (juli > 20) {
            topOne.setVisibility(View.GONE);
            topTwo.setVisibility(View.VISIBLE);
            ll_gone.setVisibility(View.GONE);
        } else {
            topTwo.setVisibility(View.GONE);
            topOne.setVisibility(View.VISIBLE);
            ll_gone.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }




    @OnClick({R2.id.patient_fragment_etsearch, R2.id.patient_fragment_search})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.patient_fragment_etsearch) {
            Intent intent = new Intent(getContext(), PatientSearchActivity.class);
            intent.putExtra("search", etSearch.getText().toString());
            startActivity(intent);
        }
        if (view.getId() == R.id.patient_fragment_search) {
            Intent intent = new Intent(getContext(), PatientSearchActivity.class);
            startActivity(intent);
        }

    }
}
