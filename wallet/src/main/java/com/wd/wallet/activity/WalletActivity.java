package com.wd.wallet.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.wallet.R;
import com.wd.wallet.R2;
import com.wd.wallet.adapter.WalletRecordAdapter;
import com.wd.wallet.bean.WalletRecordBean;
import com.wd.wallet.bean.WalletUserBean;
import com.wd.wallet.contract.WalletContract;
import com.wd.wallet.presenter.WalletPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/wallet/WalletActivity")
public class WalletActivity extends BaseAcitvity implements WalletContract.IView {


    @BindView(R2.id.tv_wallet_money)
    TextView tvWalletMoney;
    @BindView(R2.id.bt_wallet_withdraw)
    Button btWalletWithdraw;
    @BindView(R2.id.bt_wallet_recharge)
    Button btWalletRecharge;
    @BindView(R2.id.rv_wallet)
    RecyclerView rvWallet;
    @BindView(R2.id.iv_wallet_back)
    ImageView ivWalletBack;
    String str = "http://www.17qq.com/img_qqtouxiang/6062256.jpeg";
    @BindView(R2.id.iv_bg)
    SimpleDraweeView ivBg;

    @Override
    protected BasePresenter initPresenter() {
        return new WalletPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initView() {
        ivWalletBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter instanceof WalletContract.IPresenter) {
            ((WalletContract.IPresenter) presenter).getUserMoney();
            ((WalletContract.IPresenter) presenter).getRecord(1, 10);
        }
        Uri uri = Uri.parse(str);
        ivBg.setImageURI(uri);
        btWalletWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletActivity.this, WalletWithdrawActivity.class);
                startActivity(intent);
            }
        });
        btWalletRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletActivity.this, WalletRechargeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onUserMoney(WalletUserBean walletUserBean) {
        int result = walletUserBean.getResult();
        tvWalletMoney.setText(result + "");
    }

    @Override
    public void onRecord(WalletRecordBean walletRecordBean) {
        List<WalletRecordBean.ResultBean> list = walletRecordBean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(WalletActivity.this, RecyclerView.VERTICAL, false);
        WalletRecordAdapter adapter = new WalletRecordAdapter(this, list);
        rvWallet.setLayoutManager(manager);
        rvWallet.setAdapter(adapter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
