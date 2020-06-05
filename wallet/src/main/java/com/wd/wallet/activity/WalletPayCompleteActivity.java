package com.wd.wallet.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.wallet.R;
import com.wd.wallet.R2;
import com.wd.wallet.adapter.WallDoctorAdapter;
import com.wd.wallet.bean.WalletDoctorBean;
import com.wd.wallet.bean.WalletRecordBean;
import com.wd.wallet.bean.WalletUserBean;
import com.wd.wallet.contract.WalletContract;
import com.wd.wallet.presenter.WalletPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletPayCompleteActivity extends BaseAcitvity implements WalletContract.IView {


    @BindView(R2.id.rv_doctor)
    RecyclerView rvDoctor;

    @Override
    protected BasePresenter initPresenter() {
        return new WalletPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wallet_pay_complete;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if(presenter instanceof WalletContract.IPresenter){
            ((WalletContract.IPresenter)presenter).getDoctor(2,1,1,5);
        }
    }

    @Override
    public void onDoctor(WalletDoctorBean walletDoctorBean) {
        List<WalletDoctorBean.ResultBean> list = walletDoctorBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        WallDoctorAdapter adapter = new WallDoctorAdapter(this, list);
        rvDoctor.setLayoutManager(manager);
        rvDoctor.setAdapter(adapter);

    }

    @Override
    public void onUserMoney(WalletUserBean walletUserBean) {

    }

    @Override
    public void onRecord(WalletRecordBean walletRecordBean) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
