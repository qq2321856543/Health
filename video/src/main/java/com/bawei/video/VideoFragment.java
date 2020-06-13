package com.bawei.video;

import android.view.View;
import android.widget.Toast;

import com.wd.common.base.util.Base.BaseFragment;
import com.wd.common.base.util.Base.BasePresenter;

/**
 * @ProjectName: Health
 * @Package: com.bawei.video
 * @ClassName: VideoFragment
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/6/13 19:06
 */
public class VideoFragment extends BaseFragment {
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.video_fragment_video;
    }

    @Override
    protected void initView(View view) {
        Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {

    }
}
