package com.bawei.video.base;

import com.dueeeke.videoplayer.ijk.IjkPlayerFactory;
import com.dueeeke.videoplayer.player.VideoViewConfig;
import com.dueeeke.videoplayer.player.VideoViewManager;
import com.wd.common.base.util.Base.BaseApplication;

/**
 * @ProjectName: Health
 * @Package: com.bawei.video.base
 * @ClassName: VideoApp
 * @Description: (java类作用描述)
 * @Author: 李聪聪
 * @CreateDate: 2020/6/14 14:26
 */
public class VideoApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        VideoViewManager.setConfig(VideoViewConfig.newBuilder()
                //使用使用IjkPlayer解码
                .setPlayerFactory(IjkPlayerFactory.create())
                .build());
    }
}
