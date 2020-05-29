package com.wd.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import com.wd.home.R;
import com.wd.home.R2;

import com.wd.home.bean.HomeDiseaseDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SymptomDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
     List<HomeDiseaseDetailBean.ResultBean> list;

    public SymptomDetailsAdapter(Context context, List<HomeDiseaseDetailBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.symptomdetailsadapter, null);
        ViweHoder viweHoder = new ViweHoder(inflate);
        return viweHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeDiseaseDetailBean.ResultBean resultBean = list.get(position);
        //忌讳
        String benefitTaboo = resultBean.getBenefitTaboo();
        //中医治疗
        String chineseMedicineTreatment = resultBean.getChineseMedicineTreatment();
        //病理
        String pathology = resultBean.getPathology();
        //症状
        String symptom = resultBean.getSymptom();
        //西医
        String westernMedicineTreatment = resultBean.getWesternMedicineTreatment();
        ((ViweHoder)holder).tv_bl.setText(pathology);
        ((ViweHoder)holder).tv_bz.setText(symptom);
        ((ViweHoder)holder).tv_yyj.setText(benefitTaboo);
        if(westernMedicineTreatment==null){
            ((ViweHoder)holder).tv_xyzl.setText("无");
        }else{
            ((ViweHoder)holder).tv_xyzl.setText(westernMedicineTreatment);

        }
        if(chineseMedicineTreatment==null){
            ((ViweHoder)holder).tv_zyzl.setText("无");
        }else{
            ((ViweHoder)holder).tv_zyzl.setText(chineseMedicineTreatment);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViweHoder extends RecyclerView.ViewHolder{
        @BindView(R2.id.tv_bl)
        TextView tv_bl;
        @BindView(R2.id.tv_bz)
        TextView tv_bz;
        @BindView(R2.id.tv_yyj)
        TextView tv_yyj;
        @BindView(R2.id.tv_xyzl)
        TextView tv_xyzl;
        @BindView(R2.id.tv_zyzl)
        TextView tv_zyzl;
        public ViweHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
