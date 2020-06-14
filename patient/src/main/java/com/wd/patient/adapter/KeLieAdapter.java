package com.wd.patient.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.patient.R;
import com.wd.patient.R2;
import com.wd.patient.bean.BingXiangBean;
import com.wd.patient.bean.KeLieBean;

import org.greenrobot.eventbus.EventBus;

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
public class KeLieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<KeLieBean.ResultBean> mList = new ArrayList<>();

    public KeLieAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<KeLieBean.ResultBean> list) {
        mList = list;
        notifyDataSetChanged();
    }
    private int text;
    public void setTextChange(int b) {
        text = b;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.item_ke, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (text == mList.get(i).getId()) {
            ((ViewHolder) viewHolder).name.setTextColor(Color.BLUE);
        } else {
            ((ViewHolder) viewHolder).name.setTextColor(Color.GRAY);
        }

        ((ViewHolder) viewHolder).name.setText(mList.get(i).getDepartmentName());
        ((ViewHolder)viewHolder).bei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(mList.get(i).getId());
            }
        });
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
