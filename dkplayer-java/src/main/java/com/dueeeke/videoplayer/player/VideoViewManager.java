package com.dueeeke.videoplayer.player;

/**
 * 视频播放器管理器.
 */
public class VideoViewManager {

    private static VideoViewManager sInstance;
    private IjkVideoView mPlayer;

    private VideoViewManager() {
    }

    public static VideoViewManager instance() {
        if (sInstance == null) {
            synchronized (VideoViewManager.class) {
                if (sInstance == null) {
                    sInstance = new VideoViewManager();
                }
            }
        }
        return sInstance;
    }

    public IjkVideoView getCurrentVideoPlayer() {
        return mPlayer;
    }

    public void setCurrentVideoPlayer(IjkVideoView player) {
        mPlayer = player;
    }

    public void releaseVideoPlayer() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    public void stopPlayback() {
        if (mPlayer != null) mPlayer.stopPlayback();
    }

    public boolean onBackPressed() {
        return mPlayer != null && mPlayer.onBackPressed();
    }
}
