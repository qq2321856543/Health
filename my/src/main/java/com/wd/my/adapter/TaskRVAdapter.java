package com.wd.my.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wd.my.R;
import com.wd.my.R2;
import com.wd.my.bean.TaskBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ProjectName: WeiDuHealth
 * @Package: com.wd.minemodule.adapter
 * @ClassName: CollectionInfoRVAdapter
 * @Description:
 * @Author: 李泽晋
 * @CreateDate: 2020.5.23 0023 18:17
 * @UpdateUser:
 * @UpdateDate: 2020.5.23 0023 18:17
 * @UpdateRemark:
 * @Version: 1.0
 */
public class TaskRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "TaskRVAdapter";
    private List<TaskBean.ResultBean> mList = new ArrayList<>();
    private Context                   mContext;
    private PositionCallBack          mPositionCallBack;
    String nameSpace = "http://schemas.android.com/apk/res-auto";
    public TaskRVAdapter(List<TaskBean.ResultBean> list, Context context) {
        mList.addAll(list);
        for (int i = 0; i < mList.size(); i++) {
            Log.d(TAG, "TaskRVAdapter: "+ mList.get(i).getTaskName());
        }
        mContext = context;
    }

    public String changeTime(long dateLong) {
        //时间转换
        Date date = new Date(dateLong);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = dateFormat.format(date);
        return format;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = View.inflate(mContext, R.layout.rv_activity_task_item, null);
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_activity_task_item, parent, false);
        IViewHolder1 viewHolder1 = new IViewHolder1(view);
        return viewHolder1;
    }

    //接口回调
    public interface PositionCallBack {
        void getPosition(int position, String flag);
    }

    public void getPosition(PositionCallBack callBack) {
        mPositionCallBack = callBack;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        TaskBean.ResultBean resultBean = mList.get(position);
        Log.d(TAG, "onBindViewHolder: "+resultBean.getTaskName());
        ((IViewHolder1) holder).tvTaskItemTaskname.setText(resultBean.getTaskName());
        if(resultBean.getReward()<2){
            ((IViewHolder1) holder).tvTaskItemHbi.setVisibility(View.GONE);
        }else{
            ((IViewHolder1) holder).tvTaskItemHbi.setText("+"+resultBean.getReward()+"H币");
        }
        int whetherFinish = resultBean.getWhetherFinish();
        if(whetherFinish==1){
            ((IViewHolder1) holder).butTaskItem.setText("已完成");
            int whetherReceive = resultBean.getWhetherReceive();
            if(whetherReceive==1){
                ((IViewHolder1) holder).butTaskItem.setText("去领取");
            }else if(whetherReceive==2){
                ((IViewHolder1) holder).butTaskItem.setText("已领取");
            }else{
                ((IViewHolder1) holder).butTaskItem.setText("去完成");
            }
        }else if(whetherFinish==2){
            ((IViewHolder1) holder).butTaskItem.setText("去完成");
        }
        //
        final String flag = ((IViewHolder1) holder).butTaskItem.getText().toString();
        ((IViewHolder1) holder).butTaskItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPositionCallBack.getPosition(position,flag);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class IViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_task_item_taskname)
        TextView tvTaskItemTaskname;
        @BindView(R2.id.but_task_item)
        Button   butTaskItem;
        @BindView(R2.id.tv_task_item_hbi)
        TextView tvTaskItemHbi;

        public IViewHolder1(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
