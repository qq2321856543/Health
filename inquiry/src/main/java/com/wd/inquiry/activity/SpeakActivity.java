package com.wd.inquiry.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.inquiry.R;
import com.wd.inquiry.R2;

import butterknife.BindView;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

public class SpeakActivity extends BaseAcitvity {
    @BindView(R2.id.rv)
    RecyclerView rv;
    private EditText et;
    private Button bt_fasong;
    private ImageView iv_back;
    private TextView tv_name;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_speak;
    }

    @Override
    protected void initView() {
        et = findViewById(R.id.et);
        bt_fasong = findViewById(R.id.bt_fasong);
        iv_back = findViewById(R.id.iv_back);
        tv_name = findViewById(R.id.tv_name);
    }

    @Override
    protected void initData() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String name="acZ0Uy767986297";
        String appkey="c7f6a1d56cb8da740fd18bfa";

        JMessageClient.getUserInfo("username", "appKey", new GetUserInfoCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage, UserInfo info) {
                // 获取到跨应用的用户信息
            }
        });
    }
}
