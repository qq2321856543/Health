package com.bawei.video.presenter;


import com.bawei.video.contral.HomePageContral;
import com.bawei.video.model.HomePageModel;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.bawei.video.bean.GouMaiBean;
import com.bawei.video.bean.ShiPinBean;
import com.bawei.video.bean.ShiPinLeiMu;
import com.bawei.video.bean.ShiPinPingLieBiao;
import com.bawei.video.bean.YuEeBean;

public class HomePagePresenter extends BasePresenter implements HomePageContral.getPresenter {

    private HomePageModel homePageModel;

    public HomePagePresenter(IBaseView baseView) {
        super(baseView);
    }

    @Override
    public void getLeiMu() {
        homePageModel.getLeiMu(new HomePageContral.getModel.CallBackLeiMu() {
            @Override
            public void getLeiMuSucc(ShiPinLeiMu shiPinLeiMu) {
                IBaseView iView = getView();
                if(iView instanceof HomePageContral.getView){
                    ((HomePageContral.getView)iView).getLeiMuSucc(shiPinLeiMu);
                }
            }

            @Override
            public void getLeiMuFiuld(String str) {
                IBaseView iView = getView();
                if(iView instanceof HomePageContral.getView){
                    ((HomePageContral.getView)iView).getLeiMuFiuld(str);
                }
            }
        });
    }

    @Override
    public void getShiPin(int categoryId, int page, int count) {
        homePageModel.getShiPin(categoryId, page, count, new HomePageContral.getModel.CallBackShiPin() {
            @Override
            public void getShiPinSucc(ShiPinBean shiPinBean) {
                IBaseView iView = getView();
                if(iView instanceof HomePageContral.getView){
                    ((HomePageContral.getView)iView).getShiPinSucc(shiPinBean);
                }
            }

            @Override
            public void getShiPinFiuld(String str) {
                IBaseView iView = getView();
                if(iView instanceof HomePageContral.getView){
                    ((HomePageContral.getView)iView).getShiPinFiuld(str);
                }
            }
        });
    }

    @Override
    public void getShiPinPingLieBiao(int videoId) {
        homePageModel.getShiPinPingLieBiao(videoId, new HomePageContral.getModel.CallBackShiPinPingLieBiao() {
            @Override
            public void getShiPinPingLieBiaoSucc(ShiPinPingLieBiao shiPinPingLieBiao) {
                IBaseView iView = getView();
                if(iView instanceof HomePageContral.getView){
                    ((HomePageContral.getView)iView).getShiPinPingLieBiaoSucc(shiPinPingLieBiao);
                }
            }

            @Override
            public void getShiPinPingLieBiaoFiuld(String str) {
                IBaseView iView = getView();
                if(iView instanceof HomePageContral.getView){
                    ((HomePageContral.getView)iView).getShiPinPingLieBiaoFiuld(str);
                }
            }
        });
    }

    @Override
    public void initModel() {
        homePageModel = new HomePageModel();
    }
}
