package com.wd.inquiry.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.inquiry.R;
import com.wd.inquiry.bean.FindDepartmentBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class FindDepartmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<FindDepartmentBean.ResultBean> list;
    private Onclick monclick;

    public FindDepartmentAdapter(Context context, List<FindDepartmentBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_finddepartment, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder)viewHolder).tv_name.setText(list.get(i).getDepartmentName());
        if (list.get(i).getIs()){
            ((ViewHolder)viewHolder).tv_name.setTextColor(0xff408FEB);
            EventBus.getDefault().postSticky(list.get(i).getId());
        }else {
            ((ViewHolder)viewHolder).tv_name.setTextColor(0xffC3C3C3);

        }
        ((ViewHolder)viewHolder).tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monclick.click(list.get(i).getId(),i);
            }
        });

    }

    public void setOnclick(Onclick onclick){
        monclick = onclick;
    }
    public interface Onclick{
        void click(int id,int postion);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
        }
    }
}
