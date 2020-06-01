package com.wd.wallet.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.wallet.R;
import com.wd.wallet.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletWithdrawActivity extends BaseAcitvity {


    @BindView(R2.id.iv_withdraw_back)
    ImageView ivWithdrawBack;
    @BindView(R2.id.tv_withdraw_record)
    TextView tvWithdrawRecord;
    @BindView(R2.id.tv_withdraw_card)
    TextView tvWithdrawCard;
    @BindView(R2.id.iv_withdraw_check_card)
    ImageView ivWithdrawCheckCard;
    @BindView(R2.id.et_recharge_money)
    EditText etRechargeMoney;
    @BindView(R2.id.ck_withdraw)
    CheckBox ckWithdraw;
    @BindView(R2.id.tv_withdraw_all)
    TextView tvWithdrawAll;
    @BindView(R2.id.bt_withdraw)
    Button btWithdraw;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wallet_withdraw;
    }

    @Override
    protected void initView() {

//        etRechargeMoney.setFocusable(true);
//        etRechargeMoney.setFocusableInTouchMode(true);
//        etRechargeMoney.setCursorVisible(true);
//        etRechargeMoney.requestFocus();
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
