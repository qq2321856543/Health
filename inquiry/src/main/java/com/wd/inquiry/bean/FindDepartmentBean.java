package com.wd.inquiry.bean;

import java.util.List;

public class FindDepartmentBean {



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
         * departmentName : 内科
         * id : 7
         * pic : http://mobile.bwstudent.com/images/health/department_pic/nk.jpg
         * rank : 1
         */

        private String departmentName;
        private int id;
        private String pic;
        private int rank;
        Boolean is=false;

        public Boolean getIs() {
            return is;
        }

        public void setIs(Boolean is) {
            this.is = is;
        }
        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }
    }
}
