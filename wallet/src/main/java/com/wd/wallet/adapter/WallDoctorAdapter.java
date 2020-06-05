package com.wd.wallet.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.wallet.R;
import com.wd.wallet.R2;
import com.wd.wallet.bean.WalletDoctorBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/6/5
 * Author: 王冠华
 * Description:
 */
public class WallDoctorAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<WalletDoctorBean.ResultBean> list;


    public WallDoctorAdapter(Context context, List<WalletDoctorBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_doctor, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        WalletDoctorBean.ResultBean bean = list.get(i);
        int num = bean.getServerNum();
        String doctorName = bean.getDoctorName();
        String imagePic = bean.getImagePic();
        Uri uri = Uri.parse(imagePic);
        String praise = bean.getPraise();
        String hospital = bean.getInauguralHospital();
        String jobTitle = bean.getJobTitle();
        ((ViewHolder)viewHolder).tvDoctorName.setText(doctorName);
        ((ViewHolder)viewHolder).ivDoctor.setImageURI(uri);
        ((ViewHolder)viewHolder).tvDoctorNum.setText(num+"");
        ((ViewHolder)viewHolder).tvDoctorGood.setText(praise);
        ((ViewHolder)viewHolder).tvDoctorHospital.setText(hospital);
        ((ViewHolder)viewHolder).tvDoctorJob.setText(jobTitle);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.iv_doctor)
        SimpleDraweeView ivDoctor;
        @BindView(R2.id.tv_doctor_name)
        TextView tvDoctorName;
        @BindView(R2.id.tv_doctor_job)
        TextView tvDoctorJob;
        @BindView(R2.id.tv_doctor_hospital)
        TextView tvDoctorHospital;
        @BindView(R2.id.tv_doctor_good)
        TextView tvDoctorGood;
        @BindView(R2.id.tv_doctor_num)
        TextView tvDoctorNum;
        @BindView(R2.id.bt_doctor)
        Button btDoctor;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
