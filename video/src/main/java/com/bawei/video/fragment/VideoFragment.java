package com.bawei.video.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bawei.video.R;
import com.bawei.video.R2;
import com.bawei.video.adapter.CommitAdapter;
import com.bawei.video.adapter.LeiMuAdapter;
import com.bawei.video.bean.GouMaiBean;
import com.bawei.video.bean.ShiPinBean;
import com.bawei.video.bean.ShiPinLeiMu;
import com.bawei.video.bean.ShiPinPingLieBiao;
import com.bawei.video.bean.YuEeBean;
import com.bawei.video.contral.HomePageContral;
import com.bawei.video.interfaces.OnViewPagerListener;
import com.bawei.video.presenter.HomePagePresenter;
import com.bawei.video.util.VideoApis;
import com.bawei.video.utils.GetScreenWinth;
import com.bawei.video.utils.MyVideoPlayer;
import com.bawei.video.utils.PagerLayoutManager;
import com.bawei.video.utils.SoftKeyBoardListener;
import com.bawei.video.utils.SoftKeyHideShow;
import com.bawei.video.utils.VideoAdapter;
import com.wd.common.base.util.Base.BaseFragment;
import com.wd.common.base.util.Base.BasePresenter;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

import static android.support.v4.content.ContextCompat.getSystemService;

/**
 * @ProjectName: Health
 * @Package: com.bawei.video
 * @ClassName: VideoFragment
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/6/13 19:06
 */
public class VideoFragment extends BaseFragment implements HomePageContral.getView {

    @BindView(R2.id.re)
    RecyclerView re;
    @BindView(R2.id.shang_top)
    RelativeLayout shangTop;

    private LeiMuAdapter adapter_leiMu;
    //视频

    private RecyclerView recyclerView;
    private PagerLayoutManager mLayoutManager;
    private MyVideoPlayer jzVideo;
    private SoftKeyBoardListener softKeyBoardListener;//软键盘监听
    private CommitAdapter commitAdapter;
    private RecyclerView recyclerViewCommit;
    private RelativeLayout rl_bottom;
    private View commit;
    private TextView tv_shape, tv_shape2, tv_send;
    private EditText tv_context;
    private LinearLayout ll_cancel;
    private RelativeLayout rl_all;
    private EditText et_context;
    private Animation showAction;
    private VideoAdapter adapter;
    boolean shifou=false;
    /**
     * 默认从第一个开始播放
     */
    private int positionClick = 0;

    /**
     * 是否可以自动滑动
     * 当现实评论列表，说明用户想评论，不可以自动滑动
     */
    private boolean isScroll = true;
    private List<ShiPinBean.ResultBean> result;
    private List<ShiPinPingLieBiao.ResultBean> result1;
    private View popView;
    private PopupWindow popupWindow;
    private int id;
    private int price;
    private View popView_jiaofei;
    private PopupWindow popupWindow_jiaofei;

    @Override
    public void onResume() {
        super.onResume();
        //home back
        if (jzVideo != null) {
            jzVideo.goOnPlayOnResume();
        }
        if (danmakuView != null && danmakuView.isPrepared() && danmakuView.isPaused()) {
            danmakuView.resume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        // 视频回去的时候要暂停
        ((AudioManager) getSystemService(
                Context.AUDIO_SERVICE)).requestAudioFocus(
                new AudioManager.OnAudioFocusChangeListener() {
                    @Override
                    public void onAudioFocusChange(int focusChange) {
                    }
                }, AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        //home back
//        if (jzVideo != null) {
//            jzVideo.goOnPlayOnPause();
//        }
        //弹幕
        if (danmakuView != null && danmakuView.isPrepared()) {
            danmakuView.pause();
        }
    }

    private Object getSystemService(String audioService) {
        return null;
    }


    @Override
    protected BasePresenter initPresenter() {
        return new HomePagePresenter(this);
    }


    @Override
    protected int getLayout() {
        return R.layout.video_fragment_video;
    }

    @Override
    protected void initView(View view) {
        //找控件
        rl_bottom = view.findViewById(R.id.rl_bottom);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerViewCommit = view.findViewById(R.id.recyclerViewCommit);
        commit = view.findViewById(R.id.commit);
        tv_shape = view.findViewById(R.id.tv_shape);
        tv_shape2 = view.findViewById(R.id.tv_shape2);
        tv_send = view.findViewById(R.id.tv_send);
        tv_context = view.findViewById(R.id.tv_context);
        ll_cancel = view.findViewById(R.id.ll_cancel);
        rl_all = view.findViewById(R.id.rl_all);
        et_context = view.findViewById(R.id.et_context);
        //创建布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        re.setLayoutManager(linearLayoutManager);
        adapter_leiMu = new LeiMuAdapter(getContext());
        re.setAdapter(adapter_leiMu);
        //网络
        BasePresenter persener = getPresenter();
        if (persener instanceof HomePagePresenter) {
            ((HomePagePresenter) persener).getLeiMu();
        }
        dan(view);
    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if (presenter instanceof HomePagePresenter) {
            ((HomePagePresenter)presenter).getLeiMu();
//            ((HomePagePresenter) presenter).getShiPin(2, 1, 20);
        }
    }




    String nme;
    int tiaomu;
    int leiMu_id;
    @Override
    public void getLeiMuSucc(ShiPinLeiMu shiPinLeiMu) {
        if (shiPinLeiMu != null) {
            adapter_leiMu.setData(shiPinLeiMu.getResult());
            adapter_leiMu.setOnClickItem(new LeiMuAdapter.OnCliCkItem() {
                @Override
                public void onck(int a, String name) {
                    leiMu_id = a;
                    nme = name;
                    BasePresenter persener = getPresenter();
                    if (persener instanceof HomePagePresenter) {
                        ((HomePagePresenter) persener).getShiPin(a, 1, 20);
                    }
                }
            });
        }
    }

    @Override
    public void getLeiMuFiuld(String str) {

    }

    @Override
    public void getShiPinSucc(ShiPinBean shiPinBean) {
        if (shiPinBean.getMessage().equals("查询成功") && shiPinBean.getResult() != null) {
            result = shiPinBean.getResult();
            setAdapter(shiPinBean.getResult());
            setSoftKeyBoardListener();
        }
    }

    @Override
    public void getShiPinFiuld(String str) {

    }

    @Override
    public void getShiPinPingLieBiaoSucc(ShiPinPingLieBiao shiPinPingLieBiao) {
        if (shiPinPingLieBiao.getMessage().equals("查询成功")) {
            result1 = shiPinPingLieBiao.getResult();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < shiPinPingLieBiao.getResult().size(); i++) {
                        int time = new Random().nextInt(300);
                        String content = "" + time + time;
                        addDanmaku(shiPinPingLieBiao.getResult().get(i).getContent(), true, Color.GREEN);
                        try {
                            Thread.sleep(time);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

        }
    }

    @Override
    public void getShiPinPingLieBiaoFiuld(String str) {

    }

    private void setAdapter(List<ShiPinBean.ResultBean> result) {
        //设置adapter
        adapter = new VideoAdapter(getContext(), result);

        if (nme != null) {
            adapter.setData(nme);
        }

        recyclerView.setAdapter(adapter);
        mLayoutManager = new PagerLayoutManager(getContext(), OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        //条目设置
        moveToPosition(tiaomu);

        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete(View view) {
                //点击进入 0
                playVideo(view, false);
                if (result != null) {
                    tiaomu = 0;
                    id = result.get(0).getId();
                    shifou=false;
                    price = result.get(0).getPrice();
                    /*Toast.makeText(ShiPinActivity.this, "id是"+id, Toast.LENGTH_SHORT).show();*/
                    BasePresenter persener = getPresenter();
                    if (persener instanceof HomePagePresenter) {
                        ((HomePagePresenter) persener).getShiPinPingLieBiao(id);
                    }

                    if (result.get(tiaomu).getWhetherBuy() == 1) {

                    } else {
//                        shi_kan();
                    }
                }
            }

            @Override
            public void onPageSelected(int position, boolean isBottom, View view) {
                positionClick = position;
                //滑动选择 1
                //弹幕id
                if (result != null) {
                    tiaomu = position;
                    id = result.get(position).getId();
                    shifou=false;
                    price = result.get(position).getPrice();
                    /*Toast.makeText(ShiPinActivity.this, "id是"+id, Toast.LENGTH_SHORT).show();*/
                    BasePresenter persener = getPresenter();
                    if (persener instanceof HomePagePresenter) {
                        ((HomePagePresenter) persener).getShiPinPingLieBiao(id);
                    }


                    if (result.get(tiaomu).getWhetherBuy() == 1) {

                    } else {
//                        shi_kan();
                    }
                }
                playVideo(view, isBottom);
            }

            @Override
            public void onPageRelease(boolean isNext, int position, View view) {
                //暂停上一个播放
//                releaseVideo(view);
            }
        });


        adapter.setOnItemClickListerer(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String type, View view, View view1, View view2) {
                if (type.equals("back")) {
                    //返回
                    if (jzVideo.backPress()) {
                        return;
                    }
                } else if (type.equals("commit")) {
                    //评论
                    showCommitDialog();
                }
            }
        });
        //点击购买
        adapter.setOnCkMai(new VideoAdapter.onCkMai() {
            @Override
            public void setOnCk(int a) {
                View parent = ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
                initTopPop(a);
                popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        //暂停
        adapter.setOnClickGif(new VideoAdapter.OnClickGif() {
            @Override
            public void onck() {
                Toast.makeText(getContext(), "暂停", Toast.LENGTH_SHORT).show();
                jzVideo.goOnPlayOnPause();
                jzVideo.onStatePause();

            }
        });
    }

    private void initTopPop(int a) {
        qian();
        popView = LayoutInflater.from(getContext()).inflate(R.layout.item_pop_mai, null, false);
        popupWindow = new PopupWindow(popView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        VideoView video = popView.findViewById(R.id.video);
        //购买的点击事件
        popView.findViewById(R.id.mai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gouMai();
            }
        });

        TextView hb = popView.findViewById(R.id.hb);

        TextView hb_hzbz = popView.findViewById(R.id.hb_hzbz);
        Button mai = popView.findViewById(R.id.mai);
        //视频
        //将路径转换成uri
        Toast.makeText(getContext(), "" + result.get(a).getOriginalUrl(), Toast.LENGTH_SHORT).show();
        Uri uri = Uri.parse(result.get(a).getOriginalUrl());
        //为视频播放器设置视频路径
        video.setVideoURI(uri);
        video.setMediaController(new MediaController(getContext()));
        //开始播放视频
        video.requestFocus();
        video.start();
        //文字
        hb.setText(result.get(a).getPrice() + "HB");
        //剩余hb

        //pop点击事件
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x73888888));
        //获取网络数据
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
    }

    /**
     * 评论布局
     */
    public void showCommitDialog() {
        if (commitAdapter == null) {
            commitAdapter = new CommitAdapter(getContext());
            recyclerViewCommit.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerViewCommit.setAdapter(commitAdapter);
        } else {
            commitAdapter.notifyDataSetChanged();
        }

        //为布局设置显示的动画
        showAction = AnimationUtils.loadAnimation(getContext(), R.anim.actionsheet_dialog_in);
        commit.startAnimation(showAction);

        //显示布局和阴影
        commit.setVisibility(View.VISIBLE);
        tv_shape.setVisibility(View.VISIBLE);

        //不可以滑动
        isScroll = false;

        //关闭评论
        ll_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commit.setVisibility(View.GONE);
                tv_shape.setVisibility(View.GONE);
                //可以滑动
                isScroll = true;
            }
        });
        //阴影点击,隐藏评论列表和阴影
        tv_shape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commit.setVisibility(View.GONE);
                tv_shape.setVisibility(View.GONE);
                //可以滑动
                isScroll = true;
            }
        });
        //输入评论点击
        tv_context.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoftKeyHideShow.HideShowSoftKey(getContext());//隐藏软键盘
            }
        });
        //第二层阴影点击，隐藏输入评论框和软键盘
        tv_shape2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoftKeyHideShow.HideShowSoftKey(getContext());//隐藏软键盘
            }
        });
        //发送评论
        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "评论成功", Toast.LENGTH_SHORT).show();
                SoftKeyHideShow.HideShowSoftKey(getContext());//隐藏软键盘
            }
        });
    }


    /**
     * 开始播放 & 监听播放完成
     */
    private void playVideo(View view, boolean isBottom) {
        if (view != null) {
            jzVideo = view.findViewById(R.id.jzVideo);
            jzVideo.startVideo();
            if (isBottom) {
                //到最后一个加载第二页
                adapter.notifyDataSetChanged();
            }
            jzVideo.setFinishListerer(new MyVideoPlayer.OnItemClickListener() {
                @Override
                public void onItemClick() {
                    //播放完成自动播放下一个,用户没有看评论列表可以播放下一个
                    if (isScroll) {
                        smoothMoveToPosition(recyclerView, positionClick++);
                    }
                }
            });
        }
    }

    /**
     * 平滑的滑动到指定位置
     */
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
            // 第一种可能:跳转位置在第一个可见位置之前
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            //滑动指定高度
            mRecyclerView.smoothScrollBy(0, GetScreenWinth.getHeight(getActivity()) -
                    (Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"))));
        } else {
            // 第三种可能:跳转位置在最后可见项之后
            mRecyclerView.smoothScrollToPosition(position);
        }
    }

    /**
     * 软键盘监听
     */
    private void setSoftKeyBoardListener() {
        softKeyBoardListener = new SoftKeyBoardListener(getActivity());
        //软键盘状态监听
        softKeyBoardListener.setListener(new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                //动态设置控件宽高
                ViewGroup.LayoutParams params = rl_bottom.getLayoutParams();
                rl_bottom.setPadding(0, 0, 0, height);
                rl_bottom.setLayoutParams(params);
                //当软键盘显示的时候
                rl_bottom.setVisibility(View.VISIBLE);
                tv_shape2.setVisibility(View.VISIBLE);

                et_context.setFocusable(true);
                et_context.setFocusableInTouchMode(true);
                et_context.setCursorVisible(true);
                et_context.requestFocus();
            }

            @Override
            public void keyBoardHide(int height) {
                //当软键盘隐藏的时候
                rl_bottom.setVisibility(View.GONE);
                tv_shape2.setVisibility(View.GONE);
                et_context.setFocusable(false);
                et_context.setFocusableInTouchMode(false);
                et_context.setCursorVisible(false);
            }
        });
        //设置点击事件,显示软键盘
        et_context.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
            @Override
            public void onClick(View view) {
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }

            private Object getSystemService(String inputMethodService) {
                return null;
            }
        });
        //防止EditText点击两次才获取到焦点
        et_context.setOnTouchListener(new View.OnTouchListener() {
            //按住和松开的标识
            int flag = 0;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                flag++;
                if (flag == 2) {
                    flag = 0;//不要忘记这句话
                    //处理逻辑
                    et_context.setFocusable(true);
                    et_context.setFocusableInTouchMode(true);
                    et_context.setCursorVisible(true);
                }
                return false;
            }
        });
    }


    /**
     * 获取状态通知栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusBarHeight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }


    private void moveToPosition(int position) {
        if (position != -1) {
            recyclerView.scrollToPosition(position);
            LinearLayoutManager mLayoutManager =
                    (LinearLayoutManager) recyclerView.getLayoutManager();
            mLayoutManager.scrollToPositionWithOffset(position, 0);
        }
    }

    //视频购买
    private void gouMai() {
        Toast.makeText(getContext(), "id" + id + "price" + price, Toast.LENGTH_SHORT).show();
        VideoApis.createrRetrofit().getGouMai(id, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GouMaiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GouMaiBean gouMaiBean) {
                        Toast.makeText(getContext(), "" + gouMaiBean.getMessage(), Toast.LENGTH_SHORT).show();
                        if (gouMaiBean.getMessage().equals("购买成功")) {
                            if (popupWindow != null && popupWindow.isShowing()) {
                                popupWindow.dismiss();
                            }
                            if (popupWindow_jiaofei != null && popupWindow_jiaofei.isShowing()) {
                                popupWindow_jiaofei.dismiss();
                            }
                            shifou=true;
                            BasePresenter persener = getPresenter();
                            if (persener instanceof HomePagePresenter) {
                                ((HomePagePresenter) persener).getShiPin(leiMu_id, 1, 20);
                            }
                            if (persener instanceof HomePagePresenter) {
                                ((HomePagePresenter) persener).getShiPin(id, 1, 20);
                            }
                            Toast.makeText(getContext(), "滑动的条目是" + tiaomu, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void qian() {
        VideoApis.createrRetrofit().getYuEe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<YuEeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(YuEeBean yuEeBean) {
                        if (yuEeBean.getMessage().equals("查询成功")) {
                            TextView hb_hzbz = popView.findViewById(R.id.hb_hzbz);
                            hb_hzbz.setText("我的h币为" + yuEeBean.getResult() + " h币不足？" + "");
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //试看
    private void shi_kan() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                int time = new Random().nextInt(30000);
                String content = "" + time + time;
                try {
                    Thread.sleep(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(getContext(), "时间过了5分钟,请缴费", Toast.LENGTH_SHORT).show();
                initTopPop_jiaofei();
                popupWindow_jiaofei.showAtLocation(shangTop, Gravity.TOP | Gravity.START, 0, getStatusBarHeight(getActivity()));
                Looper.loop();//增加部分
            }
        }).start();


    }

    Handler handler=new Handler();
    public void initTopPop_jiaofei() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                //暂停
                jzVideo.goOnPlayOnPause();
            }
        });


        popView_jiaofei = LayoutInflater.from(getContext()).inflate(R.layout.pop_jiao_fei, null, false);
        popupWindow_jiaofei = new PopupWindow(popView_jiaofei,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT, true);

        /*re = popView.findViewById(R.id.re);*/
        //点击缴费
        popView_jiaofei.findViewById(R.id.jiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        View parent = ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
                        initTopPop(tiaomu);
                        popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    }
                });
            }
        });
        //点击背景消失当前diglog
        this.popupWindow_jiaofei.setBackgroundDrawable(new ColorDrawable(0x73888888));

        this.popupWindow_jiaofei.setClippingEnabled(false);
        this.popupWindow_jiaofei.setTouchable(true);
        this.popupWindow_jiaofei.setOutsideTouchable(true);

        popupWindow_jiaofei.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if(shifou=false){
                    popupWindow_jiaofei.showAtLocation(shangTop, Gravity.TOP | Gravity.START, 0, getStatusBarHeight(getActivity()));
                }else{

                }

            }
        });
    }

    //弹幕
    private boolean showDanmaku;
    private DanmakuView danmakuView;
    private DanmakuContext danmakuContext;
    private BaseDanmakuParser parser = new BaseDanmakuParser() {
        @Override
        protected IDanmakus parse() {
            return new Danmakus();
        }
    };

    public void dan(View view) {
        danmakuView = (DanmakuView)view. findViewById(R.id.danmaku_view);
        danmakuView.enableDanmakuDrawingCache(true);
        danmakuView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                showDanmaku = true;
                danmakuView.start();
                generateSomeDanmaku();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });
        danmakuContext = DanmakuContext.create();
        danmakuView.prepare(parser, danmakuContext);

        final LinearLayout operationLayout = (LinearLayout)view. findViewById(R.id.operation_layout);
        Button send = (Button)view. findViewById(R.id.send);
        final EditText editText = (EditText) view.findViewById(R.id.edit_text);
        danmakuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operationLayout.getVisibility() == View.GONE) {
                    operationLayout.setVisibility(View.VISIBLE);
                } else {
                    operationLayout.setVisibility(View.GONE);
                }
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    addDanmaku(content, true, Color.GREEN);
                    editText.setText("");
                }
            }
        });
       getActivity(). getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == View.SYSTEM_UI_FLAG_VISIBLE) {
                    getActivity().onWindowFocusChanged(true);
                }
            }
        });
    }

    /**
     * 向弹幕View中添加一条弹幕
     *
     * @param content    弹幕的具体内容
     * @param withBorder 弹幕是否有边框
     */
    private void addDanmaku(String content, boolean withBorder) {
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        danmaku.padding = 5;
        danmaku.textSize = sp2px(20);
        danmaku.textColor = Color.WHITE;
        danmaku.setTime(danmakuView.getCurrentTime());
        if (withBorder) {
            danmaku.borderColor = Color.GREEN;
        }
        danmakuView.addDanmaku(danmaku);
    }

    /**
     * 弹幕View中添加一条弹幕
     *
     * @param content    弹幕的具体内容
     * @param withBorder 弹幕是否有边框
     * @param textColor  弹幕字体颜色
     */
    private void addDanmaku(String content, boolean withBorder, int textColor) {
        BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
        danmaku.text = content;
        Log.i("contextcontextcontext", "" + content);
        danmaku.padding = 5;
        danmaku.textSize = sp2px(20);
        danmaku.textColor = textColor;
        danmaku.setTime(danmakuView.getCurrentTime());
        if (withBorder) {
            danmaku.borderColor = Color.GREEN;
        }
        danmakuView.addDanmaku(danmaku);
    }

    /**
     * 随机生成一些弹幕内容以供测试
     */
    private void generateSomeDanmaku() {
       /* new Thread(new Runnable() {
            @Override
            public void run() {
                while (showDanmaku) {
                    int time = new Random().nextInt(300);
                    String content = "" + time + time;
                    addDanmaku(content, false);
                    try {
                        Thread.sleep(time);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();*/
    }

    /**
     * sp转px的方法。
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showDanmaku = false;
        if (danmakuView != null) {
            danmakuView.release();
            danmakuView = null;
        }
    }

}
