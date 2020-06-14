package com.bawei.video.bean;

import java.util.List;

public class ShiPinBean {

    /**
     * result : [{"abstracts":"颈椎缓解疼痛技巧","buyNum":1,"categoryId":3,"duration":15,"id":17,"originalUrl":"http://mobile.bwstudent.com/video/health/original/js/js1.mp4","price":500,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/js/js1.mp4","title":"减肥常识","whetherBuy":2,"whetherCollection":2},{"abstracts":"长期低头族玩手机，颈椎可能导致的改变","buyNum":1,"categoryId":3,"duration":13,"id":19,"originalUrl":"http://mobile.bwstudent.com/video/health/original/js/js3.mp4","price":0,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/js/js3.mp4","title":"骨骼运动解剖","whetherBuy":2,"whetherCollection":2},{"abstracts":"教你一个快速缓解颈肩酸痛的方法","buyNum":0,"categoryId":3,"duration":17,"id":18,"originalUrl":"http://mobile.bwstudent.com/video/health/original/js/js2.mp4","price":0,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/js/js2.mp4","title":"脊椎如何锻炼","whetherBuy":2,"whetherCollection":2},{"abstracts":"现代人颈椎不舒服的太多，坚持每天锻炼吧","buyNum":0,"categoryId":3,"duration":11,"id":20,"originalUrl":"http://mobile.bwstudent.com/video/health/original/js/js4.mp4","price":0,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/js/js4.mp4","title":"零基础健身","whetherBuy":2,"whetherCollection":2},{"abstracts":"颈椎拍片别花冤枉钱，拒绝过度检查","buyNum":0,"categoryId":3,"duration":29,"id":21,"originalUrl":"http://mobile.bwstudent.com/video/health/original/js/js5.mp4","price":0,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/js/js5.mp4","title":"沈主任说颈椎","whetherBuy":2,"whetherCollection":2},{"abstracts":"这种方式治好了我多年的颈椎病","buyNum":0,"categoryId":3,"duration":10,"id":22,"originalUrl":"http://mobile.bwstudent.com/video/health/original/js/js6.mp4","price":0,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/js/js6.mp4","title":"亲测有效！","whetherBuy":2,"whetherCollection":2},{"abstracts":"四招拯救你的颈椎，最萌中医大叔亲自上阵示范","buyNum":0,"categoryId":3,"duration":44,"id":23,"originalUrl":"http://mobile.bwstudent.com/video/health/original/js/js7.mp4","price":0,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/js/js7.mp4","title":"养生堂","whetherBuy":2,"whetherCollection":2},{"abstracts":"颈椎病的形成原因","buyNum":0,"categoryId":3,"duration":11,"id":24,"originalUrl":"http://mobile.bwstudent.com/video/health/original/js/js8.mp4","price":0,"shearUrl":"http://mobile.bwstudent.com/video/health/cut/js/js8.mp4","title":"颈椎健康","whetherBuy":2,"whetherCollection":2}]
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
         * abstracts : 颈椎缓解疼痛技巧
         * buyNum : 1
         * categoryId : 3
         * duration : 15
         * id : 17
         * originalUrl : http://mobile.bwstudent.com/video/health/original/js/js1.mp4
         * price : 500
         * shearUrl : http://mobile.bwstudent.com/video/health/cut/js/js1.mp4
         * title : 减肥常识
         * whetherBuy : 2
         * whetherCollection : 2
         */

        private String abstracts;
        private int buyNum;
        private int categoryId;
        private int duration;
        private int id;
        private String originalUrl;
        private int price;
        private String shearUrl;
        private String title;
        private int whetherBuy;
        private int whetherCollection;

        public String getAbstracts() {
            return abstracts;
        }

        public void setAbstracts(String abstracts) {
            this.abstracts = abstracts;
        }

        public int getBuyNum() {
            return buyNum;
        }

        public void setBuyNum(int buyNum) {
            this.buyNum = buyNum;
        }

        public int getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(int categoryId) {
            this.categoryId = categoryId;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOriginalUrl() {
            return originalUrl;
        }

        public void setOriginalUrl(String originalUrl) {
            this.originalUrl = originalUrl;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getShearUrl() {
            return shearUrl;
        }

        public void setShearUrl(String shearUrl) {
            this.shearUrl = shearUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWhetherBuy() {
            return whetherBuy;
        }

        public void setWhetherBuy(int whetherBuy) {
            this.whetherBuy = whetherBuy;
        }

        public int getWhetherCollection() {
            return whetherCollection;
        }

        public void setWhetherCollection(int whetherCollection) {
            this.whetherCollection = whetherCollection;
        }
    }
}
