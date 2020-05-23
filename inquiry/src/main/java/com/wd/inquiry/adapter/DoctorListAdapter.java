package com.wd.inquiry.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.inquiry.R;
import com.wd.inquiry.bean.DoctorListBean;

import java.util.List;

public class DoctorListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<DoctorListBean.ResultBean> list;
    private setOnclick msetOnclick;

    public DoctorListAdapter(Context context, List<DoctorListBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_doctorlist, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Glide.with(context).load(list.get(i).getImagePic()).into(((ViewHolder)viewHolder).iv);
        ((ViewHolder)viewHolder).tv_name.setText(list.get(i).getDoctorName());
        if (list.get(i).getIs()){
            ((ViewHolder)viewHolder).tv_name.setBackgroundColor(0xff3086E9);

        }else {
            ((ViewHolder)viewHolder).tv_name.setBackgroundColor(0xff989898);

        }
        ((ViewHolder)viewHolder).iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msetOnclick.click(list.get(i).getDoctorId());
            }
        });
    }
    public void OnClick(setOnclick setOnclick){
        msetOnclick = setOnclick;
    }
    public interface setOnclick{
        void click(int id);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView tv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
