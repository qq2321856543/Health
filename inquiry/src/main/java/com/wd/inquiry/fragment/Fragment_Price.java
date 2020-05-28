package com.wd.inquiry.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.common.base.util.Base.BaseFragment;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.inquiry.R;
import com.wd.inquiry.R2;
import com.wd.inquiry.activity.DoctorInfoActivity;
import com.wd.inquiry.activity.SpeakActivity;
import com.wd.inquiry.adapter.DoctorListAdapter;
import com.wd.inquiry.bean.ConsultDoctorBean;
import com.wd.inquiry.bean.CurrentInquiryRecordBean;
import com.wd.inquiry.bean.DoctorListBean;
import com.wd.inquiry.icoolor.ICoolor_DoctorList;
import com.wd.inquiry.presenter.Presenter_DoctorList;
import com.wd.inquiry.view.MyDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class Fragment_Price extends BaseFragment implements ICoolor_DoctorList.IView {
    @BindView(R2.id.iv_max)
    ImageView iv_max;
    private Button bt_ok;
    @BindView(R2.id.tv_name)
    TextView tv_name;
    @BindView(R2.id.tv_type)
    TextView tv_type;
    @BindView(R2.id.tv_location)
    TextView tv_location;
    @BindView(R2.id.tv_haoping)
    TextView tv_haoping;
    @BindView(R2.id.tv_count)
    TextView tv_count;
    ImageView iv_xiangqing;
    private TextView tv_price;
    @BindView(R2.id.rv)
    RecyclerView rv;
    @BindView(R2.id.iv_lift)
    ImageView iv_lift;
    @BindView(R2.id.iv_right)
    ImageView iv_right;
    private List<DoctorListBean.ResultBean> result;

    @Override
    protected BasePresenter initPresenter() {
        return new Presenter_DoctorList(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_price;
    }

    @Override
    protected void initView(View view) {
        bt_ok = view.findViewById(R.id.bt_ok);
        tv_price = view.findViewById(R.id.tv_price);
        iv_xiangqing=view.findViewById(R.id.iv_xiangqing);
    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter != null) {
            ((ICoolor_DoctorList.IPresenter)presenter).getDoctorList(7,4,0,1,10);
        }
    }

    @Override
    public void getDoctorListSuccess(DoctorListBean doctorListBean) {
        result = doctorListBean.getResult();
        if (result.size()==0){
            return;
        }
        DoctorListBean.ResultBean resultBean = result.get(0);
        Glide.with(getContext()).load(resultBean.getImagePic()).into(iv_max);
        tv_name.setText(resultBean.getDoctorName());
        tv_type.setText(resultBean.getJobTitle());
        tv_location.setText(resultBean.getInauguralHospital());
        tv_haoping.setText("好评率 "+resultBean.getPraise()+"%");
        tv_count.setText("服务患者数"+resultBean.getServerNum());
        tv_price.setText(resultBean.getServicePrice()+"H币/次");
        resultBean.setIs(true);



        //适配器
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false);
//        {
//            @Override
//            public boolean canScrollHorizontally() {
//            return false;
//        }
//        };
        rv.setLayoutManager(layoutManager);
        DoctorListAdapter doctorListAdapter = new DoctorListAdapter(getContext(), result);
        rv.setAdapter(doctorListAdapter);
        doctorListAdapter.OnClick(new DoctorListAdapter.setOnclick() {
            @Override
            public void click(int id) {
                for (DoctorListBean.ResultBean list: result){
                    list.setIs(false);
                    if (list.getDoctorId()==id){
                        list.setIs(true);
                        Glide.with(getContext()).load(list.getImagePic()).into(iv_max);
                        tv_name.setText(list.getDoctorName());
                        tv_type.setText(list.getJobTitle());
                        tv_location.setText(list.getInauguralHospital());
                        tv_haoping.setText("好评率 "+list.getPraise()+"%");
                        tv_count.setText("服务患者数"+list.getServerNum());
                        tv_price.setText(list.getServicePrice()+"H币/次");
                    }
                }
                doctorListAdapter.notifyDataSetChanged();
            }

        });
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (DoctorListBean.ResultBean list: result){
                    if (list.getIs()){
                        if (true){
                            //医生不繁忙
                            BasePresenter presenter = getPresenter();
                            if (presenter != null) {
                                ((ICoolor_DoctorList.IPresenter)presenter).getConsultDoctor(list.getDoctorId());
                            }

                        }
                    }
                }

            }
        });
        iv_xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (DoctorListBean.ResultBean list: result){
                    if (list.getIs()){
                        Intent intent = new Intent(getActivity(), DoctorInfoActivity.class);
                        intent.putExtra("id",list.getDoctorId());
                        startActivity(intent);
                    }
                }

            }
        });
    }

    @Override
    public void getConsultDoctorSuccess(ConsultDoctorBean consultDoctorBean) {
        if (consultDoctorBean.getMessage().equals("查询成功")){
            for (DoctorListBean.ResultBean list: result){
                if (list.getIs()){
                    Intent intent = new Intent(getContext(), SpeakActivity.class);
                    intent.putExtra("UserName",consultDoctorBean.getDoctorUserName());
                    intent.putExtra("doctorname",list.getDoctorName());
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.translate,R.anim.translateleft);
                }
            }
        }else if (consultDoctorBean.getMessage().equals("有正在沟通中的咨询")){
            View view = getLayoutInflater().inflate(R.layout.dialog, null);
            TextView textView=view.findViewById(R.id.textView);
            TextView tv_jieshu=view.findViewById(R.id.tv_jieshu);
            MyDialog mMyDialog = new MyDialog(getContext(), 0, 0, view, R.style.DialogTheme);
            mMyDialog.setCancelable(true);
            mMyDialog.show();
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMyDialog.dismiss();
                }
            });
            tv_jieshu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMyDialog.dismiss();
                }
            });

        }
    }

    @Override
    public void getCurrentInquiryRecordSuccess(CurrentInquiryRecordBean currentInquiryRecordBean) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getId(Integer id){
        BasePresenter presenter = getPresenter();
        if (presenter != null) {
            ((ICoolor_DoctorList.IPresenter)presenter).getDoctorList(id,1,0,1,10);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
