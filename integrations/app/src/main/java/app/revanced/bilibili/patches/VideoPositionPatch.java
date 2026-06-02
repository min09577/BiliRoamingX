package app.revanced.bilibili.patches;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Keep;

import app.revanced.bilibili.patches.main.VideoInfoHolder;
import app.revanced.bilibili.settings.Settings;
import app.revanced.bilibili.utils.Logger;
import app.revanced.bilibili.utils.Utils;

import com.bapis.bilibili.app.viewunite.v1.ViewReply;

import java.lang.ref.WeakReference;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * 记忆视频播放位置功能
 * 在视频退出时保存播放进度，下次打开同一视频时自动跳转到上次位置
 */
public class VideoPositionPatch {
    private static final String PREFS_NAME = "biliroaming_video_positions";
    private static final int MIN_POSITION_MS = 5000; // 小于5秒不保存
    private static final int MAX_DURATION_MS = 4 * 60 * 60 * 1000; // 超过4小时不保存（可能是直播）

    // 当前活跃的IMediaPlayer引用
    private static WeakReference<IMediaPlayer> currentPlayerRef = new WeakReference<>(null);
    
    // 当前视频key缓存，避免重复计算
    private static String currentVideoKey = null;

    /**
     * 在播放器准备好时调用，恢复上次的播放位置
     * 由 DefaultPlaybackSpeedPatch 在 PlayerOnPrepared 中注入调用
     */
    @Keep
    public static void onPlayerPrepared(IMediaPlayer player) {
        if (!Settings.RememberVideoPosition.get()) {
            return;
        }
        try {
            currentPlayerRef = new WeakReference<>(player);
            currentVideoKey = getVideoKey();
            
            if (currentVideoKey == null) {
                return;
            }
            
            SharedPreferences prefs = getPrefs();
            if (prefs.contains(currentVideoKey)) {
                int savedPosition = prefs.getInt(currentVideoKey, 0);
                if (savedPosition > MIN_POSITION_MS) {
                    // 延迟跳转，确保播放器完全准备好
                    final int seekPos = savedPosition;
                    final String key = currentVideoKey;
                    Utils.async(300L, () -> {
                        try {
                            IMediaPlayer p = currentPlayerRef.get();
                            if (p != null) {
                                p.seekTo(seekPos);
                                Logger.debug(() -> "VideoPosition: restored " + seekPos + "ms for " + key);
                            }
                        } catch (Throwable t) {
                            Logger.error(t, () -> "VideoPosition: failed to restore");
                        }
                    });
                }
            }
        } catch (Throwable t) {
            Logger.error(t, () -> "VideoPosition: error in onPlayerPrepared");
        }
    }

    /**
     * 保存当前播放位置
     * 从 Activity 的 onPause/onStop 中调用
     */
    @Keep
    public static void savePosition() {
        if (!Settings.RememberVideoPosition.get()) {
            return;
        }
        try {
            IMediaPlayer player = currentPlayerRef.get();
            if (player == null || currentVideoKey == null) {
                return;
            }
            
            int position = (int) player.getCurrentPosition();
            int duration = (int) player.getDuration();
            
            // 不保存太短的进度
            if (position < MIN_POSITION_MS) {
                return;
            }
            // 不保存太长的时长（可能是直播）
            if (duration > MAX_DURATION_MS || duration <= 0) {
                return;
            }
            // 距离结尾小于5秒，视为看完，清除记录
            if ((duration - position) < 5000) {
                getPrefs().edit().remove(currentVideoKey).apply();
                Logger.debug(() -> "VideoPosition: cleared (near end) for " + currentVideoKey);
                return;
            }
            
            getPrefs().edit().putInt(currentVideoKey, position).apply();
            Logger.debug(() -> "VideoPosition: saved " + position + "ms for " + currentVideoKey);
        } catch (Throwable t) {
            Logger.error(t, () -> "VideoPosition: error saving");
        }
    }

    /**
     * 清除播放器引用（Activity销毁时调用）
     */
    @Keep
    public static void onPlayerDestroyed() {
        savePosition();
        currentPlayerRef = new WeakReference<>(null);
        currentVideoKey = null;
    }

    /**
     * 获取当前视频的唯一标识键 (aid:cid)
     */
    private static String getVideoKey() {
        try {
            if (VideoInfoHolder.getCurrent() == null) return null;
            Object view = VideoInfoHolder.getCurrent().getView();
            long cid = VideoInfoHolder.getCurrent().getCid();
            if (view == null || cid == 0) return null;
            
            if (view instanceof ViewReply) {
                ViewReply viewReply = (ViewReply) view;
                if (viewReply.hasArc()) {
                    return viewReply.getArc().getAid() + ":" + cid;
                }
            } else if (view instanceof com.bapis.bilibili.app.view.v1.ViewReply) {
                com.bapis.bilibili.app.view.v1.ViewReply viewReply = 
                    (com.bapis.bilibili.app.view.v1.ViewReply) view;
                if (viewReply.hasArc()) {
                    return viewReply.getArc().getAid() + ":" + cid;
                }
            }
        } catch (Throwable t) {
            Logger.error(t, () -> "VideoPosition: error getting video key");
        }
        return null;
    }

    private static SharedPreferences getPrefs() {
        return Utils.getContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }
}
