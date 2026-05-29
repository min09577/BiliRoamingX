package app.revanced.bilibili.patches;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Keep;

import app.revanced.bilibili.settings.Settings;
import app.revanced.bilibili.utils.Logger;
import app.revanced.bilibili.utils.Reflex;

@Keep
public class VideoDescPatch {

    /**
     * Called from bytecode patch after ExpandableLayout.onFinishInflate().
     * If AutoExpandDesc is enabled, force-expand the layout.
     */
    public static void onExpandableLayoutInflate(View view) {
        if (!Settings.AutoExpandDesc.get()) return;
        try {
            // Try common expand methods
            var clazz = view.getClass();
            // Method 1: void setExpanded(boolean)
            try {
                var method = clazz.getMethod("setExpanded", boolean.class);
                method.invoke(view, true);
                return;
            } catch (NoSuchMethodException ignored) {}
            // Method 2: void expand()
            try {
                var method = clazz.getMethod("expand");
                method.invoke(view);
                return;
            } catch (NoSuchMethodException ignored) {}
            // Method 3: void setExpand(boolean)
            try {
                var method = clazz.getMethod("setExpand", boolean.class);
                method.invoke(view, true);
                return;
            } catch (NoSuchMethodException ignored) {}
            // Method 4: set max lines to large number if it's a TextView
            if (view instanceof android.widget.TextView) {
                ((android.widget.TextView) view).setMaxLines(9999);
                return;
            }
            // Method 5: Try to find and call any method with "expand" in name
            for (var m : clazz.getMethods()) {
                if (m.getName().toLowerCase().contains("expand")
                        && m.getParameterCount() == 0) {
                    m.invoke(view);
                    return;
                }
            }
            Logger.debug(() -> "VideoDescPatch: no expand method found on " + clazz.getName());
        } catch (Throwable e) {
            Logger.error(e, () -> "VideoDescPatch: failed to auto expand desc");
        }
    }

    /**
     * Called from bytecode patch to check if floating button should be hidden.
     * Returns GONE visibility if HideFloatingButton is enabled.
     */
    public static int getFloatingButtonVisibility(int original) {
        if (Settings.HideFloatingButton.get())
            return View.GONE;
        return original;
    }

    /**
     * Called from bytecode patch to hide floating button view by ID.
     */
    public static void hideFloatingButton(View view) {
        if (!Settings.HideFloatingButton.get()) return;
        try {
            view.setVisibility(View.GONE);
        } catch (Throwable e) {
            Logger.error(e, () -> "VideoDescPatch: failed to hide floating button");
        }
    }
}
