package com.wd.wallet.bean;

import java.util.List;

/**
 * Time: 2020/6/5
 * Author: 王冠华
 * Description:
 */
public class WalletDoctorBean {


    /**
     * result : [{"badNum":0,"doctorId":68,"doctorName":"张医生","imagePic":"http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image6.jpg","inauguralHospital":"北京清华大学附属医院","jobTitle":"主治医师","praise":"0.00%","praiseNum":0,"serverNum":0,"servicePrice":500},{"badNum":0,"doctorId":64,"doctorName":"周盟棋","imagePic":"http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image2.jpg","inauguralHospital":"清华大学附属医院","jobTitle":"主治医师","praise":"0.00%","praiseNum":0,"serverNum":3,"servicePrice":500},{"badNum":0,"doctorId":67,"doctorName":"周盟棋","imagePic":"http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image5.jpg","inauguralHospital":"清华大学附属医院","jobTitle":"主治医师","praise":"0.00%","praiseNum":0,"serverNum":0,"servicePrice":500},{"badNum":0,"doctorId":66,"doctorName":"周盟棋","imagePic":"http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image4.jpg","inauguralHospital":"清华大学附属医院","jobTitle":"主治医师","praise":"0.00%","praiseNum":0,"serverNum":0,"servicePrice":500},{"badNum":0,"doctorId":62,"doctorName":"星宇","imagePic":"http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image7.jpg","inauguralHospital":"北京309医院","jobTitle":"主治医师","praise":"0.00%","praiseNum":0,"serverNum":0,"servicePrice":500}]
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
         * badNum : 0
         * doctorId : 68
         * doctorName : 张医生
         * imagePic : http://mobile.bwstudent.com/images/health/doctor/system_image_pic/system_image6.jpg
         * inauguralHospital : 北京清华大学附属医院
         * jobTitle : 主治医师
         * praise : 0.00%
         * praiseNum : 0
         * serverNum : 0
         * servicePrice : 500
         */

        private int badNum;
        private int doctorId;
        private String doctorName;
        private String imagePic;
        private String inauguralHospital;
        private String jobTitle;
        private String praise;
        private int praiseNum;
        private int serverNum;
        private int servicePrice;

        public int getBadNum() {
            return badNum;
        }

        public void setBadNum(int badNum) {
            this.badNum = badNum;
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
    }
}
