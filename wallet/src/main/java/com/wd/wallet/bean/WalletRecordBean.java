package com.wd.wallet.bean;

import java.util.List;

/**
 * Time: 2020/6/1
 * Author: 王冠华
 * Description:
 */
public class WalletRecordBean {

    /**
     * result : [{"changeNum":-500,"createTime":1590748490000,"direction":2,"type":9},{"changeNum":-500,"createTime":1590746739000,"direction":2,"type":9},{"changeNum":-500,"createTime":1590745954000,"direction":2,"type":9},{"changeNum":-500,"createTime":1590745856000,"direction":2,"type":9},{"changeNum":-500,"createTime":1590741924000,"direction":2,"type":9}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * changeNum : -500
         * createTime : 1590748490000
         * direction : 2
         * type : 9
         */

        private int changeNum;
        private long createTime;
        private int direction;
        private int type;

        public int getChangeNum() {
            return changeNum;
        }

        public void setChangeNum(int changeNum) {
            this.changeNum = changeNum;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
