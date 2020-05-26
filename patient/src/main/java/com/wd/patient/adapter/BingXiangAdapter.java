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
import com.wd.patient.bean.BingXiangBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.adapter
 * @ClassName: BingXiangAdapter
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/5/21 22:59
 */
public class BingXiangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<BingXiangBean.ResultBean> mList = new ArrayList<>();

    public BingXiangAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<BingXiangBean.ResultBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.item_ke, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
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
