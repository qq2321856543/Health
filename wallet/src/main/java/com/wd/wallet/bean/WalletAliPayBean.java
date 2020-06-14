package com.wd.wallet.bean;

/**
 * Time: 2020/6/2
 * Author: 王冠华
 * Description:
 */
public class WalletAliPayBean {

    /**
     * result : alipay_sdk=alipay-sdk-java-3.1.0&app_id=2019021363236422&biz_content=%7B%22out_trade_no%22%3A%2220200602150941050%22%2C%22subject%22%3A%22%E5%85%AB%E7%BB%B4%E7%A7%BB%E5%8A%A8%E9%80%9A%E4%BF%A1%E5%AD%A6%E9%99%A2-%E7%BB%B4%E5%BA%A6%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%221.0%22%7D&charset=utf-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fmobile.bwstudent.com%2FpayApiProd%2FaliPay%2FhealthNotification&sign=Zz32HOR9G3ef4pL4Wc39De02OUHioH3gTzIg9UxeZdSuBhghd7P43tvJYVf9MUsYW7mkmS997DZ9GJ7rfdlUINpXWkqrlLLk0Qe6tdFDq81yuqZag1zLhEbYar5h%2FP4hD6QWUKTjLy2n5j%2FvYpYRyHxkh%2BX4bflS8kwkTEmmbZbBDty0ibuiTiABVolmnx1VuGtQxYVYu3F%2BCFz4K3wgQHxbRls%2B52QgjetuOFwqn%2FDBDYcFw8yX32D8rxyPJuy%2FSUyfHt4kIamwhid1XCTEcL7wmNy3FEtYgn3hk%2BmWtSiJ60Wv74vWz4TsYvNdncb0hhbKFuPb4HifLKGEmzplxw%3D%3D&sign_type=RSA2&timestamp=2020-06-02+15%3A09%3A41&version=1.0
     * message : ok
     * status : 0000
     */

    private String result;
    private String message;
    private String status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
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
