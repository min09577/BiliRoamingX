package app.revanced.bilibili.patches;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Keep;

import app.revanced.bilibili.patches.main.ApplicationDelegate;
import app.revanced.bilibili.settings.Settings;
import app.revanced.bilibili.utils.Logger;
import app.revanced.bilibili.utils.Utils;

@Keep
public class VideoDescPatch {

    /**
     * Called from bytecode patch when setOnClickListener is called on ExpandableLayout.
     * If AutoExpandDesc is enabled, post a click to trigger expansion.
     */
    public static void onDescExpandableClickSet(View view) {
        if (!Settings.AutoExpandDesc.get()) return;
        try {
            // Post a click after layout is done to trigger expansion
            view.post(() -> {
                try {
                    view.performClick();
                } catch (Throwable e) {
                    Logger.error(e, () -> "VideoDescPatch: failed to auto click expand");
                }
            });
        } catch (Throwable e) {
            Logger.error(e, () -> "VideoDescPatch: failed to post auto expand");
        }
    }

    /**
     * Called from bytecode patch on setMaxLines of ExpandableTextView.
     * If AutoExpandDesc is enabled, replace maxLines with a large value.
     */
    public static int getDescMaxLines(int original) {
        if (Settings.AutoExpandDesc.get())
            return 9999;
        return original;
    }

    /**
     * Called from bytecode patch on ExpandableLayout constructor.
     * If AutoExpandDesc is enabled, expand the layout after init.
     */
    /**
     * Called from Activity lifecycle to hide the floating mini-player button
     * on the video detail page.
     */
    public static void hideFloatingButton() {
        if (!Settings.HideFloatingButton.get()) return;
        try {
            Activity activity = ApplicationDelegate.getTopActivity();
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) return;
            // Try float_window first, then mini_play_container
            int floatWindowId = Utils.getResId("float_window", "id");
            if (floatWindowId != 0) {
                View floatView = activity.findViewById(floatWindowId);
                if (floatView != null) {
                    floatView.setVisibility(View.GONE);
                    return;
                }
            }
            int miniPlayId = Utils.getResId("mini_play_container", "id");
            if (miniPlayId != 0) {
                View miniView = activity.findViewById(miniPlayId);
                if (miniView != null) {
                    miniView.setVisibility(View.GONE);
                }
            }
        } catch (Throwable e) {
            Logger.error(e, () -> "VideoDescPatch: failed to hide floating button");
        }
    }

    /**
     * Called from Activity lifecycle to auto-expand video description.
     * Finds the ExpandableLayout by resource ID and triggers expansion.
     */
    public static void autoExpandDesc() {
        if (!Settings.AutoExpandDesc.get()) return;
        try {
            Activity activity = ApplicationDelegate.getTopActivity();
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) return;
            // Find ExpandableLayout by resource ID
            int expandableLayoutId = Utils.getResId("expandable_layout", "id");
            if (expandableLayoutId == 0) {
                Logger.debug(() -> "VideoDescPatch: expandable_layout id not found");
                return;
            }
            View expandableView = activity.findViewById(expandableLayoutId);
            if (expandableView == null) {
                Logger.debug(() -> "VideoDescPatch: expandable_layout view not found");
                return;
            }
            expandableView.postDelayed(() -> {
                try {
                    // Try reflection to call expand()/setExpanded(true) on the view
                    var clazz = expandableView.getClass();
                    for (var m : clazz.getMethods()) {
                        String name = m.getName();
                        String nameLower = name.toLowerCase();
                        if (nameLower.equals("expand") && m.getParameterCount() == 0) {
                            m.invoke(expandableView);
                            Logger.debug(() -> "VideoDescPatch: expanded via expand()");
                            return;
                        }
                        if ((name.equals("setExpanded") || name.equals("setExpand"))
                                && m.getParameterCount() == 1
                                && m.getParameterTypes()[0] == boolean.class) {
                            m.invoke(expandableView, true);
                            Logger.debug(() -> "VideoDescPatch: expanded via " + name + "(true)");
                            return;
                        }
                    }
                    // Fallback: perform click on the expandable layout to trigger expansion
                    expandableView.performClick();
                    Logger.debug(() -> "VideoDescPatch: expanded via performClick()");
                } catch (Throwable e) {
                    Logger.error(e, () -> "VideoDescPatch: failed to auto expand desc");
                }
            }, 500);
        } catch (Throwable e) {
            Logger.error(e, () -> "VideoDescPatch: failed to auto expand desc");
        }
    }
}
