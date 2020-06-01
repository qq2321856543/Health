package com.wd.wallet.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.wallet.R;
import com.wd.wallet.R2;
import com.wd.wallet.bean.WalletRecordBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/6/1
 * Author: 王冠华
 * Description:
 */
public class WalletRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<WalletRecordBean.ResultBean> list;


    public WalletRecordAdapter(Context context, List<WalletRecordBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_wallet_record, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        WalletRecordBean.ResultBean bean = list.get(i);
        int changeNum = bean.getChangeNum();
        long createTime = bean.getCreateTime();
        Date date = new Date(createTime);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd");
        String format = sd.format(date);
        ((ViewHolder)viewHolder).tvTime.setText(format);
        ((ViewHolder)viewHolder).tvChange.setText(changeNum+"币");
        if(bean.getDirection()==1){
            ((ViewHolder)viewHolder).tvChange.setTextColor(Color.parseColor("#d92109"));
        }else {
            ((ViewHolder)viewHolder).tvChange.setTextColor(Color.parseColor("#3087ea"));
        }
        int type = bean.getType();
        switch (type){
            case 1:
                ((ViewHolder)viewHolder).tvType.setText("签到");
                break;
            case 2:
                ((ViewHolder)viewHolder).tvType.setText("病友圈首评");
                break;
            case 3:
                ((ViewHolder)viewHolder).tvType.setText("首发病友圈");
                break;
            case 4:
                ((ViewHolder)viewHolder).tvType.setText("完善档案");
                break;
            case 5:
                ((ViewHolder)viewHolder).tvType.setText("健康评测");
                break;
            case 6:
                ((ViewHolder)viewHolder).tvType.setText("悬赏消费");
                break;
            case 7:
                ((ViewHolder)viewHolder).tvType.setText("悬赏奖励");
                break;
            case 8:
                ((ViewHolder)viewHolder).tvType.setText("邀请奖励");
                break;
            case 9:
                ((ViewHolder)viewHolder).tvType.setText("问诊消费");
                break;
            case 10:
                ((ViewHolder)viewHolder).tvType.setText("问诊收入");
                break;
            case 11:
                ((ViewHolder)viewHolder).tvType.setText("观看资讯");
                break;
            case 12:
                ((ViewHolder)viewHolder).tvType.setText("送礼物");
                break;
            case 13:
                ((ViewHolder)viewHolder).tvType.setText("绑定身份证");
                break;
            case 14:
                ((ViewHolder)viewHolder).tvType.setText("绑定银行卡");
                break;
            case 15:
                ((ViewHolder)viewHolder).tvType.setText("充值");
                break;
            case 16:
                ((ViewHolder)viewHolder).tvType.setText("提现");
                break;
            case 17:
                ((ViewHolder)viewHolder).tvType.setText("购买健康视频");
                break;
                default:
                    break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_item_record_type)
        TextView tvType;
        @BindView(R2.id.tv_item_record_time)
        TextView tvTime;
        @BindView(R2.id.tv_item_record_change)
        TextView tvChange;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
