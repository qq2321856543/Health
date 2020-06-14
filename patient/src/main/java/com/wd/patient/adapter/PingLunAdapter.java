package com.wd.patient.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.patient.R;
import com.wd.patient.R2;
import com.wd.patient.bean.PatientCommentBean;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

public class PingLunAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<PatientCommentBean.ResultBean> list = new ArrayList<>();


    public PingLunAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_ping_lun, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).name.setText(list.get(position).getNickName()+"");
        ((ViewHolder) holder).neirong.setText(list.get(position).getContent()+"");
        ((ViewHolder) holder).shang_tx.setText(list.get(position).getSupportNum()+"");
        ((ViewHolder) holder).xia_tx.setText(list.get(position).getOpposeNum()+"");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String a = sf.format(list.get(position).getCommentTime());
        ((ViewHolder) holder).riqi.setText(a+"");
        Glide.with(context).load(list.get(position).getHeadPic()).into(((ViewHolder) holder).zuoImg);
        int whetherDoctor = list.get(position).getWhetherDoctor();
        if(whetherDoctor==1){
            Glide.with(context).load(R.mipmap.tingzhenqi).into(((ViewHolder) holder).youShangImg);
        }
//        ((ViewHolder)holder).dibu_bei.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                setOnClickBei.onck();
//            }
//        });
        ((ViewHolder)holder).zuoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String headPic = list.get(position).getHeadPic();
                int commentUserId = list.get(position).getCommentUserId();
                String nickName = list.get(position).getNickName();
                setOnClickTou.onck(headPic,commentUserId,nickName);
            }
        });
//        ((ViewHolder)holder).shang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                clickShang.onck(list.get(position).getCommentId());
//            }
//        });

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }
//    public OnClickBei setOnClickBei;
//    public void setOnclck(OnClickBei onClickBei){
//        setOnClickBei=onClickBei;
//    }
//    public interface OnClickBei{
//        void onck();
//    }


    public OnClickTou setOnClickTou;
    public void setOnclckTou(OnClickTou onClickTou){
        setOnClickTou=onClickTou;
    }
    public interface OnClickTou{
        void onck(String a, int b, String name);
    }
    public void setData(List<PatientCommentBean.ResultBean> result) {
        list = result;
        notifyDataSetChanged();
    }

//    public OnClickShang clickShang;
//    public void setOnClickShang(OnClickShang onClickShang){
//        clickShang=onClickShang;
//    }
//    public interface OnClickShang{
//        void onck(int id);
//    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.zuo_img)
        ImageView zuoImg;
        @BindView(R2.id.name)
        TextView name;
        @BindView(R2.id.you_shang_img)
        ImageView youShangImg;
        @BindView(R2.id.neirong)
        TextView neirong;
        @BindView(R2.id.riqi)
        TextView riqi;
        @BindView(R2.id.shang)
        LinearLayout shang;
        @BindView(R2.id.xia)
        LinearLayout xia;
        @BindView(R2.id.shang_tx)
        TextView shang_tx;
        @BindView(R2.id.xia_tx)
        TextView xia_tx;
        @BindView(R2.id.dibu_bei)
        LinearLayout dibu_bei;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
