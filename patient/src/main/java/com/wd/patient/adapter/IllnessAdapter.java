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
import com.wd.patient.bean.IllnessBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: Health
 * @Package: com.wd.patient.adapter
 * @ClassName: IllnessAdapter
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/6/7 11:05
 */
public class IllnessAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<IllnessBean.ResultBean> mList = new ArrayList<>();

    public IllnessAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<IllnessBean.ResultBean> list) {
        mList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.item_select, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder)viewHolder).name.setText(mList.get(i).getName());
        ((ViewHolder)viewHolder).bei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetName.getName(mList.get(i).getName());
            }
        });
    }

    private getName mGetName;

    public void getNameCallBack(getName getName) {
        mGetName = getName;
    }
    public interface getName {
        void getName(String name);
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
