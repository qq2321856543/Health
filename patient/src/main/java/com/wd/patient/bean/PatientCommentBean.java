package com.wd.patient.bean;

import java.util.List;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.bean
 * @ClassName: PatientCommentBean
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/5/31 19:00
 */
public class PatientCommentBean {
    /**
     * result : [{"commentId":21123,"commentTime":1590736360000,"commentUserId":50,"content":"6666","headPic":"http://mobile.bwstudent.com/images/health/doctor/image_pic/default/default_image_pic.jpg","nickName":"华佗","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":1},{"commentId":21120,"commentTime":1590651283000,"commentUserId":60,"content":"你才是傻逼","headPic":"http://mobile.bwstudent.com/images/health/doctor/image_pic/default/default_image_pic.jpg","nickName":"陈倩","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":1},{"commentId":21119,"commentTime":1590632106000,"commentUserId":54,"content":"你才是傻逼","headPic":"http://mobile.bwstudent.com/images/health/doctor/image_pic/default/default_image_pic.jpg","nickName":"何梦洋","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":1},{"commentId":21118,"commentTime":1590630758000,"commentUserId":246,"content":"非常好","headPic":"http://mobile.bwstudent.com/images/health/user/head_pic/2020-05-28/TbUGyF20200528211452.jpg","nickName":"雷霆嘎巴","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2},{"commentId":21117,"commentTime":1590630543000,"commentUserId":56,"content":"哈哈，鸡你太美","headPic":"http://mobile.bwstudent.com/images/health/doctor/image_pic/default/default_image_pic.jpg","nickName":"武则天","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":1}]
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
         * commentId : 21123
         * commentTime : 1590736360000
         * commentUserId : 50
         * content : 6666
         * headPic : http://mobile.bwstudent.com/images/health/doctor/image_pic/default/default_image_pic.jpg
         * nickName : 华佗
         * opinion : 0
         * opposeNum : 0
         * supportNum : 0
         * whetherDoctor : 1
         */

        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String content;
        private String headPic;
        private String nickName;
        private int opinion;
        private int opposeNum;
        private int supportNum;
        private int whetherDoctor;

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getOpinion() {
            return opinion;
        }

        public void setOpinion(int opinion) {
            this.opinion = opinion;
        }

        public int getOpposeNum() {
            return opposeNum;
        }

        public void setOpposeNum(int opposeNum) {
            this.opposeNum = opposeNum;
        }

        public int getSupportNum() {
            return supportNum;
        }

        public void setSupportNum(int supportNum) {
            this.supportNum = supportNum;
        }

        public int getWhetherDoctor() {
            return whetherDoctor;
        }

        public void setWhetherDoctor(int whetherDoctor) {
            this.whetherDoctor = whetherDoctor;
        }
    }
}
