package com.wd.inquiry.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.inquiry.R;
import com.wd.inquiry.R2;
import com.wd.inquiry.bean.DoctorInfoBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoctorInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    private List<DoctorInfoBean.ResultBean.CommentListBean> list=new ArrayList<>();

    public DoctorInfoAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<DoctorInfoBean.ResultBean.CommentListBean> mlist){
        list = mlist;
        notifyDataSetChanged();
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
        Date date = new Date(list.get(i).getCommentTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        String[] split = format.split("-");
        ((ViewHolder)viewHolder).sdv.setImageURI(Uri.parse(list.get(i).getHeadPic()));
        ((ViewHolder)viewHolder).tv_name.setText(list.get(i).getNickName());
        ((ViewHolder)viewHolder).tv_pinglunsize.setText(list.get(i).getContent());
        ((ViewHolder)viewHolder).tv_shijian.setText(split[0]+"."+split[1]+"."+split[2]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView sdv;
        private final TextView tv_name;
        @BindView(R2.id.tv_shijian)
        TextView tv_shijian;
        @BindView(R2.id.tv_pinglunsize)
        TextView tv_pinglunsize;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            sdv = itemView.findViewById(R.id.sdv);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
