package com.sike.testvideo01;

import com.dueeeke.videoplayer.player.IjkVideoView;

/**
 * @author wodemingziyouyidianchang
 * @version 1.0.0
 * @since 2018/9/10 22:54
 */

// 呵呵你叫悬浮工具
public class SuspendUtil {

    private static SuspendUtil instance;

    private IjkVideoView ijkVideoView;
    private FloatView floatView;
    private FloatController mFloatController;
    private boolean isShowing;
    //    private KeyReceiver mKeyReceiver;
    private int mPlayingPosition = -1;
    private Class mActClass;

    private SuspendUtil(){

    }

    // 呵呵你叫双重判断式单例,这种low的代码已经调动不了我的兴趣了
    public static SuspendUtil getInstance() {
        if (instance == null) {
            synchronized (SuspendUtil.class) {
                if (instance == null) {
                    instance = new SuspendUtil();
                }
            }
        }
        return instance;
    }


}
