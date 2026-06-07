package app.revanced.bilibili.settings.fragments

import android.os.Bundle
import androidx.preference.Preference
import app.revanced.bilibili.settings.Settings
import app.revanced.bilibili.settings.search.annotation.SettingFragment
import app.revanced.bilibili.utils.*

@SettingFragment("biliroaming_setting_pip_aspect_ratio")
class PipAspectRatioFragment : BiliRoamingBaseSettingFragment() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        super.onCreatePreferences(savedInstanceState, rootKey)
        val preference = findPreference<Preference>("pip_aspect_ratio")
        preference?.onClick { onPipAspectRatioClick() }
    }

    private fun onPipAspectRatioClick(): Boolean {
        val options = arrayOf(
            Utils.getString("biliroaming_pip_aspect_ratio_default"),
            Utils.getString("biliroaming_pip_aspect_ratio_16_9"),
            Utils.getString("biliroaming_pip_aspect_ratio_4_3"),
            Utils.getString("biliroaming_pip_aspect_ratio_1_1")
        )
        android.app.AlertDialog.Builder(context)
            .setTitle(Utils.getString("biliroaming_pip_aspect_ratio_title"))
            .setSingleChoiceItems(options, Settings.PipAspectRatio()) { dialog, which ->
                Settings.PipAspectRatio.save(which)
                dialog.dismiss()
                Toasts.showShortWithId("biliroaming_save_ok")
            }
            .setNegativeButton(android.R.string.cancel, null)
            .create().constraintSize().show()
        return true
    }
}
