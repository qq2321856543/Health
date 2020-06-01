package com.wd.wallet.bean;

/**
 * Time: 2020/6/1
 * Author: 王冠华
 * Description:
 */
public class WalletUserBean {

    /**
     * result : 989999
     * message : 查询成功
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
