package com.wd.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
    private int num;
    private String[] split;

    public HomeHealthListAdapter(Context context, List<HomeFindListBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        num=i;
        if(i==0){
            View view = View.inflate(context, R.layout.item_home_health_two, null);
            ViewHoder1 viewHoder = new ViewHoder1(view);
            return viewHoder;
        }else if(i==1){
            View view = View.inflate(context, R.layout.item_home_health_two, null);
            ViewHoder1 viewHoder = new ViewHoder1(view);
            return viewHoder;
        }else{
            View view = View.inflate(context, R.layout.item_home_health_one, null);
            ViewHoder viewHoder = new ViewHoder(view);
            return viewHoder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {

        final HomeFindListBean.ResultBean resultBean = list.get(i);
        if(i==0){
            ((ViewHoder1)holder).tvHomeItemName.setText(resultBean.getTitle());
            ((ViewHoder1)holder).tvHomeItemAuthor.setText(resultBean.getSource());
            String thumbnail = resultBean.getThumbnail();
            split = thumbnail.split(";");
            String s = split[0];
            String s1 = split[1];
            String s2 = split[2];
            Uri parse = Uri.parse(s);
            Uri parse1 = Uri.parse(s1);
            Uri parse2 = Uri.parse(s2);
            ((ViewHoder1)holder).ivOne.setImageURI(parse);
            ((ViewHoder1)holder).ivTwo.setImageURI(parse1);
            ((ViewHoder1)holder).ivThree.setImageURI(parse2);
            long releaseTime = resultBean.getReleaseTime();
            Date date = new Date(releaseTime);
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sd.format(date);
            ((ViewHoder1)holder).tvHomeItemTime.setText(format);
            ((ViewHoder1)holder).rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(monClick!=null){
                        monClick.setClick(resultBean.getId(),resultBean.getThumbnail());
                    }
                }
            });
        }else if(i==1){
            String thumbnail = resultBean.getThumbnail();
            split = thumbnail.split(";");
//            String s = split[0];
//            String s1 = split[1];
//            String s2 = split[2];
//            Uri parse = Uri.parse(s);
//            Uri parse1 = Uri.parse(s1);
//            Uri parse2 = Uri.parse(s2);
//            ((ViewHoder1)holder).ivOne.setImageURI(parse);
//            ((ViewHoder1)holder).ivTwo.setImageURI(parse1);
//            ((ViewHoder1)holder).ivThree.setImageURI(parse2);
            ((ViewHoder1)holder).tvHomeItemName.setText(resultBean.getTitle());
            ((ViewHoder1)holder).tvHomeItemAuthor.setText(resultBean.getSource());

            long releaseTime = resultBean.getReleaseTime();
            Date date = new Date(releaseTime);
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sd.format(date);
            ((ViewHoder1)holder).tvHomeItemTime.setText(format);
            ((ViewHoder1)holder).rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(monClick!=null){
                        monClick.setClick(resultBean.getId(),resultBean.getThumbnail());
                    }
                }
            });
        }else{
            String thumbnail = resultBean.getThumbnail();
            split = thumbnail.split(";");
            String s = split[0];
            Uri parse = Uri.parse(s);
            ((ViewHoder)holder).iv.setImageURI(parse);
            ((ViewHoder)holder).name.setText(resultBean.getTitle());
            ((ViewHoder)holder).author.setText(resultBean.getSource());

            long releaseTime = resultBean.getReleaseTime();
            Date date = new Date(releaseTime);
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sd.format(date);
            ((ViewHoder)holder).time.setText(format);
            ((ViewHoder)holder).rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(monClick!=null){
                        monClick.setClick(resultBean.getId(),resultBean.getThumbnail());
                    }
                }
            });
        }


    }

    @Override
    public int getItemViewType(int position) {
        HomeFindListBean.ResultBean resultBean = list.get(position);
        String thumbnail = resultBean.getThumbnail();
        String[] split = thumbnail.split(";");
        Log.i("xxx","sp"+split.length);
        if(split.length==0){
            return 0;
        }else if(split.length>1){
            return 1;
        }else{
            return 2;
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private onClick monClick;

    public interface onClick {
        void setClick(int id, String img);
    }

    public void Click(onClick onClick) {
        monClick = onClick;
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        @BindView(R2.id.iv_home_item)
        SimpleDraweeView iv;
        @BindView(R2.id.tv_home_item_name)
        TextView name;
        @BindView(R2.id.tv_home_item_author)
        TextView author;
        @BindView(R2.id.tv_home_item_time)
        TextView time;
        @BindView(R2.id.rl)
        RelativeLayout rl;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewHoder1 extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_home_item_name)
        TextView tvHomeItemName;
        @BindView(R2.id.iv_home_item)
        SimpleDraweeView ivOne;
        @BindView(R2.id.iv_home_item2)
        SimpleDraweeView ivTwo;
        @BindView(R2.id.iv_home_item3)
        SimpleDraweeView ivThree;
        @BindView(R2.id.tv_home_item_author)
        TextView tvHomeItemAuthor;
        @BindView(R2.id.tv_home_item_time)
        TextView tvHomeItemTime;
        @BindView(R2.id.rl)
        RelativeLayout rl;

        public ViewHoder1(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
