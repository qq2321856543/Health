package com.wd.patient.bean;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.bean
 * @ClassName: PublishCommentBean
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/5/31 21:02
 */
public class PublishCommentBean {
    /**
     * message : 评论成功
     * status : 0000
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
