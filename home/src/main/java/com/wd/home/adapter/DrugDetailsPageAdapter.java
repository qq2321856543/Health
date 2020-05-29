package com.wd.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import com.wd.home.R;
import com.wd.home.R2;

import com.wd.home.bean.HomeDrugsDetailBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrugDetailsPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<HomeDrugsDetailBean.ResultBean> list;

    public DrugDetailsPageAdapter(Context context, ArrayList<HomeDrugsDetailBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.drugdetailspageadapter, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeDrugsDetailBean.ResultBean resultBean = list.get(position);
        //批准文号
        String approvalNumber = resultBean.getApprovalNumber();
        //用药成分
        String component = resultBean.getComponent();
        //功能主治
        String effect = resultBean.getEffect();
        //注意事项
        String mindMatter = resultBean.getMindMatter();
        //包装规格
        String packing = resultBean.getPacking();
        //药品性状
        String shape = resultBean.getShape();
        //不良反应
        String sideEffects = resultBean.getSideEffects();
        //贮藏
        String storage = resultBean.getStorage();
        //忌讳
        String taboo = resultBean.getTaboo();
        //用法用量
        String usage = resultBean.getUsage();
        ((ViewHolder)holder).tv_yycf.setText(component);
        ((ViewHolder)holder).tv_yyjh.setText(taboo);
        ((ViewHolder)holder).tv_gnzz.setText(effect);
        ((ViewHolder)holder).tv_yfyl.setText(usage);
        ((ViewHolder)holder).tv_ypxz.setText(shape);
        ((ViewHolder)holder).tv_bzgg.setText(packing);
        ((ViewHolder)holder).tv_blfy.setText(sideEffects);
        ((ViewHolder)holder).tv_cxtj.setText(storage);
        ((ViewHolder)holder).tv_zysx.setText(mindMatter);
        ((ViewHolder)holder).tv_pzwh.setText(approvalNumber);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R2.id.tv_yycf)
        TextView tv_yycf;
        @BindView(R2.id.tv_yyjh)
        TextView tv_yyjh;
        @BindView(R2.id.tv_gnzz)
        TextView tv_gnzz;
        @BindView(R2.id.tv_yfyl)
        TextView tv_yfyl;
        @BindView(R2.id.tv_ypxz)
        TextView tv_ypxz;
        @BindView(R2.id.tv_bzgg)
        TextView tv_bzgg;
        @BindView(R2.id.tv_blfy)
        TextView tv_blfy;
        @BindView(R2.id.tv_cxtj)
        TextView tv_cxtj;
        @BindView(R2.id.tv_zysx)
        TextView tv_zysx;
        @BindView(R2.id.tv_pzwh)
        TextView tv_pzwh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
