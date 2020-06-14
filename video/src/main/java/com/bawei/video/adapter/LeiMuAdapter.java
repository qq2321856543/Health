package com.bawei.video.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bawei.video.R;
import com.bawei.video.R2;
import com.bawei.video.bean.ShiPinLeiMu;

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

public class LeiMuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    Context context;
    List<ShiPinLeiMu.ResultBean> list;
    public LeiMuAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_leimu, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,final int position) {
        ((ViewHolder)holder).name.setText(list.get(position).getName()+"");
        ((ViewHolder)holder).bei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnCliCkItem.onck(list.get(position).getId(),list.get(position).getName());
            }
        });

    }
    OnCliCkItem setOnCliCkItem;
    public void setOnClickItem(OnCliCkItem onClickItem){
        setOnCliCkItem=onClickItem;
    }
    public interface OnCliCkItem{
        void onck(int a, String name);
    }
    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public void setData(List<ShiPinLeiMu.ResultBean> result) {
        list=result;
        notifyDataSetChanged();
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
