package app.revanced.bilibili.patches;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Keep;

import com.bilibili.bililive.room.ui.roomv3.player.container.LiveRoomPlayerContainerView;

import app.revanced.bilibili.patches.main.ApplicationDelegate;
import app.revanced.bilibili.settings.Settings;
import app.revanced.bilibili.utils.Logger;
import app.revanced.bilibili.utils.Reflex;
import app.revanced.bilibili.utils.Utils;

@Keep
public class LiveRoomPatch {
    public static boolean forbidSwitchLiveRoom() {
        return Settings.ForbidSwitchLiveRoom.get();
    }

    public static boolean disableLiveRoomDoubleClick() {
        return Settings.DisableLiveRoomDoubleClick.get();
    }

    public static boolean onDoubleTap(LiveRoomPlayerContainerView playerView) {
        if (!disableLiveRoomDoubleClick()) return false;
        try {
            var playerBridge = Reflex.callMethod(playerView, "getPlayerCommonBridge");
            if (Reflex.callMethod(playerBridge, "isPlaying")) {
                Reflex.callMethod(playerBridge, "pause");
            } else {
                Reflex.callMethod(playerBridge, "resume");
            }
            return true;
        } catch (Throwable e) {
            Logger.error(e, () -> "disable live room double tap failed");
            return false;
        }
    }

    public static boolean disableSlideLeft() {
        return Settings.DisableSlideLeft.get();
    }

    public static boolean disableAutoFloat() {
        return Settings.DisableAutoFloat.get();
    }

    /**
     * Called from Activity lifecycle to remove live room watermark overlay.
     * Hides the watermark view (room number + logo) in the top-left corner.
     */
    public static void removeLiveWatermark() {
        if (!Settings.RemoveLiveWatermark.get()) return;
        try {
            Activity activity = ApplicationDelegate.getTopActivity();
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) return;
            // Try common watermark resource IDs
            String[] watermarkIds = {
                "live_room_watermark",
                "live_watermark_container",
                "rl_watermark",
                "iv_watermark",
                "live_room_cover_watermark"
            };
            for (String idName : watermarkIds) {
                int resId = Utils.getResId(idName, "id");
                if (resId != 0) {
                    View watermarkView = activity.findViewById(resId);
                    if (watermarkView != null) {
                        watermarkView.setVisibility(View.GONE);
                        final String capturedIdName = idName;
                        Logger.debug(() -> "LiveRoomPatch: hid watermark view " + capturedIdName);
                        return;
                    }
                }
            }
            // Fallback: traverse the view tree to find watermark-like views
            View decorView = activity.getWindow().getDecorView();
            hideWatermarkRecursive(decorView, 0);
        } catch (Throwable e) {
            Logger.error(e, () -> "LiveRoomPatch: failed to remove watermark");
        }
    }

    private static void hideWatermarkRecursive(View view, int depth) {
        if (depth > 8) return;
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                View child = group.getChildAt(i);
                // Check if this view's resource name contains "watermark"
                try {
                    String resName = "";
                    if (child.getId() != View.NO_ID) {
                        resName = child.getResources().getResourceEntryName(child.getId());
                    }
                    final String name = resName;
                    if (name.contains("watermark")) {
                        child.setVisibility(View.GONE);
                        Logger.debug(() -> "LiveRoomPatch: hid watermark by traversal: " + name);
                        return;
                    }
                } catch (Throwable ignored) {}
                hideWatermarkRecursive(child, depth + 1);
            }
        }
    }

    /**
     * Called from Activity lifecycle to remove live room mosaic overlay.
     * Hides the mosaic/遮罩 view that covers part of the live video.
     */
    public static void removeLiveMosaic() {
        if (!Settings.RemoveLiveMask.get()) return;
        try {
            Activity activity = ApplicationDelegate.getTopActivity();
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) return;
            // Try common mosaic resource IDs
            String[] mosaicIds = {
                "live_room_mosaic",
                "live_mosaic_container",
                "mosaic_view",
                "live_mask_view",
                "live_room_mask",
                "live_cover_mask",
                "iv_mosaic",
                "fl_mosaic"
            };
            for (String idName : mosaicIds) {
                int resId = Utils.getResId(idName, "id");
                if (resId != 0) {
                    View mosaicView = activity.findViewById(resId);
                    if (mosaicView != null) {
                        mosaicView.setVisibility(View.GONE);
                        final String capturedIdName = idName;
                        Logger.debug(() -> "LiveRoomPatch: hid mosaic view " + capturedIdName);
                        return;
                    }
                }
            }
            // Fallback: traverse the view tree to find mosaic-like views
            View decorView = activity.getWindow().getDecorView();
            hideMosaicRecursive(decorView, 0);
        } catch (Throwable e) {
            Logger.error(e, () -> "LiveRoomPatch: failed to remove mosaic");
        }
    }

    private static void hideMosaicRecursive(View view, int depth) {
        if (depth > 8) return;
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                View child = group.getChildAt(i);
                // Check if this view's resource name contains "mosaic" or "mask"
                try {
                    String resName = "";
                    if (child.getId() != View.NO_ID) {
                        resName = child.getResources().getResourceEntryName(child.getId());
                    }
                    final String name = resName;
                    if (name.contains("mosaic") || name.contains("mask")) {
                        child.setVisibility(View.GONE);
                        Logger.debug(() -> "LiveRoomPatch: hid mosaic by traversal: " + name);
                        return;
                    }
                } catch (Throwable ignored) {}
                hideMosaicRecursive(child, depth + 1);
            }
        }
    }
}
