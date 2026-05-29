package app.revanced.bilibili.patches;

import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Keep;

import com.bapis.bilibili.app.playerunite.v1.PlayViewUniteReply;
import com.bapis.bilibili.app.playurl.v1.DashItem;
import com.bapis.bilibili.app.playurl.v1.PlayViewReply;
import com.bapis.bilibili.app.playurl.v1.Stream;

import app.revanced.bilibili.account.Accounts;
import app.revanced.bilibili.settings.Settings;
import app.revanced.bilibili.utils.Utils;

public class TrialQualityPatch {

    private static int dp2px(float dp) {
        return (int) ((dp * Utils.getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    /**
     * Strip trial-limiting query parameters (deadline, expire) from URL.
     * This removes the server-enforced time limit on trial stream URLs.
     */
    private static String stripTrialParams(String url) {
        if (url == null || url.isEmpty()) return url;
        try {
            Uri uri = Uri.parse(url);
            // Remove deadline and other time-limiting params
            Uri.Builder builder = uri.buildUpon().clearQuery();
            for (String key : uri.getQueryParameterNames()) {
                if (!"deadline".equals(key) && !"dltime".equals(key)) {
                    builder.appendQueryParameter(key, uri.getQueryParameter(key));
                }
            }
            return builder.build().toString();
        } catch (Exception e) {
            // Failed to strip trial params, return original URL
            return url;
        }
    }

    private static void stripDashItemTrialParams(DashItem item) {
        if (item == null) return;
        item.setBaseUrl(stripTrialParams(item.getBaseUrl()));
        var backupUrls = item.getBackupUrlList();
        if (backupUrls != null && !backupUrls.isEmpty()) {
            for (int i = 0; i < backupUrls.size(); i++) {
                item.setBackupUrl(i, stripTrialParams(backupUrls.get(i)));
            }
        }
    }

    public static void makeVipFree(PlayViewReply playViewReply) {
        playViewReply.clearAb();
        playViewReply.getVideoInfo().getStreamListList().stream().filter(Stream::hasDashVideo)
                .forEach(e -> {
                    var streamInfo = e.getStreamInfo();
                    if (streamInfo.getNeedVip()) {
                        streamInfo.setNeedVip(false);
                        streamInfo.setVipFree(true);
                    }
                    // Strip trial URL params for unlimited quality
                    if (Settings.UnlimitedTrialQuality.get()) {
                        var dashVideo = e.getDashVideo();
                        if (dashVideo != null) {
                            dashVideo.setBaseUrl(stripTrialParams(dashVideo.getBaseUrl()));
                            var backupUrls = dashVideo.getBackupUrlList();
                            if (backupUrls != null && !backupUrls.isEmpty()) {
                                for (int i = 0; i < backupUrls.size(); i++) {
                                    dashVideo.setBackupUrl(i, stripTrialParams(backupUrls.get(i)));
                                }
                            }
                        }
                    }
                });
        // Strip audio stream trial params too
        if (Settings.UnlimitedTrialQuality.get()) {
            playViewReply.getVideoInfo().getDashAudioList()
                    .forEach(TrialQualityPatch::stripDashItemTrialParams);
        }
    }

    public static void makeVipFree(PlayViewUniteReply playViewUniteReply) {
        playViewUniteReply.clearQnTrialInfo();
        playViewUniteReply.getVodInfo().getStreamListList().stream().filter(com.bapis.bilibili.playershared.Stream::hasDashVideo)
                .forEach(e -> {
                    var streamInfo = e.getStreamInfo();
                    if (streamInfo.getNeedVip()) {
                        streamInfo.setNeedVip(false);
                        streamInfo.setVipFree(true);
                    }
                    // Strip trial URL params for unlimited quality
                    if (Settings.UnlimitedTrialQuality.get()) {
                        var dashVideo = e.getDashVideo();
                        if (dashVideo != null) {
                            dashVideo.setBaseUrl(stripTrialParams(dashVideo.getBaseUrl()));
                            var backupUrls = dashVideo.getBackupUrlList();
                            if (backupUrls != null && !backupUrls.isEmpty()) {
                                for (int i = 0; i < backupUrls.size(); i++) {
                                    dashVideo.setBackupUrl(i, stripTrialParams(backupUrls.get(i)));
                                }
                            }
                        }
                    }
                });
        // Strip audio stream trial params too
        if (Settings.UnlimitedTrialQuality.get()) {
            playViewUniteReply.getVodInfo().getDashAudioList()
                    .forEach(a -> {
                        a.setBaseUrl(stripTrialParams(a.getBaseUrl()));
                        var backupUrls = a.getBackupUrlList();
                        if (backupUrls != null && !backupUrls.isEmpty()) {
                            for (int i = 0; i < backupUrls.size(); i++) {
                                a.setBackupUrl(i, stripTrialParams(backupUrls.get(i)));
                            }
                        }
                    });
        }
    }

    @Keep
    public static void onBindOnline(boolean selected, TextView strokeBadge, TextView solidBadge) {
        if (Settings.TrialVipQuality.get() && !Accounts.isEffectiveVip()
                && Utils.getString("try_listening_tips") // 限免中
                .equals(solidBadge.getText().toString())) {
            solidBadge.setVisibility(View.GONE);
            var strokeText = selected ? Utils.getString("player_try_watching") // 试看中
                    : Utils.getString("player_try_watch_enable"); // 可试看
            strokeBadge.setText(strokeText);
            strokeBadge.setVisibility(View.VISIBLE);
            if (strokeText.length() > 2) {
                strokeBadge.setPadding(dp2px(4f), dp2px(1f), dp2px(4f), dp2px(2f));
            } else {
                strokeBadge.setPadding(dp2px(8.5f), dp2px(1f), dp2px(8.5f), dp2px(2f));
            }
        }
    }
}
