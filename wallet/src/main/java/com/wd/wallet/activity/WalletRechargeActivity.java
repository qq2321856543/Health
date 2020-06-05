package com.wd.wallet.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.wallet.R;
import com.wd.wallet.R2;
import com.wd.wallet.bean.WalletAliPayBean;
import com.wd.wallet.bean.WalletDrawCashBean;
import com.wd.wallet.bean.WalletWxPayBean;
import com.wd.wallet.contract.IRechargeContract;
import com.wd.wallet.presenter.RechargePresenter;
import com.wd.wallet.util.Constants;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletRechargeActivity extends BaseAcitvity implements IRechargeContract.IView {

    private static final int SDK_PAY_FLAG = 1;
    @BindView(R2.id.iv_recharge_back)
    ImageView ivRechargeBack;
    @BindView(R2.id.et_recharge_money)
    EditText etRechargeMoney;
    @BindView(R2.id.rb_wechat)
    RadioButton rbWechat;
    @BindView(R2.id.rb_ali)
    RadioButton rbAli;
    @BindView(R2.id.rg)
    RadioGroup rg;
    @BindView(R2.id.bt_recharge)
    Button btRecharge;
    int flag;
    double money;
    @Override
    protected BasePresenter initPresenter() {
        return new RechargePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wallet_recharge;
    }

    @Override
    protected void initView() {
        ivRechargeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {


        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(rbWechat.getId()==checkedId){
                    flag=1;
                }
                if(rbAli.getId()==checkedId){
                    flag=2;
                }
            }
        });
        //点击调用充值接口
        btRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = etRechargeMoney.getText().toString();
                money=Double.parseDouble(str);
                BasePresenter presenter = getPresenter();
                if(money>0){
                    if(presenter instanceof IRechargeContract.IPresenter){
                        ((IRechargeContract.IPresenter)presenter).getAliPay(money,flag);
                        ((IRechargeContract.IPresenter)presenter).getWeChatPay(money,flag);
                    }
                }else {
                    Toast.makeText(WalletRechargeActivity.this, "请输入充值金额", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onWeChatPay(WalletWxPayBean walletWxPayBean) {
        String message = walletWxPayBean.getMessage();

        IWXAPI wxapi = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        wxapi.registerApp(Constants.APP_ID);
        PayReq payReq = new PayReq();
        payReq.appId=Constants.APP_ID;
        payReq.partnerId=walletWxPayBean.getPartnerId();
        payReq.prepayId=walletWxPayBean.getPrepayId();
        payReq.packageValue="Sign=WXPay";
        payReq.nonceStr=walletWxPayBean.getNonceStr();
        payReq.timeStamp=""+walletWxPayBean.getTimeStamp();
        payReq.sign=walletWxPayBean.getSign();
        wxapi.sendReq(payReq);
    }
    Handler handler=new Handler();
    @Override
    public void onAliPay(WalletAliPayBean walletAliPayBean) {
//        String result = walletAliPayBean.getResult();
//        // 订单信息
//        final String orderInfo = result;
//        Runnable payRunnable = new Runnable() {
//
//            @Override
//            public void run() {
//                PayTask alipay = new PayTask(WalletRechargeActivity.this);
//                Map<String,String> result = alipay.payV2(orderInfo,true);
//                Message msg = new Message();
//                msg.what = SDK_PAY_FLAG;
//                msg.obj = result;
//                handler.sendMessage(msg);
//            }
//        };
//        // 必须异步调用
//        Thread payThread = new Thread(payRunnable);
//        payThread.start();
    }

    @Override
    public void onDrawCash(WalletDrawCashBean walletDrawCashBean) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
