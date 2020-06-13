package com.wd.patient.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.core.Controller;
import com.app.hubert.guide.model.GuidePage;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BaseApplication;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.common.base.util.util.SPUtils;
import com.wd.patient.R;
import com.wd.patient.R2;
import com.wd.patient.adapter.PingLunAdapter;
import com.wd.patient.bean.BingXiangBean;
import com.wd.patient.bean.BingYouQuanBean;
import com.wd.patient.bean.KeLieBean;
import com.wd.patient.bean.PatientCommentBean;
import com.wd.patient.bean.PublishCommentBean;
import com.wd.patient.contract.PatientContract;
import com.wd.patient.network.PatientApis;
import com.wd.patient.presenter.PatientPresenter;
import com.wd.patient.view.GuideView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PatientParticularsActivity extends BaseAcitvity implements PatientContract.IView {


    @BindView(R2.id.ti_tle)
    TextView tiTle;
    @BindView(R2.id.aaa)
    ImageView aaa;
    @BindView(R2.id.name)
    TextView name;
    @BindView(R2.id.bingzheng)
    TextView bingzheng;
    @BindView(R2.id.keshi)
    TextView keshi;
    @BindView(R2.id.bing_zhuang_xiang)
    TextView bingZhuangXiang;
    @BindView(R2.id.yiliao_jingli)
    TextView yiliaoJingli;
    @BindView(R2.id.xiangguan_img)
    SimpleDraweeView xiangguanImg;
    @BindView(R2.id.num_one)
    TextView numOne;
    @BindView(R2.id.num_two)
    TextView numTwo;
    @BindView(R2.id.yiyuan)
    TextView yiYuan;
    @BindView(R2.id.shijian)
    TextView shiJian;
    @BindView(R2.id.ping)
    LinearLayout ping;
    @BindView(R2.id.adoptComment)
    TextView adoptComment;
    @BindView(R2.id.tou_img)
    SimpleDraweeView touImg;
    @BindView(R2.id.zuixia_name)
    TextView zuixiaName;
    @BindView(R2.id.huo_h_b)
    TextView huoHB;
    @BindView(R2.id.data)
    TextView data;
    @BindView(R2.id.shang_top)
    LinearLayout shangTop;
    private int sickCircleId;
    private PingLunAdapter pingLunAdapter;

    @Override
    protected BasePresenter initPresenter() {
        return new PatientPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_patient_particulars;
    }

    @Override
    protected void initView() {
        //蒙层实现
        NewbieGuide.with(this)
                .setLabel("guide1")
                .addGuidePage(GuidePage.newInstance()
                        .setBackgroundColor(Color.parseColor("#B3999999"))
                        .setLayoutRes(R.layout.dim_comment))
                .alwaysShow(true)
                .show();


        sickCircleId = getIntent().getIntExtra("sickCircleId", 0);
        BasePresenter presenter = getPresenter();
        if (presenter instanceof PatientPresenter) {
            ((PatientPresenter) presenter).getBingXiang(sickCircleId);
        }
    }

    @Override
    protected void initData() {

        //点击评论
        ping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTopPop();
                popupWindow.showAtLocation(shangTop, Gravity.TOP|Gravity.START, 0, getStatusBarHeight(PatientParticularsActivity.this));
            }
        });



    }

    @Override
    public void onPatientSuccess(BingYouQuanBean bingYouQuanBean) {

    }

    @Override
    public void onPatientFailure(String str) {

    }

    @Override
    public void onSuccess(BingXiangBean bingXiangBean) {
        if (bingXiangBean != null) {
            BingXiangBean.ResultBean result = bingXiangBean.getResult();
            tiTle.setText(result.getTitle());
            name.setText(result.getAdoptNickName());
            bingzheng.setText(result.getDisease());
            keshi.setText(result.getDepartment());
            bingZhuangXiang.setText(result.getDetail());
            yiYuan.setText(result.getTreatmentHospital());
            String startTime = new SimpleDateFormat(" yyyy.MM.dd").format(new Date(result.getTreatmentStartTime()));
            String endTime = new SimpleDateFormat(" MM.dd").format(new Date(result.getTreatmentEndTime()));
            shiJian.setText(startTime+" -"+endTime);
            yiliaoJingli.setText(result.getTreatmentProcess());
            Uri xiangguanImguri = Uri.parse(result.getPicture());
            xiangguanImg.setImageURI(xiangguanImguri);
            numOne.setText(String.valueOf(result.getCollectionNum()));
            numTwo.setText(String.valueOf(result.getCommentNum()));
            Uri touUri = Uri.parse(result.getAdoptHeadPic());
            touImg.setImageURI(touUri);
            zuixiaName.setText(result.getAdoptNickName());
            adoptComment.setText(result.getAdoptComment());
        }
    }

    @Override
    public void onFailure(String str) {

    }

    @Override
    public void onKeLieSuccess(KeLieBean keLieBean) {

    }

    @Override
    public void onKeLieFailure(String str) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }





    //popwindow
    private View popView;
    private PopupWindow popupWindow;
    private RecyclerView re;
    private EditText nei;

    /**
     * 获取状态通知栏高度
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }
    //popwindow
    private void initTopPop() {

        popView = LayoutInflater.from(this).inflate(R.layout.pop_ping_lun, null, false);
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);

        re = popView.findViewById(R.id.re);
        nei = popView.findViewById(R.id.nei);

        //设置评论列表适配器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        re.setLayoutManager(linearLayoutManager);
        pingLunAdapter = new PingLunAdapter(this);
        re.setAdapter(pingLunAdapter);


        //点击背景消失当前diglog
        popView.findViewById(R.id.cha_zi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


        //发送评论
        popView.findViewById(R.id.fasong).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = nei.getText().toString();
                //点击发送消息
                sendMessages(s);
            }
        });

        popupWindow.setBackgroundDrawable(new ColorDrawable(0x73888888));
        //评论列表展示
        showComment();
        popupWindow.setClippingEnabled(false);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
    }
    //发送评论消息
    private void sendMessages(String str) {
        PatientApis.createrRetrofit().sendMessages(sickCircleId, str)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PublishCommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PublishCommentBean publishCommentBean) {
                        if (publishCommentBean != null) {
                            Toast.makeText(PatientParticularsActivity.this, publishCommentBean.getMessage(), Toast.LENGTH_SHORT).show();
                            showComment();
                            pingLunAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //展示评论列表
    private void showComment() {

        PatientApis.createrRetrofit().showComment(sickCircleId, 1, 30)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PatientCommentBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PatientCommentBean patientCommentBean) {
                        if (patientCommentBean != null) {
                            pingLunAdapter.setData(patientCommentBean.getResult());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        //点击跳转用户发布的activity
        pingLunAdapter.setOnclckTou(new PingLunAdapter.OnClickTou() {
            @Override
            public void onck(String a, int b,String name) {
                ARouter.getInstance().build("/patient/userPatient")
                        .withString("a",a)
                        .withInt("b",b)
                        .withString("name",name)
                        .navigation();

            }
        });
    }


}
