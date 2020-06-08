package com.wd.my.bean;

import java.util.List;

/**
 * Created by Android Studio.
 * User: 郑昊菲
 * Date: 2020/6/8
 * Time: 20:41
 */
public class OCRBean {



    private int ErrorCode;
    private RecogInfoBean recogInfo;
    private List<ResultListBean> ResultList;

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public RecogInfoBean getRecogInfo() {
        return recogInfo;
    }

    public void setRecogInfo(RecogInfoBean recogInfo) {
        this.recogInfo = recogInfo;
    }

    public List<ResultListBean> getResultList() {
        return ResultList;
    }

    public void setResultList(List<ResultListBean> ResultList) {
        this.ResultList = ResultList;
    }

    public static class RecogInfoBean {
        /**
         * requestId : 123
         * beginTime : 2020-05-20 11:24:36
         * endTime : 2020-05-20 11:24:38
         * time : 1950
         * responseId : 2111134
         */

        private String requestId;
        private String beginTime;
        private String endTime;
        private int time;
        private String responseId;

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getResponseId() {
            return responseId;
        }

        public void setResponseId(String responseId) {
            this.responseId = responseId;
        }
    }

    public static class ResultListBean {
        /**
         * pid : 2
         * type : 身份证人像页
         * position : {"left":105,"top":91,"width":430,"height":278}
         * direct : 0
         * angle : 0
         * image_data :
         * head_image_data :
         * ocr_error_code : 0
         * FieldList : [{"key":"IDNum","chn_key":"身份证号码","value":"440711198410xxxxxx","image_data":"","position":{"left":153,"top":224,"width":234,"height":23},"quad":[156,227,384,228,384,244,156,244]},{"key":"Nation","chn_key":"民族","value":"汉","image_data":"","position":{"left":128,"top":76,"width":67,"height":22},"quad":[132,79,192,79,192,95,131,95]},{"key":"Name","chn_key":"姓名","value":"张三","image_data":"","position":{"left":88,"top":39,"width":63,"height":27},"quad":[91,43,148,42,148,62,92,63]},{"key":"Address","chn_key":"地址","value":"广东省江门市蓬江区","image_data":"","position":{"left":85,"top":141,"width":182,"height":45},"quad":[88,145,264,144,264,182,88,183]},{"key":"Sex","chn_key":"性别","value":"男","image_data":"","position":{"left":0,"top":0,"width":0,"height":0},"quad":[88,145,264,144,264,182,88,183]},{"key":"Birth","chn_key":"出生","value":"19841203","image_data":"","position":{"left":0,"top":0,"width":0,"height":0},"quad":[560245776,0,560244016,0,1023,0,7,0]}]
         */

        private int pid;
        private String type;
        private PositionBean position;
        private int direct;
        private int angle;
        private String image_data;
        private String head_image_data;
        private int ocr_error_code;
        private List<FieldListBean> FieldList;

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public PositionBean getPosition() {
            return position;
        }

        public void setPosition(PositionBean position) {
            this.position = position;
        }

        public int getDirect() {
            return direct;
        }

        public void setDirect(int direct) {
            this.direct = direct;
        }

        public int getAngle() {
            return angle;
        }

        public void setAngle(int angle) {
            this.angle = angle;
        }

        public String getImage_data() {
            return image_data;
        }

        public void setImage_data(String image_data) {
            this.image_data = image_data;
        }

        public String getHead_image_data() {
            return head_image_data;
        }

        public void setHead_image_data(String head_image_data) {
            this.head_image_data = head_image_data;
        }

        public int getOcr_error_code() {
            return ocr_error_code;
        }

        public void setOcr_error_code(int ocr_error_code) {
            this.ocr_error_code = ocr_error_code;
        }

        public List<FieldListBean> getFieldList() {
            return FieldList;
        }

        public void setFieldList(List<FieldListBean> FieldList) {
            this.FieldList = FieldList;
        }

        public static class PositionBean {
            /**
             * left : 105
             * top : 91
             * width : 430
             * height : 278
             */

            private int left;
            private int top;
            private int width;
            private int height;

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getTop() {
                return top;
            }

            public void setTop(int top) {
                this.top = top;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }

        public static class FieldListBean {
            /**
             * key : IDNum
             * chn_key : 身份证号码
             * value : 440711198410xxxxxx
             * image_data :
             * position : {"left":153,"top":224,"width":234,"height":23}
             * quad : [156,227,384,228,384,244,156,244]
             */

            private String key;
            private String chn_key;
            private String value;
            private String image_data;
            private PositionBeanX position;
            private List<Integer> quad;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getChn_key() {
                return chn_key;
            }

            public void setChn_key(String chn_key) {
                this.chn_key = chn_key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getImage_data() {
                return image_data;
            }

            public void setImage_data(String image_data) {
                this.image_data = image_data;
            }

            public PositionBeanX getPosition() {
                return position;
            }

            public void setPosition(PositionBeanX position) {
                this.position = position;
            }

            public List<Integer> getQuad() {
                return quad;
            }

            public void setQuad(List<Integer> quad) {
                this.quad = quad;
            }

            public static class PositionBeanX {
                /**
                 * left : 153
                 * top : 224
                 * width : 234
                 * height : 23
                 */

                private int left;
                private int top;
                private int width;
                private int height;

                public int getLeft() {
                    return left;
                }

                public void setLeft(int left) {
                    this.left = left;
                }

                public int getTop() {
                    return top;
                }

                public void setTop(int top) {
                    this.top = top;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }
            }
        }
    }
}
