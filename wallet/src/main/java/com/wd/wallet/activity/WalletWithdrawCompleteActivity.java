package com.wd.wallet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.wallet.R;
import com.wd.wallet.R2;
import com.wd.wallet.bean.WalletDoctorBean;
import com.wd.wallet.bean.WalletRecordBean;
import com.wd.wallet.bean.WalletUserBean;
import com.wd.wallet.contract.WalletContract;
import com.wd.wallet.presenter.WalletPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletWithdrawCompleteActivity extends BaseAcitvity  {


    @BindView(R2.id.tv_withdraw_complete_money)
    TextView tvWithdrawCompleteMoney;
    @BindView(R2.id.tv_withdraw_complete_num)
    TextView tvWithdrawCompleteNum;
    @BindView(R2.id.bt_withdraw_complete_finish)
    Button btWithdrawCompleteFinish;
    @BindView(R2.id.tv_withdraw_complete_time)
    TextView tvWithdrawCompleteTime;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wallet_withdraw_complete;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String money = intent.getStringExtra("money");
        tvWithdrawCompleteMoney.setText("¥"+money);

        btWithdrawCompleteFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }




}
