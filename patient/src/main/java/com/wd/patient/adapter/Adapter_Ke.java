package com.wd.patient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.wd.patient.R;
import com.wd.patient.R2;
import com.wd.patient.bean.KeLieBean;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class Adapter_Ke extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<KeLieBean.ResultBean> list;


    public Adapter_Ke(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_select_office, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,final int position) {
        ((ViewHolder)holder).name.setText(list.get(position).getDepartmentName()+"");
        ((ViewHolder)holder).bei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = list.get(position).getId();
                onck.onck(id,list.get(position).getDepartmentName()+"");
            }
        });

    }
    public setOnck onck;
    public void setOnClick(setOnck seonck){
        onck=seonck;
    }

    public void setData(List<KeLieBean.ResultBean> result) {
        list=result;
    }



    public interface setOnck{
        void onck(int a, String name);
    }
    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.name)
        TextView name;
        @BindView(R2.id.bei)
        LinearLayout bei;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
