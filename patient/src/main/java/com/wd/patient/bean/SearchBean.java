package com.wd.patient.bean;

import java.util.List;

public class SearchBean {

    /**
     * result : [{"amount":0,"collectionNum":0,"commentNum":10,"detail":"天一黑眼睛就不好使了。","releaseTime":1570550400000,"sickCircleId":41,"title":"眼睛疼"},{"amount":10,"collectionNum":0,"commentNum":0,"detail":"不是表面的背痛，是内里的腰部疼痛，本人一直从事长时间坐姿的工作，导致腰部经常酸痛，而且工比较忙，一直没有时间去看，导致最近有点疼的厉害。求一个治疗方案。","releaseTime":1569168000000,"sickCircleId":33,"title":"腰有点疼，求个好的治疗方案"},{"amount":20,"collectionNum":0,"commentNum":0,"detail":"脖子疼的有点厉害，不能左右扭动，想动一下舒缓僵硬的感觉也不能，我这以后还要上班呢，这怎么办啊？","releaseTime":1569168000000,"sickCircleId":28,"title":"我的颈椎有点疼"}]
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
         * amount : 0
         * collectionNum : 0
         * commentNum : 10
         * detail : 天一黑眼睛就不好使了。
         * releaseTime : 1570550400000
         * sickCircleId : 41
         * title : 眼睛疼
         */

        private int amount;
        private int collectionNum;
        private int commentNum;
        private String detail;
        private long releaseTime;
        private int sickCircleId;
        private String title;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(int collectionNum) {
            this.collectionNum = collectionNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getSickCircleId() {
            return sickCircleId;
        }

        public void setSickCircleId(int sickCircleId) {
            this.sickCircleId = sickCircleId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
