package com.wd.inquiry.activity;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.util.RsaCoder;
import com.wd.inquiry.R;
import com.wd.inquiry.R2;
import com.wd.inquiry.adapter.MessageAdapter;
import com.wd.inquiry.bean.ConsultDoctorBean;
import com.wd.inquiry.bean.CurrentInquiryRecordBean;
import com.wd.inquiry.bean.InquiryRecordListBean;
import com.wd.inquiry.bean.MessageBean;
import com.wd.inquiry.bean.PushMessageBean;
import com.wd.inquiry.icoolor.ICoolor_ConsultDoctor;
import com.wd.inquiry.icoolor.ICoolor_Message;
import com.wd.inquiry.presenter.Presenter_ConsultDoctor;
import com.wd.inquiry.presenter.Presenter_Message;
import com.wd.inquiry.view.Edittext;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
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
@Route(path = "/inquiry/SpeakActivity")
public class SpeakActivity extends BaseAcitvity implements ICoolor_Message.IView {
    ArrayList<MessageBean> list = new ArrayList<>();

    @BindView(R2.id.rv)
    RecyclerView rv;
    private ImageView iv_fasong;
    private ImageView iv_back;
    private TextView tv_name;
    String name1="zhengha123";
    String pwd1= "88888";
    String name2="zhenghaofei";
    String pwd2="666666";
    private EditText et;
    private MessageAdapter messageAdapter;
    Boolean is=false;
    private String imname="";
    private int recordId;
    private int doctorId;
    MessageBean messageBean = new MessageBean();

    @Override
    protected BasePresenter initPresenter() {
        return new Presenter_Message(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_speak;
    }

    @Override
    protected void initView() {
        et = findViewById(R.id.et);
        iv_fasong = findViewById(R.id.iv_fasong);
        iv_back = findViewById(R.id.iv_back);
        tv_name = findViewById(R.id.tv_name);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,true);
        rv.setLayoutManager(layoutManager);
        messageAdapter = new MessageAdapter(this);
        rv.setAdapter(messageAdapter);
    }

    @Override
    protected void initData() {
        //获取当前问诊id
        BasePresenter presenter = getPresenter();
        if (presenter != null) {
            ((ICoolor_Message.IPresenter)presenter).getCurrentInquiryRecord();
        }

        //获取历史消息
        MessageList();
        String userName = getIntent().getStringExtra("UserName");
        imname=userName;
        String doctorname = getIntent().getStringExtra("doctorname");
        if (userName.length()>25){
            try {
                String Jname = RsaCoder.decryptByPublicKey(userName);
                imname=Jname;
                Log.i("ooo",""+imname);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //控件赋值
        tv_name.setText(doctorname);
//        BasePresenter presenter = getPresenter();
//        if (presenter != null) {
//            ((ICoolor_ConsultDoctor.IPresenter)presenter).getConsultDoctor(id);
//        }
        //设置用户名

        //JMessageClient.registerEventReceiver(this);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.translateleft,R.anim.translate);
            }
        });
        //获取用户信息
//        JMessageClient.getUserInfo(imname, "b5f102cc307091e167ce52e0", new GetUserInfoCallback() {
//            @Override
//            public void gotResult(int responseCode, String responseMessage, UserInfo info) {
//                Toast.makeText(SpeakActivity.this, "sdf"+responseMessage, Toast.LENGTH_SHORT).show();
//                Log.i("xxx","getUserInfo："+responseMessage+info.getAppKey()+info.getUserName());
//
//            }
//        });

        iv_fasong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string = et.getText().toString();
                et.setText("");
                sendMess(imname,string);
                //服务器存储消息
                if (presenter != null) {
                    ((ICoolor_Message.IPresenter)presenter).getPushMessage(recordId,string,1,doctorId);
                }
//                messageBean.setSendMessage(string);
//                messageBean.setCloseMessage("");
//                messageBean.setType(2);
//                list.add(messageBean);
               // messageAdapter.setData(list);
            }
        });
        Runnable task = new Runnable() {
            @Override
            public void run() {
                /**
                 * 此处执行任务
                 * */
                mHanlder.sendEmptyMessage(1);
                mHanlder.postDelayed(this, 1 * 1000);//延迟5秒,再次执行task本身,实现了循环的效果
            }
        };
        mHanlder.postDelayed(task, 500);//第一次调用,延迟1秒执行task
    }
    public void sendMess(String name,String str){
                //创建跨应用会话
        Conversation con = Conversation.createSingleConversation(name, "b5f102cc307091e167ce52e0");
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
                    Log.i("ooo",""+s);
                    Toast.makeText(SpeakActivity.this, "发送失败"+i, Toast.LENGTH_SHORT).show();

                    // 消息发送失败
                }
            }
        });
        //发送消息
        JMessageClient.sendMessage(message);
    }
    public void DoctorInfo(String name){
        JMessageClient.getUserInfo(name, "b5f102cc307091e167ce52e0", new GetUserInfoCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage, UserInfo info) {
                Log.i("ooo","getUserInfo："+responseMessage);
                Log.i("ooo","getUserInfo："+responseMessage+info.getAppKey()+info.getUserName());

            }
        });
    }


//    @Override
//    public void getConsultDoctorSuccess(ConsultDoctorBean consultDoctorBean) {
//        String doctorUserName = consultDoctorBean.getDoctorUserName();
//
//    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getText(String str){
//        MessageBean messageBean = new MessageBean();
//        messageBean.setCloseMessage(str);
//        messageBean.setSendMessage("");
//        messageBean.setType(1);
//        list.add(messageBean);
        //messageAdapter.setData(list);
        BasePresenter presenter = getPresenter();
        if (presenter != null) {
            ((ICoolor_Message.IPresenter)presenter).getInquiryRecordList(recordId,1,20);
        }
    }

    private boolean isInputMethodShowing() {
        //获取当前屏幕内容的高度
        int screenHeight = getWindow().getDecorView().getHeight();
        //获取View可见区域的bottom
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);

        return screenHeight - rect.bottom - getSoftButtonsBarHeight() != 0;
    }

    /**
     * 底部虚拟按键栏的高度
     * @return
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private int getSoftButtonsBarHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        //这个方法获取可能不是真实屏幕的高度
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int usableHeight = metrics.heightPixels;
        //获取当前屏幕的真实高度
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight) {
            return realHeight - usableHeight;
        } else {
            return 0;
        }
    }
    private Handler mHanlder = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    boolean inputMethodShowing = isInputMethodShowing();
                    if (inputMethodShowing){
                        rv.scrollToPosition(list.size()-1);//滚动到指定位置
                    }
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };
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

    @Override
    public void getPushMessageSuccess(PushMessageBean pushMessageBean) {

        Toast.makeText(this, ""+pushMessageBean.getMessage(), Toast.LENGTH_SHORT).show();
        BasePresenter presenter = getPresenter();
        if (presenter != null) {
            ((ICoolor_Message.IPresenter)presenter).getInquiryRecordList(recordId,1,20);
        }
    }

    @Override
    public void getInquiryRecordListSuccess(InquiryRecordListBean inquiryRecordListBean) {
        List<InquiryRecordListBean.ResultBean> result = inquiryRecordListBean.getResult();
//        for (InquiryRecordListBean.ResultBean resultBean:result){
//            int direction = resultBean.getDirection();
//            String content = resultBean.getContent();
//            if (direction==1){
//                //发送消息
//                messageBean.setSendMessage(content);
//                messageBean.setType(2);
//            }else {
//                //接受消息
//                messageBean.setSendMessage(content);
//                messageBean.setType(1);
//            }
//            list.add(messageBean);
//
//        }
//        if (result.size()!=0){
//            Long askTime = result.get(result.size() - 1).getAskTime();
//            messageBean.setTime(askTime);
//        }
        ArrayList<InquiryRecordListBean.ResultBean> resultBeans = new ArrayList<>();
        resultBeans.addAll(result);
        messageAdapter.setData(resultBeans);
    }

    @Override
    public void getCurrentInquiryRecordSuccess(CurrentInquiryRecordBean currentInquiryRecordBean) {
        //问诊id
        recordId = currentInquiryRecordBean.getResult().getRecordId();
        doctorId = currentInquiryRecordBean.getResult().getDoctorId();
        BasePresenter presenter = getPresenter();
        if (presenter != null) {
            ((ICoolor_Message.IPresenter)presenter).getInquiryRecordList(recordId,1,20);
        }
    }
    public void MessageList(){
        BasePresenter presenter = getPresenter();
        if (presenter != null) {
            ((ICoolor_Message.IPresenter)presenter).getCurrentInquiryRecord();
        }
    }
}
