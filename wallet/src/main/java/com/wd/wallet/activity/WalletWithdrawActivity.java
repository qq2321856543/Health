package com.wd.wallet.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.wallet.R;
import com.wd.wallet.R2;
import com.wd.wallet.bean.WalletAliPayBean;
import com.wd.wallet.bean.WalletDrawCashBean;
import com.wd.wallet.bean.WalletWxPayBean;
import com.wd.wallet.contract.IRechargeContract;
import com.wd.wallet.presenter.RechargePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletWithdrawActivity extends BaseAcitvity implements IRechargeContract.IView {


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
    @BindView(R2.id.rl2)
    RelativeLayout rl2;
    int flag=1;
    int money;
    private String string;
    int num;
    @Override
    protected BasePresenter initPresenter() {
        return new RechargePresenter(this);
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
        Intent intent = getIntent();
        String myMoney = intent.getStringExtra("myMoney");
        num=Integer.parseInt(myMoney);
        tvWithdrawAll.setText(myMoney+"H币,可提现"+(num/100)+"元,全部提现");
        ckWithdraw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    etRechargeMoney.setHint(myMoney);
                    etRechargeMoney.setHintTextColor(Color.parseColor("#3087ea"));
                }else {
                    etRechargeMoney.setHint("");
                }
            }
        });
        ivWithdrawBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击显示当前银行卡
        ivWithdrawCheckCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag++;
                if(flag%2==0){
                    rl2.setVisibility(View.VISIBLE);
                    ivWithdrawCheckCard.setImageResource(R.mipmap.wallet_withdraw_down);
                }else {
                    rl2.setVisibility(View.INVISIBLE);
                    ivWithdrawCheckCard.setImageResource(R.mipmap.wallet_withdraw_right);
                }

            }
        });
        tvWithdrawRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletWithdrawActivity.this, WithdrawRecordActivity.class);
                startActivity(intent);
            }
        });
        btWithdraw.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                string = etRechargeMoney.getText().toString();
                money=Integer.parseInt(string);
                BasePresenter presenter = getPresenter();
                if(presenter instanceof IRechargeContract.IPresenter){
                    ((IRechargeContract.IPresenter)presenter).getDrawCash(money);
                }
            }
        });
    }

    @Override
    public void onDrawCash(WalletDrawCashBean walletDrawCashBean) {
        String message = walletDrawCashBean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        if(walletDrawCashBean.getStatus().equals("0000")){
            Intent intent = new Intent(WalletWithdrawActivity.this, WalletWithdrawCompleteActivity.class);
            intent.putExtra("money",string);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onWeChatPay(WalletWxPayBean walletWxPayBean) {

    }

    @Override
    public void onAliPay(WalletAliPayBean walletAliPayBean) {

    }


}
