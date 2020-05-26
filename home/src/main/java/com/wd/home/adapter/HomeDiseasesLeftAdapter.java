package com.wd.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.bean.HomeDepartmentBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/26
 * Author: 王冠华
 * Description:
 */
public class HomeDiseasesLeftAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomeDepartmentBean.ResultBean> list;


    public HomeDiseasesLeftAdapter(Context context, List<HomeDepartmentBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_home_diseases_left, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        HomeDepartmentBean.ResultBean bean = list.get(i);
        int id = bean.getId();
        ((ViewHolder)viewHolder).tv.setText(bean.getDepartmentName());
        ((ViewHolder)viewHolder).tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monClick.setClick(id);
            }
        });
    }
    private onClick monClick;
    public interface onClick{
        void setClick(int id);
    }
    public void Click(onClick onClick){
        monClick=onClick;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_diseases_item)
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
