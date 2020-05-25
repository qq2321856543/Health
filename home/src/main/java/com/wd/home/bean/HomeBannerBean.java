package com.wd.home.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.util.List;

/**
 * Time: 2020/5/22
 * Author: 王冠华
 * Description:
 */
public class HomeBannerBean {

    /**
     * result : [{"imageUrl":"https://n1.hdfimg.com/g1/M03/A5/00/wYYBAFz10_WAd9DRAAEzaplfqDM541.jpg","jumpUrl":"http://www.crha.cn/yxyjdt.html","rank":1,"title":"医研究动态学"},{"imageUrl":"https://kano.guahao.com/wrO93976221?webp=80","jumpUrl":"https://hd.guahao.com/u/25314?ayr1&P-1.1.1","rank":2,"title":"全国爱眼日公益行"},{"imageUrl":"http://img.zcool.cn/community/01df5d5860946da801219c77583d5b.jpg@1280w_1l_2o_100sh.jpg","jumpUrl":"https://www.wjx.cn/jq/33939807.aspx","rank":3,"title":"体检一下"}]
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

    public static class ResultBean extends SimpleBannerInfo {
        /**
         * imageUrl : https://n1.hdfimg.com/g1/M03/A5/00/wYYBAFz10_WAd9DRAAEzaplfqDM541.jpg
         * jumpUrl : http://www.crha.cn/yxyjdt.html
         * rank : 1
         * title : 医研究动态学
         */

        private String imageUrl;
        private String jumpUrl;
        private int rank;
        private String title;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getJumpUrl() {
            return jumpUrl;
        }

        public void setJumpUrl(String jumpUrl) {
            this.jumpUrl = jumpUrl;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public Object getXBannerUrl() {
            return null;
        }
    }
}
