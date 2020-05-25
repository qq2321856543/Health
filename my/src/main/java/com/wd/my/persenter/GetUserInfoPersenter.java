package com.wd.my.persenter;


import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.Base.IBaseView;
import com.wd.my.bean.SignInBean;
import com.wd.my.bean.UserInfoBean;
import com.wd.my.contract.GetUserInfoContract;
import com.wd.my.model.GetUserInfoModel;

public class GetUserInfoPersenter extends BasePresenter implements GetUserInfoContract.IPersenter {

    private GetUserInfoModel mGetUserInfoModel;
    private GetUserInfoContract.IView mView;

    public GetUserInfoPersenter(IBaseView iBaseView) {
        super(iBaseView);
    }


    @Override
    public void initModel() {
        mGetUserInfoModel = new GetUserInfoModel();
        IBaseView view = getView();
        if (view != null && view instanceof GetUserInfoContract.IView) {
            mView = (GetUserInfoContract.IView) view;
        }
    }

    @Override
    public void getUserInfo() {
        mGetUserInfoModel.getUserInfo(new GetUserInfoContract.IModel.GetUserInfoCallBack() {
            @Override
            public void getUserInfoSuccess(UserInfoBean userInfoBean) {
                mView.getUserInfoSuccess(userInfoBean);
            }

            @Override
            public void getUserInfoFailed(String msg) {
                mView.getUserInfoFailed(msg);
            }
        });
    }

    @Override
    public void getSignInDay() {
        mGetUserInfoModel.getSignInDay(new GetUserInfoContract.IModel.GetSignInDayCallBack() {
            @Override
            public void getSignInSuccess(SignInBean signInBean) {
                mView.getSignInSuccess(signInBean);
            }

            @Override
            public void getSignInFailed(String msg) {
                mView.getSignInFailed(msg);
            }
        });
    }

    @Override
    public void signIn() {
        mGetUserInfoModel.signIn(new GetUserInfoContract.IModel.SignInCallBack() {
            @Override
            public void signInSuccess(SignInBean signInBean) {
                mView.signInSuccess(signInBean);
            }

            @Override
            public void signInFailed(String msg) {
                mView.signInFailed(msg);
            }
        });
    }
}
