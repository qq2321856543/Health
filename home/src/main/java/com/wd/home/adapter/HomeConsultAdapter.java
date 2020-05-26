package com.wd.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
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
public class HomeConsultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomeDepartmentBean.ResultBean> list;


    public HomeConsultAdapter(Context context, List<HomeDepartmentBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_home_consult, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        HomeDepartmentBean.ResultBean bean = list.get(i);
        String name = bean.getDepartmentName();
        int id = bean.getId();
        String pic = bean.getPic();
        Uri uri = Uri.parse(pic);
        ((ViewHolder) viewHolder).iv.setImageURI(uri);
        ((ViewHolder) viewHolder).name.setText(name);
        ((ViewHolder) viewHolder).iv.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R2.id.iv_home_item_consult)
        SimpleDraweeView iv;
        @BindView(R2.id.tv_home_item_name)
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
