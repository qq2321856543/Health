package com.wd.my.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.wd.common.base.util.Base.BaseAcitvity;
import com.wd.common.base.util.Base.BasePresenter;
import com.wd.my.R;
import com.wd.my.R2;
import com.wd.my.adapter.TaskRVAdapter;
import com.wd.my.bean.AddArchivesBean;
import com.wd.my.bean.SignInBean;
import com.wd.my.bean.TaskBean;
import com.wd.my.contract.TaskContract;
import com.wd.my.persenter.TaskPersenter;
import com.wd.my.view.Signin;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TaskActivity extends BaseAcitvity implements TaskContract.IView {
    private static final String TAG = "TaskActivity";
    @BindView(R2.id.signin_customview)
    Signin signinCustomview;
    @BindView(R2.id.iv_task_back)
    ImageView ivTaskBack;
    @BindView(R2.id.rv_task_everyday)
    RecyclerView rvTaskEveryday;
    @BindView(R2.id.rv_task_onlyone)
    RecyclerView rvTaskOnlyone;

    @Override
    protected BasePresenter initPresenter() {
        return new TaskPersenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_task;
    }

    @Override
    protected void initView() {
        BasePresenter persener = getPresenter();
        if (persener != null && persener instanceof TaskPersenter) {
            ((TaskPersenter) persener).getSignInDay();
            ((TaskPersenter) persener).getTask();
        }
    }

    @Override
    protected void initData() {
        List<String> list = new ArrayList<>();
        list.add("第一天");
        list.add("第二天");
        list.add("第三天");
        list.add("第四天");
        list.add("第五天");
        list.add("第六天");
        list.add("第七天");
        signinCustomview.setSignInData(list);
    }

    @Override
    public void getTaskSuccess(TaskBean taskBean) {
        List<TaskBean.ResultBean> result = taskBean.getResult();
        final List<TaskBean.ResultBean> daily = new ArrayList<>();
        final List<TaskBean.ResultBean> onlyOne = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            int taskType = result.get(i).getTaskType();
            if (taskType == 1) {
                daily.add(result.get(i));
            } else {
                onlyOne.add(result.get(i));
            }
        }
        //日常任务
        TaskRVAdapter dailyAdapter = new TaskRVAdapter(daily, this);
        RecyclerView.LayoutManager dailyLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvTaskEveryday.setLayoutManager(dailyLayoutManager);
        rvTaskEveryday.setAdapter(dailyAdapter);
        dailyAdapter.getPosition(new TaskRVAdapter.PositionCallBack() {
            @Override
            public void getPosition(int position, String flag) {
                if(flag.equals("去领取")){
                    int id = daily.get(position).getId();
                    BasePresenter persener = getPresenter();
                    if (persener != null && persener instanceof TaskPersenter) {
                        ((TaskPersenter) persener).receiveReward(id);
                    }
                }else if(flag.equals("去完成")){
                    int id = daily.get(position).getId();
                    BasePresenter persener = getPresenter();
                    if (persener != null && persener instanceof TaskPersenter) {
                        ((TaskPersenter) persener).doTask(id);
                    }
//                    String taskName = daily.get(position).getTaskName();
//                    if(taskName.equals("签到")){
////                        BasePersenter persener = getPersener();
////                        if (persener != null && persener instanceof TaskPersenter) {
////                            ((TaskPersenter) persener).doTask(daily.get(position).getId());
////                        }
////                        finish();
//                    }else if(taskName.equals("每日首发病友圈")){
//                        Toast.makeText(TaskActivity.this, "还没有写发病友圈页", Toast.LENGTH_SHORT).show();
//                    }else if(taskName.equals("每日病友圈首评")){
//                        Toast.makeText(TaskActivity.this, "还没有写发病友圈评论页", Toast.LENGTH_SHORT).show();
//                    }
                }
            }
        });

        //一次性
        TaskRVAdapter onlyAdapter = new TaskRVAdapter(onlyOne, this);
        RecyclerView.LayoutManager onlyLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rvTaskOnlyone.setLayoutManager(onlyLayoutManager);
        rvTaskOnlyone.setAdapter(onlyAdapter);
        onlyAdapter.getPosition(new TaskRVAdapter.PositionCallBack() {
            @Override
            public void getPosition(int position, String flag) {
                if(flag.equals("去领取")){
                    int id = onlyOne.get(position).getId();
                    BasePresenter persener = getPresenter();
                    if (persener != null && persener instanceof TaskPersenter) {
                        ((TaskPersenter) persener).receiveReward(id);
                    }
                }else if(flag.equals("去完成")){
                    int id = onlyOne.get(position).getId();
                    BasePresenter persener = getPresenter();
                    if (persener != null && persener instanceof TaskPersenter) {
                        ((TaskPersenter) persener).doTask(id);
                    }
//                    Intent intent = new Intent();
//                    String taskName = onlyOne.get(position).getTaskName();
//                    if(taskName.equals("完善档案")){
//                        intent.setClass(TaskActivity.this,ArchivesActivity.class);
//                        startActivity(intent);
//                    }else if(taskName.equals("参与健康测评")){
//                        Toast.makeText(TaskActivity.this, "还没有写健康评测页", Toast.LENGTH_SHORT).show();
//                    }else if(taskName.equals("绑定身份证")){
//                        Toast.makeText(TaskActivity.this, "还没有写绑定身份证页", Toast.LENGTH_SHORT).show();
//                    }else if(taskName.equals("绑定银行卡")){
//                        Toast.makeText(TaskActivity.this, "还没有写绑定银行卡页", Toast.LENGTH_SHORT).show();
//                    }
                }
            }
        });
    }

    @Override
    public void getTaskFailed(String msg) {

    }

    @Override
    public void doTaskSuccess(AddArchivesBean addArchivesBean) {

    }

    @Override
    public void doTaskFailed(String msg) {

    }

    @Override
    public void receiveRewardSuccess(AddArchivesBean addArchivesBean) {

    }

    @Override
    public void receiveRewardFailed(String msg) {

    }

    @Override
    public void getSignInDaySuccess(SignInBean signInBean) {

    }

    @Override
    public void getSignInDayFailed(String msg) {

    }
}
