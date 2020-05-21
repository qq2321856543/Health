package com.wd.inquiry.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.common.base.util.Base.BaseFragment;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.inquiry.R;
import com.wd.inquiry.R2;
import com.wd.inquiry.activity.SpeakActivity;
import com.wd.inquiry.bean.DoctorListBean;
import com.wd.inquiry.icoolor.ICoolor_DoctorList;
import com.wd.inquiry.presenter.Presenter_DoctorList;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;

public class Fragment_Comprehensive extends BaseFragment implements ICoolor_DoctorList.IView {
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

    @Override
    protected BasePresenter initPresenter() {
        return new Presenter_DoctorList(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_comprehensive;
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
            ((ICoolor_DoctorList.IPresenter)presenter).getDoctorList(7,1,0,1,10);
        }
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SpeakActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void getDoctorListSuccess(DoctorListBean doctorListBean) {
        List<DoctorListBean.ResultBean> result = doctorListBean.getResult();
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
