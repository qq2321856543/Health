package com.wd.my.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.util.RetrofitUtil;
import com.wd.common.base.util.util.SPUtils;
import com.wd.my.R;
import com.wd.my.R2;
import com.wd.my.bean.OCRBean;
import com.wd.my.bean.UpLoadBean;
import com.wd.my.util.CreatApis;
import com.wildma.pictureselector.PictureSelector;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IDcardActivity extends BaseAcitvity implements View.OnClickListener {

    @BindView(R2.id.iv_one)
    ImageView iv_one;
    @BindView(R2.id.iv_two)
    ImageView iv_two;
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_idcard;
    }

    @Override
    protected void initView() {
        iv_one.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.iv_one){
            PictureSelector
                    .create(this, PictureSelector.SELECT_REQUEST_CODE)
                    .selectPicture(true, 200, 200, 1, 1);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                String strFilep = data.getStringExtra(PictureSelector.PICTURE_PATH);
                //String strFilep = "D:\\LinuxTestPic\\2身份证\\2.jpg";  //图片本地存储的路径
                String pid = "2"; //产品pid

                String key = "LE6epjU5nXiP5nd5KcJvN4";    //用户的key,登录之后能够查看到
                String secret = "55a1adc7f2b4445286e2815a13a740d3"; //用户的secret
                String url = "http://etoplive.com/ocr/recog.srvc";
                File file = new File(strFilep);
                try (FileInputStream is = new FileInputStream(file)) {
                    byte[] da = new byte[is.available()];
                    is.read(da);
                    String filedata = com.wd.common.base.util.util.Base64.encode(da);
                    //String filedata = Base64.encode(da);
                    OkHttpClient client = new OkHttpClient.Builder()
                            .connectTimeout(15, TimeUnit.SECONDS)// 连接超时时间
                            .writeTimeout(30, TimeUnit.SECONDS)// 写入超时时间
                            .readTimeout(60, TimeUnit.SECONDS)// 读取超时时间
                            .build();
                    FormBody mBody = new FormBody.Builder()
                            .add("pid", pid)
                            .add("file", filedata)
                            .add("key", key)
                            .add("secret", secret)
                            .build();
                    Request request = new Request.Builder().url(url).post(mBody).build();
                    Response response = client.newCall(request).execute();
                    String result = response.body().string();
                    Gson gson = new Gson();
                    OCRBean ocrBean = gson.fromJson(result, OCRBean.class);
                    List<OCRBean.ResultListBean> resultList = ocrBean.getResultList();
                    String value = resultList.get(0).getFieldList().get(2).getValue();
                    Log.i("mmm",""+value);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                    }
                }).start();
            }

            }

        }
    }

