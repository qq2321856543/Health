package com.wd.login.activity.bean;

/**
 * Time: 2020/5/21
 * Author: 王冠华
 * Description:
 */
public class LoginLoginBean {

    /**
     * result : {"age":0,"email":"767986297@qq.com","headPic":"http://mobile.bwstudent.com/images/health/user/head_pic/default/default_head_3.jpg","height":0,"id":247,"jiGuangPwd":"YqEXf9XsNRHTU7yPnrYL54gBEYza/Th47H7QR+NuZRB+PDKTRZeVzORsiWstCwD358Tq2AfgLOUgH7QG8ZbMHiqpZ3YpP80dhOEAC32lwhfow3PPwc5lPd0lXn4iRtlId0fVp2odITX9lUvSTTQPPOXjPCHS5TmTNjGgH3keiKU=","nickName":"Mm_TLAJD","sessionId":"1590060032351247","sex":1,"userName":"acZ0Uy767986297","weight":0,"whetherBingWeChat":2}
     * message : 登录成功
     * status : 0000
     */

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
         * age : 0
         * email : 767986297@qq.com
         * headPic : http://mobile.bwstudent.com/images/health/user/head_pic/default/default_head_3.jpg
         * height : 0
         * id : 247
         * jiGuangPwd : YqEXf9XsNRHTU7yPnrYL54gBEYza/Th47H7QR+NuZRB+PDKTRZeVzORsiWstCwD358Tq2AfgLOUgH7QG8ZbMHiqpZ3YpP80dhOEAC32lwhfow3PPwc5lPd0lXn4iRtlId0fVp2odITX9lUvSTTQPPOXjPCHS5TmTNjGgH3keiKU=
         * nickName : Mm_TLAJD
         * sessionId : 1590060032351247
         * sex : 1
         * userName : acZ0Uy767986297
         * weight : 0
         * whetherBingWeChat : 2
         */

        private int age;
        private String email;
        private String headPic;
        private int height;
        private int id;
        private String jiGuangPwd;
        private String nickName;
        private String sessionId;
        private int sex;
        private String userName;
        private int weight;
        private int whetherBingWeChat;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getJiGuangPwd() {
            return jiGuangPwd;
        }

        public void setJiGuangPwd(String jiGuangPwd) {
            this.jiGuangPwd = jiGuangPwd;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getWhetherBingWeChat() {
            return whetherBingWeChat;
        }

        public void setWhetherBingWeChat(int whetherBingWeChat) {
            this.whetherBingWeChat = whetherBingWeChat;
        }
    }
}
