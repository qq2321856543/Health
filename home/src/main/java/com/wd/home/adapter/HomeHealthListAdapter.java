package com.wd.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.bean.HomeFindListBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Time: 2020/5/22
 * Author: 王冠华
 * Description:
 */
public class HomeHealthListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomeFindListBean.ResultBean> list;


    public HomeHealthListAdapter(Context context, List<HomeFindListBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int type = getItemViewType(i);
        if (type == 0) {
            View view = View.inflate(context, R.layout.item_home_health_one, null);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        } else if (type == 1) {
            View view = View.inflate(context, R.layout.item_home_health_two, null);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        } else {
            View view = View.inflate(context, R.layout.item_home_health_two, null);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        HomeFindListBean.ResultBean bean = list.get(i);
        long releaseTime = bean.getReleaseTime();
        String title = bean.getTitle();
        String source = bean.getSource();
        String img = bean.getThumbnail();

        Uri uri = Uri.parse(img);
        Date date = new Date(releaseTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        ((ViewHolder)viewHolder).iv.setImageURI(uri);
        ((ViewHolder)viewHolder).name.setText(title);
        ((ViewHolder)viewHolder).author.setText(source);
        ((ViewHolder)viewHolder).time.setText(format);
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.iv_home_item)
        SimpleDraweeView iv;
        @BindView(R2.id.tv_home_item_name)
        TextView name;
        @BindView(R2.id.tv_home_item_author)
        TextView author;
        @BindView(R2.id.tv_home_item_time)
        TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
