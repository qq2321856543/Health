package com.wd.inquiry.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.util.util.SPUtils;
import com.wd.inquiry.R;
import com.wd.inquiry.bean.MessageBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    private ArrayList<MessageBean> list = new ArrayList<>();


    public MessageAdapter(Context context) {
        this.context = context;
    }
    public void setData(ArrayList<MessageBean> mlist){
        list = mlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i==1){
            //接收
            View view = View.inflate(context, R.layout.item_messageone, null);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }else {
            View view = View.inflate(context, R.layout.item_messagetwo, null);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (list.get(i).getType()==1){

            ((ViewHolder)viewHolder).tv.setText(list.get(i).getCloseMessage());
            if (list.get(i).getTime()!=null){
                Date date = new Date(list.get(i).getTime());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = dateFormat.format(date);
                String[] split = format.split("-");
                ((ViewHolder)viewHolder).tv_shijian.setText(split[0]+"-"+split[1]+"-"+split[2]);
            }
        }else {
            ((ViewHolder)viewHolder).tv.setText(list.get(i).getSendMessage());
            String head = SPUtils.getString(context, SPUtils.USERINFO_NAME, "head");
            ((ViewHolder)viewHolder).iv.setImageURI(Uri.parse(head));
            if (list.get(i).getTime()!=null){
                Date date = new Date(list.get(i).getTime());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = dateFormat.format(date);
                String[] split = format.split("-");
                ((ViewHolder)viewHolder).tv_shijian.setText(split[0]+"-"+split[1]+"-"+split[2]);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {

        return list.get(position).getType();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv;
        private final TextView tv_shijian;
        private final SimpleDraweeView iv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            tv_shijian = itemView.findViewById(R.id.tv_shijian);
        }
    }
}
