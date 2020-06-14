package com.bawei.video.util;





import com.bawei.video.bean.GouMaiBean;
import com.bawei.video.bean.ShiPinBean;
import com.bawei.video.bean.ShiPinLeiMu;
import com.bawei.video.bean.ShiPinPingLieBiao;
import com.bawei.video.bean.YuEeBean;
import com.wd.common.base.util.util.RetrofitUtil;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VideoApis{
    public static VideoApis createrRetrofit(){
        return RetrofitUtil.getInstance().getRetrofitServie(VideoApis.class);
    }
    //类目
    @GET("health/user/video/v1/findVideoCategoryList")
    Observable<ShiPinLeiMu> getLeiMu();

    //视频
    @GET("health/user/video/v1/findVideoVoList")
    Observable<ShiPinBean> getShiPin(@Query("categoryId") int categoryId, @Query("page") int page, @Query("count") int count);

    //视频评论列表
    @GET("health/user/video/v1/findVideoCommentList")
    Observable<ShiPinPingLieBiao> getShiPinPingLieBiao(@Query("videoId") int videoId);

    //我的钱包剩余余额
    @GET("health/user/verify/v1/findUserWallet")
    Observable<YuEeBean> getYuEe();

    //健康视频购买
    @POST("health/user/video/verify/v1/videoBuy")
    @FormUrlEncoded
    Observable<GouMaiBean> getGouMai(@Field("videoId") int videoId, @Field("price") int price);

}
