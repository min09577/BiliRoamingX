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
     * Called from bytecode patch on ExpandableLayout constructor.
     * If AutoExpandDesc is enabled, expand the layout after init.
     */
    public static void onExpandableLayoutInit(View view) {
        if (!Settings.AutoExpandDesc.get()) return;
        try {
            view.post(() -> {
                try {
                    // Try common expand methods via reflection
                    var clazz = view.getClass();
                    for (var m : clazz.getMethods()) {
                        String name = m.getName().toLowerCase();
                        if ((name.contains("expand") || name.contains("setexpanded"))
                                && m.getParameterCount() == 0) {
                            m.invoke(view);
                            return;
                        }
                        if ((name.equals("setexpanded") || name.equals("setexpand"))
                                && m.getParameterCount() == 1
                                && m.getParameterTypes()[0] == boolean.class) {
                            m.invoke(view, true);
                            return;
                        }
                    }
                    // Fallback: if it's a ViewGroup, expand by removing max height
                    if (view instanceof ViewGroup) {
                        view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                        view.requestLayout();
                    }
                } catch (Throwable e) {
                    Logger.error(e, () -> "VideoDescPatch: failed to expand on init");
                }
            });
        } catch (Throwable e) {
            Logger.error(e, () -> "VideoDescPatch: failed to post expand on init");
        }
    }
}
