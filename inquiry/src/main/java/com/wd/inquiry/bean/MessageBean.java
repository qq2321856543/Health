package com.wd.inquiry.bean;

public class MessageBean {
    private String sendMessage;
    private String closeMessage;
    private int type;
    private Long Time;

    public Long getTime() {
        return Time;
    }

    public void setTime(Long time) {
        Time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(String sendMessage) {
        this.sendMessage = sendMessage;
    }

    public String getCloseMessage() {
        return closeMessage;
    }

    public void setCloseMessage(String closeMessage) {
        this.closeMessage = closeMessage;
    }
}
