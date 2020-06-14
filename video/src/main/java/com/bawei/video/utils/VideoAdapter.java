package com.bawei.video.utils;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bawei.video.R;
import com.bawei.video.bean.ShiPinBean;

import java.util.List;


import cn.jzvd.JZVideoPlayerStandard;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {
    Context mContext;
    List<ShiPinBean.ResultBean> result;

    public VideoAdapter(Context mContext, List<ShiPinBean.ResultBean> result) {
        this.mContext = mContext;
        this.result = result;
    }
    String name;
    public void setData(String nme) {
        name=nme;
        notifyDataSetChanged();
    }

    //为RecyclerView的Item添加监听
    public interface OnItemClickListener {
        void onItemClick(int position, String type, View view, View view1, View view2);
    }

    public OnItemClickListener mOnItemClickListerer;

    public void setOnItemClickListerer(OnItemClickListener listerer) {
        this.mOnItemClickListerer = listerer;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.adapter_play_video, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.jzVideo.setUp(String.valueOf(result.get(position).getOriginalUrl()), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
        //隐藏全屏按钮、返回按钮
        holder.jzVideo.fullscreenButton.setVisibility(View.GONE);
        holder.jzVideo.backButton.setVisibility(View.GONE);

        if(result.get(position).getWhetherBuy()==1){
            holder.iv_heart.setVisibility(View.GONE);
        }else{
            holder.iv_heart.setVisibility(View.VISIBLE);
        }
        //返回
        holder.ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListerer.onItemClick(position, "back", view, view, view);
            }
        });
        //评论
        holder.iv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListerer.onItemClick(position, "commit", view, view, view);
            }
        });
        holder.tx_name.setText(name+"");
        holder.tv_context.setText(result.get(position).getAbstracts()+"");
        holder.iv_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CkMai.setOnCk(position);
            }
        });
        holder.iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ClickGif!=null){
                    Toast.makeText(mContext, "点击礼物了", Toast.LENGTH_SHORT).show();
                    ClickGif.onck();
                }

            }
        });

    }
    public onCkMai CkMai;
    public void setOnCkMai(onCkMai onCkMai){
        CkMai=onCkMai;
    }
    public interface onCkMai{
        void setOnCk(int a);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }


    public OnClickGif ClickGif;
    public void setOnClickGif(OnClickGif onClickGif){
        ClickGif=onClickGif;
    }
    public interface OnClickGif{
        void onck();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public JZVideoPlayerStandard jzVideo;
        LinearLayout ll_back;
        ImageView iv_commit;
        private final TextView tx_name;
        private final TextView tv_context;
        private final ImageView iv_heart;
        private final ImageView iv_gift;
        private final ImageView iv_share;

        public ViewHolder(View itemView) {
            super(itemView);
            ll_back = itemView.findViewById(R.id.ll_back);
            iv_commit = itemView.findViewById(R.id.iv_commit);
            tx_name = itemView.findViewById(R.id.tv_uid);
            jzVideo = itemView.findViewById(R.id.jzVideo);
            tv_context = itemView.findViewById(R.id.tv_context);
            iv_heart = itemView.findViewById(R.id.iv_heart);
            iv_gift = itemView.findViewById(R.id.iv_gift);
            iv_share = itemView.findViewById(R.id.iv_share);
        }
    }


}
