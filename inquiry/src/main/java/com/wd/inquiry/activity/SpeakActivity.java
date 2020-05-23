package com.wd.inquiry.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
import com.wd.inquiry.adapter.MessageAdapter;
import com.wd.inquiry.bean.ConsultDoctorBean;
import com.wd.inquiry.bean.MessageBean;
import com.wd.inquiry.icoolor.ICoolor_ConsultDoctor;
import com.wd.inquiry.presenter.Presenter_ConsultDoctor;
import com.wd.inquiry.view.Edittext;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
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

public class SpeakActivity extends BaseAcitvity implements ICoolor_ConsultDoctor.IView {
    ArrayList<MessageBean> list = new ArrayList<>();

    @BindView(R2.id.rv)
    RecyclerView rv;
    private Button bt_fasong;
    private ImageView iv_back;
    private TextView tv_name;
    String name1="zhengha123";
    String pwd1= "88888";
    String name2="zhenghaofei";
    String pwd2="666666";
    private EditText et;
    private MessageAdapter messageAdapter;

    @Override
    protected BasePresenter initPresenter() {
        return new Presenter_ConsultDoctor(this);
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

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        messageAdapter = new MessageAdapter(this);
        rv.setAdapter(messageAdapter);
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", 0);
        BasePresenter presenter = getPresenter();
        if (presenter != null) {
            ((ICoolor_ConsultDoctor.IPresenter)presenter).getConsultDoctor(id);
        }

        //JMessageClient.registerEventReceiver(this);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.translateleft,R.anim.translate);
            }
        });
        //获取用户信息
//        JMessageClient.getUserInfo("acZ0Uy767986297", "c7f6a1d56cb8da740fd18bfa", new GetUserInfoCallback() {
//            @Override
//            public void gotResult(int responseCode, String responseMessage, UserInfo info) {
//                Toast.makeText(SpeakActivity.this, "sdf"+responseMessage, Toast.LENGTH_SHORT).show();
//                Log.i("xxx","getUserInfo："+responseMessage+info.getAppKey()+info.getUserName());
//
//            }
//        });

        bt_fasong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SpeakActivity.this, "发送...", Toast.LENGTH_SHORT).show();
                String string = et.getText().toString();
                et.setText("");
                sendMess(name2,string);
                MessageBean messageBean = new MessageBean();
                messageBean.setSendMessage(string);
                messageBean.setCloseMessage("");
                messageBean.setType(2);
                list.add(messageBean);
                messageAdapter.setData(list);
            }
        });

    }
    public void sendMess(String name,String str){
                //创建跨应用会话
        Conversation con = Conversation.createSingleConversation(name, "c7f6a1d56cb8da740fd18bfa");
        MessageContent content = new TextContent(str);
        //创建一条消息
        Message message = con.createSendMessage(content);
        message.setOnSendCompleteCallback(new BasicCallback() {
            @Override
            public void gotResult(int i, String s) {
                if (i == 0) {
                    Toast.makeText(SpeakActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                    // 消息发送成功
                } else {
                    Toast.makeText(SpeakActivity.this, "发送失败"+i, Toast.LENGTH_SHORT).show();

                    // 消息发送失败
                }
            }
        });
        //发送消息
        JMessageClient.sendMessage(message);
    }


    @Override
    public void getConsultDoctorSuccess(ConsultDoctorBean consultDoctorBean) {
        String doctorUserName = consultDoctorBean.getDoctorUserName();

    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getText(String str){
        MessageBean messageBean = new MessageBean();
        messageBean.setCloseMessage(str);
        messageBean.setSendMessage("");
        messageBean.setType(1);
        list.add(messageBean);
        messageAdapter.setData(list);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
