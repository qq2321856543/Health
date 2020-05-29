package com.wd.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



import com.wd.home.R;
import com.wd.home.R2;
import com.wd.home.activity.HomeSearchActivity;
import com.wd.home.bean.HomePageSearchBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrugsSearchVoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HomePageSearchBean.ResultBean.DrugsSearchVoListBean> drugsSearchVoList;

    public DrugsSearchVoListAdapter(Context context, List<HomePageSearchBean.ResultBean.DrugsSearchVoListBean> drugsSearchVoList) {
        this.context = context;
        this.drugsSearchVoList = drugsSearchVoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.drugssearchvolistadapter, null);
        ViewHoder viewHoder = new ViewHoder(view);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomePageSearchBean.ResultBean.DrugsSearchVoListBean drugsSearchVoListBean = drugsSearchVoList.get(position);
        String drugsName = drugsSearchVoListBean.getDrugsName();
        ((ViewHoder)holder).tv.setText(drugsName);
    }

    @Override
    public int getItemCount() {
        return drugsSearchVoList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{
        @BindView(R2.id.tv)
        TextView tv;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
