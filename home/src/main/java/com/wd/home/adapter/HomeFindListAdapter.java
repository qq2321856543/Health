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

public class HomeFindListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    int i;
    Context context;
    List<HomeFindListBean.ResultBean> result;


    private String[] split;

    public HomeFindListAdapter(Context context, List<HomeFindListBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @Override
    public int getItemViewType(int position) {
        HomeFindListBean.ResultBean resultBean = result.get(position);
        String thumbnail = resultBean.getThumbnail();
        String[] split = thumbnail.split(";");
        Log.i("xxx", "sp" + split.length);
        if (split.length == 0) {
            return 0;
        } else if (split.length > 1) {
            return 1;
        } else {
            return 2;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        i = viewType;
        if (viewType == 0) {
            View view = View.inflate(context, R.layout.homefindlistadaptertwo, null);
            ViewHoder1 viewHoder = new ViewHoder1(view);
            return viewHoder;
        } else if (viewType == 1) {
            View view = View.inflate(context, R.layout.homefindlistadaptertwo, null);
            ViewHoder1 viewHoder = new ViewHoder1(view);
            return viewHoder;
        } else {
            View view = View.inflate(context, R.layout.homefindlistadapter, null);
            ViewHoder viewHoder = new ViewHoder(view);
            return viewHoder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final HomeFindListBean.ResultBean resultBean = result.get(position);
        if (i == 0) {
            ((ViewHoder1) holder).home_name.setText(resultBean.getTitle());
            ((ViewHoder1) holder).home_yys.setText(resultBean.getSource());
            String thumbnail = resultBean.getThumbnail();
            split = thumbnail.split(";");
            String s = split[0];
            String s1 = split[1];
            String s2 = split[2];
            Uri parse = Uri.parse(s);
            Uri parse1 = Uri.parse(s1);
            Uri parse2 = Uri.parse(s2);
            ((ViewHoder1)holder).line.getBackground().setAlpha(50);
            ((ViewHoder1) holder).home_sdv.setImageURI(parse);
            ((ViewHoder1) holder).home_sdv1.setImageURI(parse1);
            ((ViewHoder1) holder).home_sdv2.setImageURI(parse2);
            long releaseTime = resultBean.getReleaseTime();
            Date date = new Date(releaseTime);
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sd.format(date);
            ((ViewHoder1) holder).home_sj.setText(format);
            ((ViewHoder1) holder).rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (msetOnClick != null) {
                        msetOnClick.getId(resultBean.getId(), resultBean.getThumbnail());
                    }
                }
            });
        } else if (i == 1) {
            String thumbnail = resultBean.getThumbnail();
            split = thumbnail.split(";");
            String s = split[0];
            String s1 = split[1];
            String s2 = split[2];
            Uri parse = Uri.parse(s);
            Uri parse1 = Uri.parse(s1);
            Uri parse2 = Uri.parse(s2);

            ((ViewHoder1) holder).home_sdv.setImageURI(parse);
            ((ViewHoder1) holder).home_sdv1.setImageURI(parse1);
            ((ViewHoder1) holder).home_sdv2.setImageURI(parse2);
            ((ViewHoder1) holder).home_name.setText(resultBean.getTitle());
            ((ViewHoder1) holder).home_yys.setText(resultBean.getSource());
            ((ViewHoder1)holder).line.getBackground().setAlpha(50);
            long releaseTime = resultBean.getReleaseTime();
            Date date = new Date(releaseTime);
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sd.format(date);
            ((ViewHoder1) holder).home_sj.setText(format);
            ((ViewHoder1) holder).rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (msetOnClick != null) {
                        msetOnClick.getId(resultBean.getId(), resultBean.getThumbnail());
                    }
                }
            });
        } else {
            String thumbnail = resultBean.getThumbnail();
            split = thumbnail.split(";");
            String s = split[0];
            Uri parse = Uri.parse(s);
            ((ViewHoder) holder).home_sdv.setImageURI(parse);
            ((ViewHoder) holder).home_name.setText(resultBean.getTitle());
            ((ViewHoder) holder).home_yys.setText(resultBean.getSource());
            ((ViewHoder)holder).line.getBackground().setAlpha(50);
            long releaseTime = resultBean.getReleaseTime();
            Date date = new Date(releaseTime);
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sd.format(date);
            ((ViewHoder) holder).home_sj.setText(format);
            ((ViewHoder) holder).rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (msetOnClick != null) {
                        msetOnClick.getId(resultBean.getId(), resultBean.getThumbnail());
                    }
                }
            });
        }

    }

    public SetOnClick msetOnClick;

    public void setOnClick(SetOnClick setOnClick) {
        msetOnClick = setOnClick;
    }

    public interface SetOnClick {
        void getId(int id, String img);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        @BindView(R2.id.home_sdv)
        SimpleDraweeView home_sdv;
        @BindView(R2.id.home_name)
        TextView home_name;
        @BindView(R2.id.home_yys)
        TextView home_yys;
        @BindView(R2.id.home_sj)
        TextView home_sj;
        @BindView(R2.id.rl)
        RelativeLayout rl;
        @BindView(R2.id.line)
        View line;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewHoder1 extends RecyclerView.ViewHolder {
        @BindView(R2.id.home_sdv)
        SimpleDraweeView home_sdv;
        @BindView(R2.id.home_sdv_1)
        SimpleDraweeView home_sdv1;
        @BindView(R2.id.home_sdv_2)
        SimpleDraweeView home_sdv2;
        @BindView(R2.id.home_name)
        TextView home_name;
        @BindView(R2.id.home_yys)
        TextView home_yys;
        @BindView(R2.id.home_sj)
        TextView home_sj;
        @BindView(R2.id.rl)
        RelativeLayout rl;
        @BindView(R2.id.line)
        View line;

        public ViewHoder1(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
