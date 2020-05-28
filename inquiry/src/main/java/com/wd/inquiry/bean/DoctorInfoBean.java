package com.wd.inquiry.bean;

import java.util.List;

public class DoctorInfoBean {



    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
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

    public static class ResultBean {
        /**
         * badNum : 0
         * commentList : [{"commentTime":1590485858000,"content":"很专业","headPic":"http://mobile.bwstudent.com/images/health/user/head_pic/default/default_head_1.jpg","nickName":"LF_XJOBR"}]
         * commentNum : 1
         * doctorId : 52
         * doctorName : 华佗
         * doctorReceiveGiftList : []
         * followFlag : 2
         * goodField : 混子
         * imagePic : http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image4.jpg
         * inauguralHospital : 精神病科
         * jobTitle : 主治医师
         * personalProfile : 什么也不是
         * praise : 50.00%
         * praiseNum : 1
         * serverNum : 4
         * servicePrice : 500
         */

        private int badNum;
        private int commentNum;
        private int doctorId;
        private String doctorName;
        private int followFlag;
        private String goodField;
        private String imagePic;
        private String inauguralHospital;
        private String jobTitle;
        private String personalProfile;
        private String praise;
        private int praiseNum;
        private int serverNum;
        private int servicePrice;
        private List<CommentListBean> commentList;
        private List<?> doctorReceiveGiftList;

        public int getBadNum() {
            return badNum;
        }

        public void setBadNum(int badNum) {
            this.badNum = badNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public int getFollowFlag() {
            return followFlag;
        }

        public void setFollowFlag(int followFlag) {
            this.followFlag = followFlag;
        }

        public String getGoodField() {
            return goodField;
        }

        public void setGoodField(String goodField) {
            this.goodField = goodField;
        }

        public String getImagePic() {
            return imagePic;
        }

        public void setImagePic(String imagePic) {
            this.imagePic = imagePic;
        }

        public String getInauguralHospital() {
            return inauguralHospital;
        }

        public void setInauguralHospital(String inauguralHospital) {
            this.inauguralHospital = inauguralHospital;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getPersonalProfile() {
            return personalProfile;
        }

        public void setPersonalProfile(String personalProfile) {
            this.personalProfile = personalProfile;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getServerNum() {
            return serverNum;
        }

        public void setServerNum(int serverNum) {
            this.serverNum = serverNum;
        }

        public int getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(int servicePrice) {
            this.servicePrice = servicePrice;
        }

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public List<?> getDoctorReceiveGiftList() {
            return doctorReceiveGiftList;
        }

        public void setDoctorReceiveGiftList(List<?> doctorReceiveGiftList) {
            this.doctorReceiveGiftList = doctorReceiveGiftList;
        }

        public static class CommentListBean {
            /**
             * commentTime : 1590485858000
             * content : 很专业
             * headPic : http://mobile.bwstudent.com/images/health/user/head_pic/default/default_head_1.jpg
             * nickName : LF_XJOBR
             */

            private long commentTime;
            private String content;
            private String headPic;
            private String nickName;

            public long getCommentTime() {
                return commentTime;
            }

            public void setCommentTime(long commentTime) {
                this.commentTime = commentTime;
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
        }
    }
}
