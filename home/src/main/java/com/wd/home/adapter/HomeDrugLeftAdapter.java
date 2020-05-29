package com.wd.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.bean.HomeFindDrugsCategoryBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/26
 * Author: 王冠华
 * Description:
 */
public class HomeDrugLeftAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomeFindDrugsCategoryBean.ResultBean> list;



    public HomeDrugLeftAdapter(Context context, List<HomeFindDrugsCategoryBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_home_drug_left, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        HomeFindDrugsCategoryBean.ResultBean bean = list.get(i);
        String name = bean.getName();
        int id = bean.getId();
        ((ViewHolder) viewHolder).tv.setText(name);
        boolean check = bean.isCheck();
        if(check){
            ((ViewHolder) viewHolder).rl.setBackgroundColor(Color.WHITE);
        }else {
            ((ViewHolder) viewHolder).rl.setBackgroundColor(Color.parseColor("#F0F0F0"));

        }
        ((ViewHolder) viewHolder).rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monClick.setClick(id,i);
            }
        });
    }

    private onClick monClick;

    public interface onClick {
        void setClick(int id,int position);
    }

    public void Click(onClick onClick) {
        monClick = onClick;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_drug_item)
        TextView tv;
        @BindView(R2.id.rl)
        RelativeLayout rl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
