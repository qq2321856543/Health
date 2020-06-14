package com.wd.patient.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BaseApplication;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.patient.R;
import com.wd.patient.R2;
import com.wd.patient.adapter.SearchAdapter;
import com.wd.patient.bean.SearchBean;
import com.wd.patient.contract.PatientSearchContract;
import com.wd.patient.presenter.PatientSearchPresenter;
import com.wd.patient.utils.SpacesItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PatientSearchActivity extends BaseAcitvity implements PatientSearchContract.IView {

    @BindView(R2.id.patient_activity_back)
    ImageView patientActivityBack;
    @BindView(R2.id.search_et)
    EditText searchEt;
    @BindView(R2.id.search_tx)
    TextView searchTx;
    @BindView(R2.id.patient_activity_notwork)
    ImageView patientActivityNotwork;
    @BindView(R2.id.patient_activity_imagegone)
    LinearLayout patientActivityImagegone;
    @BindView(R2.id.sou_re)
    RecyclerView souRe;
    @BindView(R2.id.patient_activity_notsearch_tv)
    TextView notSearchTv;
    private SearchAdapter buttonAdapter;

    @Override
    protected BasePresenter initPresenter() {
        return new PatientSearchPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_patient_search;
    }

    @Override
    protected void initView() {
        String search = getIntent().getStringExtra("search");
        searchEt.setText(search);
    }

    @Override
    protected void initData() {
        LinearLayoutManager two = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        souRe.setLayoutManager(two);
        buttonAdapter = new SearchAdapter(BaseApplication.getContext());
        souRe.addItemDecoration(new SpacesItemDecoration(15));
        souRe.setAdapter(buttonAdapter);
    }


    @Override
    public void onSuccess(SearchBean searchBean) {
        if (searchBean != null) {

            if (searchBean.getResult().size() != 0) {
                patientActivityImagegone.setVisibility(View.GONE);
                souRe.setVisibility(View.VISIBLE);
                buttonAdapter.setData(searchBean.getResult());
            }else {
                patientActivityImagegone.setVisibility(View.VISIBLE);
                souRe.setVisibility(View.GONE);
                notSearchTv.setText("抱歉 ! 没有找到 " + searchEt.getText().toString() + "相关的病友圈");
            }

        }
    }

    @Override
    public void onFailure(String str) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R2.id.patient_activity_back, R2.id.search_tx})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.patient_activity_back) {
            finish();
        }
        if (view.getId() == R.id.search_tx) {
            BasePresenter presenter = getPresenter();
            if (presenter instanceof PatientSearchPresenter) {
                ((PatientSearchPresenter)presenter).getSearch(searchEt.getText().toString());
            }
        }

    }
}
