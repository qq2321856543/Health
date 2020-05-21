package com.wd.login.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnPageChangeListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.login.R;
import com.wd.login.R2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuidePageActivity extends BaseAcitvity {


    @BindView(R2.id.cb_guide_page)
    ConvenientBanner cb;
    @BindView(R2.id.bt_guide_page)
    Button btJoin;

    private ArrayList<Integer> list;
    private TextView tv;
    private ArrayList<String> data;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_guide_page;


    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
        initGuidePage();
    }

    @Override
    protected void initView() {
        data = new ArrayList<>();
        data.add("专业的在线问诊");
        data.add("丰富的健康知识");
        data.add("专业的在线问诊");
        data.add("丰富的健康知识");
        data.add("打造你的健康常青树");
        list = new ArrayList<>();
        list.add(R.mipmap.login_pages_one);
        list.add(R.mipmap.login_pages_two);
        list.add(R.mipmap.login_pages_three);
        list.add(R.mipmap.login_pages_four);
        list.add(R.mipmap.login_pages_five);

    }

    @Override
    protected void initData() {

    }

    private void initGuidePage() {
        cb.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new LocalImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.guide_page;
            }
        }, list)
                .setPageIndicator(new int[]{R.mipmap.login_dian, R.mipmap.login_dian, R.mipmap.login_dian, R.mipmap.login_dian, R.mipmap.login_dian})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setPointViewVisible(true)
                .setCanLoop(false)
                .setOnPageChangeListener(new OnPageChangeListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    }

                    @Override
                    public void onPageSelected(int index) {
                        for (int i = 0; i < index; i++) {
                            for (int j = 0; j < data.size(); j++) {
                                tv.setText(data.get(i));
                            }
                        }
                        if (index == 4) {
                            btJoin.setVisibility(View.VISIBLE);
                            cb.setPointViewVisible(true);
                        } else {
                            cb.setPointViewVisible(true);
                            btJoin.setVisibility(View.GONE);
                        }
                    }
                });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.bt_guide_page)
    public void onViewClicked() {
        Intent intent = new Intent(GuidePageActivity.this, LoginLonginActivity.class);
        startActivity(intent);
        finish();
    }

    public class LocalImageHolderView extends Holder<Integer> {

        private SimpleDraweeView mimageView;


        public LocalImageHolderView(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {
            mimageView = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
//            mimageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        @Override
        public void updateUI(Integer data) {
            mimageView.setImageResource(data);
        }

    }


}
