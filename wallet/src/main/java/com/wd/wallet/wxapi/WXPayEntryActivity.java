package com.wd.wallet.wxapi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.wallet.R;
import com.wd.wallet.R2;
import com.wd.wallet.activity.WalletPayCompleteActivity;
import com.wd.wallet.util.Constants;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onResp(BaseResp resp) {
        int code = resp.errCode;
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            switch (resp.errCode) {
                case 0: {
                    Toast.makeText(WXPayEntryActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(WXPayEntryActivity.this, WalletPayCompleteActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                }
                case -1: {
                    Toast.makeText(WXPayEntryActivity.this, "发生错误\t可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                }
                case -2: {
                    Toast.makeText(WXPayEntryActivity.this, "用户取消支付", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                }
                default:
                    break;
            }
        }


    }
}
