package com.wd.wallet.bean;

/**
 * Time: 2020/6/2
 * Author: 王冠华
 * Description:
 */
public class WalletWxPayBean {

    /**
     * appId : wxe3fcbe8a55cd33ff
     * mchKey : W1u2h3u4a5j6i7a8n9x1u2y3u4n5j666
     * message : 支付成功
     * nonceStr : MDsNObZQyXCnjYBi
     * packageValue : Sign=WXPay
     * partnerId : 1526061551
     * prepayId : wx021510169314726c786f8e461768085700
     * sign : C1E927C30E67EE6CB9721C2888330602
     * status : 0000
     * timeStamp : 1591081816
     */

    private String appId;
    private String mchKey;
    private String message;
    private String nonceStr;
    private String packageValue;
    private String partnerId;
    private String prepayId;
    private String sign;
    private String status;
    private String timeStamp;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
