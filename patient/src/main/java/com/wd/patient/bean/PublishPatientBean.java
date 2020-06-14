package com.wd.patient.bean;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.bean
 * @ClassName: PulishPatientBean
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/6/7 18:49
 */
public class PublishPatientBean {
    /**
     * result : 95
     * message : 发表成功
     * status : 0000
     */

    private int result;
    private String message;
    private String status;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

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
