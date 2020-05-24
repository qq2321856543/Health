package com.wd.patient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wd.patient.R;
import com.wd.patient.R2;
import com.wd.patient.bean.BingYouQuanBean;
import com.wd.patient.bean.SearchBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<SearchBean.ResultBean> mList = new ArrayList<>();

    public SearchAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<SearchBean.ResultBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.item_apdapter_bottom, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder) viewHolder).name.setText(mList.get(i).getTitle());
        ((ViewHolder)viewHolder).content.setText(mList.get(i).getDetail());
        ((ViewHolder)viewHolder).collect.setText("收藏 "+String.valueOf(mList.get(i).getCollectionNum()));
        ((ViewHolder)viewHolder).jianyi.setText("建议 "+String.valueOf(mList.get(i).getCommentNum()));
        String time = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ").format(new Date(mList.get(i).getReleaseTime()));
        ((ViewHolder)viewHolder).time.setText(time);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.name)
        TextView name;
        @BindView(R2.id.time)
        TextView time;
        @BindView(R2.id.content)
        TextView content;
        @BindView(R2.id.collect)
        TextView collect;
        @BindView(R2.id.jianyi)
        TextView jianyi;
        @BindView(R2.id.bottom_bei)
        RelativeLayout bottomBei;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
