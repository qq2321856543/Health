package com.wd.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.bean.HomeDrugsKnowledgeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/26
 * Author: 王冠华
 * Description:
 */
public class HomeDrugRightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomeDrugsKnowledgeBean.ResultBean> list;



    public HomeDrugRightAdapter(Context context, List<HomeDrugsKnowledgeBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_home_drug_right, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        HomeDrugsKnowledgeBean.ResultBean bean = list.get(i);
        String name = bean.getName();
        int id = bean.getId();
        String picture = bean.getPicture();
        Uri uri = Uri.parse(picture);
        ((ViewHolder) viewHolder).tvDrugItem.setText(name);
        ((ViewHolder) viewHolder).ivDrugItem.setImageURI(uri);
        ((ViewHolder) viewHolder).rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                monClick.setClick(id,name);
            }
        });
    }

    private onClick monClick;

    public interface onClick {
        void setClick(int id,String name);
    }

    public void Click(onClick onClick) {
        monClick = onClick;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.iv_drug_item)
        SimpleDraweeView ivDrugItem;
        @BindView(R2.id.tv_drug_item)
        TextView tvDrugItem;
        @BindView(R2.id.rl)
        RelativeLayout rl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
