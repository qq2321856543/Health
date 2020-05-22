package com.wd.inquiry.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.util.Locale;

import butterknife.BindView;
import cn.jmessage.biz.httptask.task.GetEventNotificationTaskMng;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
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
    String name1="zhf123";
    String pwd1="123456";
    String name2="zhenghaofei";
    String pwd2="666666";

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
        //JMessageClient.registerEventReceiver(this);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //获取用户信息
        JMessageClient.getUserInfo("acZ0Uy767986297", "c7f6a1d56cb8da740fd18bfa", new GetUserInfoCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage, UserInfo info) {
                Toast.makeText(SpeakActivity.this, "sdf"+responseMessage, Toast.LENGTH_SHORT).show();
                Log.i("xxx","getUserInfo："+responseMessage+info.getAppKey()+info.getUserName());

            }
        });

//        //创建跨应用会话
//        Conversation con = Conversation.createSingleConversation(name1, "c7f6a1d56cb8da740fd18bfa");
//        MessageContent content = new TextContent("hello");
//        //创建一条消息
//        Message message = con.createSendMessage(content);
//        message.setOnSendCompleteCallback(new BasicCallback() {
//            @Override
//            public void gotResult(int i, String s) {
//                if (i == 0) {
//                    Toast.makeText(SpeakActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
//                    // 消息发送成功
//                } else {
//                    Toast.makeText(SpeakActivity.this, "发送失败"+i, Toast.LENGTH_SHORT).show();
//
//                    // 消息发送失败
//                }
//            }
//        });
//        //发送消息
//        JMessageClient.sendMessage(message);

    }



}
