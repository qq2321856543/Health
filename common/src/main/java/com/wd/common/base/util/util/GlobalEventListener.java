package com.wd.common.base.util.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ConversationType;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.event.OfflineMessageEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.model.UserInfo;

/**
 * 在demo中对于通知栏点击事件和在线消息接收事件，我们都直接在全局监听
 */
public class GlobalEventListener {
    private Context appContext;

    public GlobalEventListener(Context context) {
        appContext = context;
        JMessageClient.registerEventReceiver(this);
        Log.i("xxx","GlobalEventListener");

    }
    public void onEvent(OfflineMessageEvent event) {
        List<Message> msgs = event.getOfflineMessageList();
        for (Message msg:msgs) {
            TextContent textContent= (TextContent) msg.getContent();
            String text = textContent.getText();
            Log.i("xxx","消息:"+text);
        }
    }
    public void onEvent(MessageEvent event) {
        Message msg = event.getMessage();
        TextContent textContent = (TextContent) msg.getContent();
        String text = textContent.getText();
        Log.i("xxx","消息:"+text);

    }
//    public void onEvent(NotificationClickEvent event) {
//        jumpToActivity(event.getMessage());
//    }
//
//    public void onEvent(MessageEvent event) {
//        jumpToActivity(event.getMessage());
//    }
//
//    private void jumpToActivity(Message msg) {
//        UserInfo fromUser = msg.getFromUser();
//        //final Intent notificationIntent = new Intent(appContext, ShowMessageActivity.class);
////        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        String userName = fromUser.getUserName();
//        String appKey = fromUser.getAppKey();
//        int msgId = msg.getId();
//        Conversation conversation = JMessageClient.getSingleConversation(userName, appKey);
//        Message message = conversation.getMessage(msgId);
//        TextContent textContent= (TextContent) msg.getContent();
//        String text = textContent.getText();
//        Toast.makeText(appContext, "消息内容:"+text, Toast.LENGTH_SHORT).show();
//        if (msg.getTargetType() == ConversationType.group) {
//            GroupInfo groupInfo = (GroupInfo) msg.getTargetInfo();
////            notificationIntent.putExtra(ShowMessageActivity.EXTRA_IS_GROUP, true);
////            notificationIntent.putExtra(ShowMessageActivity.EXTRA_GROUPID, groupInfo.getGroupID());
//        } else {
////            notificationIntent.putExtra(ShowMessageActivity.EXTRA_IS_GROUP, false);
//        }
//
////        notificationIntent.putExtra(ShowMessageActivity.EXTRA_FROM_USERNAME, fromUser.getUserName());
////        notificationIntent.putExtra(ShowMessageActivity.EXTRA_FROM_APPKEY, fromUser.getAppKey());
////        notificationIntent.putExtra(ShowMessageActivity.EXTRA_MSG_TYPE, msg.getContentType().toString());
////        notificationIntent.putExtra(ShowMessageActivity.EXTRA_MSGID, msg.getId());
////        appContext.startActivity(notificationIntent);
//    }
}
