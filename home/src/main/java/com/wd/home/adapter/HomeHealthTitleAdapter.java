package com.wd.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.bean.HomePlateListBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/22
 * Author: 王冠华
 * Description:
 */
public class HomeHealthTitleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomePlateListBean.ResultBean> list;



    public HomeHealthTitleAdapter(Context context, List<HomePlateListBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.item_home_health_tilte, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        HomePlateListBean.ResultBean bean = list.get(i);
        String name = bean.getName();
        ((ViewHolder) viewHolder).tv.setText(name);

        boolean check = bean.isCheck();
        if (check) {
            ((ViewHolder) viewHolder).tv.setTextColor(Color.parseColor("#3087ea"));
        } else {
            ((ViewHolder) viewHolder).tv.setTextColor(Color.BLACK);
        }
        ((ViewHolder) viewHolder).rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = bean.getId();
                monClick.setClick(id, name, i);
                EventBus.getDefault().postSticky(bean);
            }
        });
    }

    private onClick monClick;

    public interface onClick {
        void setClick(int id, String name, int position);
    }

    public void Click(onClick onClick) {
        monClick = onClick;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_item_title)
        TextView tv;
        @BindView(R2.id.rl)
        RelativeLayout rl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
