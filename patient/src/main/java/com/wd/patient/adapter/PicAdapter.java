package com.wd.patient.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.patient.R;
import com.wd.patient.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.adapter
 * @ClassName: PicAdapter
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/6/7 21:14
 */
public class PicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> mList = new ArrayList<>();

    public PicAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<String> list) {
        mList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.item_write_iv, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Glide.with(mContext).load(mList.get(i)).into(((ViewHolder) viewHolder).writeIv);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.item_write_iv)
        ImageView writeIv;
        @BindView(R2.id.item_write_closeiv)
        ImageView closeIv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
