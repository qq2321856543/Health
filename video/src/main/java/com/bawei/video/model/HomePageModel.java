package com.bawei.video.model;



import com.bawei.video.bean.GouMaiBean;
import com.bawei.video.bean.ShiPinBean;
import com.bawei.video.bean.ShiPinLeiMu;
import com.bawei.video.bean.ShiPinPingLieBiao;
import com.bawei.video.bean.YuEeBean;
import com.bawei.video.contral.HomePageContral;
import com.bawei.video.util.VideoApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HomePageModel implements HomePageContral.getModel {
    @Override
    public void getLeiMu(CallBackLeiMu callBackLeiMu) {
        VideoApis.createrRetrofit().getLeiMu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShiPinLeiMu>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShiPinLeiMu shiPinLeiMu) {
                        if(callBackLeiMu!=null){
                            callBackLeiMu.getLeiMuSucc(shiPinLeiMu);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackLeiMu!=null){
                            callBackLeiMu.getLeiMuFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getShiPin(int categoryId, int page, int count, CallBackShiPin callBackShiPin) {
        VideoApis.createrRetrofit().getShiPin(categoryId,page,count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShiPinBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShiPinBean shiPinBean) {
                        if(callBackShiPin!=null){
                            callBackShiPin.getShiPinSucc(shiPinBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackShiPin!=null){
                            callBackShiPin.getShiPinFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getShiPinPingLieBiao(int videoId, CallBackShiPinPingLieBiao callBackShiPinPingLieBiao) {
        VideoApis.createrRetrofit().getShiPinPingLieBiao(videoId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShiPinPingLieBiao>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShiPinPingLieBiao shiPinPingLieBiao) {
                        if(callBackShiPinPingLieBiao!=null){
                            callBackShiPinPingLieBiao.getShiPinPingLieBiaoSucc(shiPinPingLieBiao);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackShiPinPingLieBiao!=null){
                            callBackShiPinPingLieBiao.getShiPinPingLieBiaoFiuld(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
