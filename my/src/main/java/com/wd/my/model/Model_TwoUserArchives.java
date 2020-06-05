package com.wd.my.model;

import com.wd.my.bean.AddUserArchivesBean;
import com.wd.my.bean.UpImageBean;
import com.wd.my.contract.ICoolor_TwoUserArchives;
import com.wd.my.util.CreatApis;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * Created by Android Studio.
 * User: 郑昊菲
 * Date: 2020/6/3
 * Time: 17:57
 */
public class Model_TwoUserArchives implements ICoolor_TwoUserArchives.IModel {
    @Override
    public void getAddUserArchives(RequestBody body, ICoolor_TwoUserArchives.AddUserArchivesCallback addUserArchivesCallback) {
        CreatApis.creatClass().getAddUserArchives(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AddUserArchivesBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AddUserArchivesBean addUserArchivesBean) {
                        addUserArchivesCallback.getSuccess(addUserArchivesBean);

                    }

                    @Override
                    public void onError(Throwable e) {
                    e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getUpImage(RequestBody body, ICoolor_TwoUserArchives.UpImageCallback upImageCallback) {
        CreatApis.creatClass().getUpImage(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UpImageBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UpImageBean upImageBean) {
                        upImageCallback.getSuccess(upImageBean);

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
