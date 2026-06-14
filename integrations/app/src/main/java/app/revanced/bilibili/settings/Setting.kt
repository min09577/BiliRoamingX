package app.revanced.bilibili.settings

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import app.revanced.bilibili.account.Accounts
import app.revanced.bilibili.utils.*

@Suppress("LeakingThis", "NOTHING_TO_INLINE")
sealed class Setting<out T : Any>(
    @JvmField val key: String,
    @JvmField val defValue: T,
    @JvmField val needReboot: Boolean = false,
    @JvmField val dependency: BooleanSetting? = null,
    private val onChange: ((value: T, async: Boolean) -> Unit)? = null
) {
    protected var value: @UnsafeVariance T = defValue

    init {
        _all.add(this)
        load()
    }

    @JvmSynthetic
    inline operator fun invoke(): T = get()

    protected abstract fun load()

    protected abstract fun saveInternal(newValue: @UnsafeVariance T)

    /**
     * 将值写入给定的 Editor (不调用 apply, 由调用方统一提交)
     * 用于批量保存多个设置时避免竞态条件
     */
    protected abstract fun saveToEditor(newValue: @UnsafeVariance T, editor: SharedPreferences.Editor)

    fun save(newValue: @UnsafeVariance T) {
        saveInternal(newValue)
    }

    fun restoreToDefault() {
        save(defValue)
    }

    fun get(): T {
        return if (Accounts.userBlocked || (dependency != null && !dependency.get())) defValue else value
    }

    fun executeOnChangeAction(async: Boolean) {
        onChange?.invoke(value, async)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Setting<*>) return false
        return key == other.key
    }

    override fun hashCode() = key.hashCode()

    override fun toString() = "$key: $value"

    companion object {

        private val _all = mutableSetOf<Setting<Any>>()
        val all: Set<Setting<Any>> = _all

        val prefs: SharedPreferences = CrossProcessPreferences.get(Constants.PREFS_SETTING)

        private val innerListener = OnSharedPreferenceChangeListener { _, key ->
            onPreferenceChanged(key.orEmpty())
        }

        private var async = true

        init {
            migrate()
            prefs.registerOnSharedPreferenceChangeListener(innerListener)
        }

        fun asyncExecuteOnChangeAction(async: Boolean) {
            this.async = async
        }

        private fun onPreferenceChanged(key: String) {
            Logger.debug { "onPreferenceChanged, key: $key" }
            all.find { it.key == key }?.run {
                load()
                if (Utils.isMainProcess())
                    executeOnChangeAction(async)
            }
        }

        private fun migrate() {
            if (Utils.isMainProcess() &&
                (prefs.getBoolean("remove_video_cmd_dms", false)
                        || prefs.getBoolean("purify_search", false))
            ) prefs.edit(commit = true) {
                if (prefs.getBoolean("remove_video_cmd_dms", false)) {
                    remove("remove_video_cmd_dms")
                    val popups = setOf("vote", "attention", "grade", "gradeSummary", "link", "other")
                    putStringSet("remove_video_popups", popups)
                }
                if (prefs.getBoolean("purify_search", false)) {
                    remove("purify_search")
                    val types = setOf("words", "trending", "recommend")
                    putStringSet("purify_search_types", types)
                }
            }
        }

        fun registerPreferenceChangeListener(listener: OnSharedPreferenceChangeListener) {
            prefs.registerOnSharedPreferenceChangeListener(listener)
        }

        fun unregisterPreferenceChangeListener(listener: OnSharedPreferenceChangeListener) {
            prefs.unregisterOnSharedPreferenceChangeListener(listener)
        }

        @JvmField
        @Volatile
        var _batchEditor: SharedPreferences.Editor? = null

        /**
         * 批量保存多个设置到同一个 Editor, 最后统一 apply
         * 解决单次保存多个设置时的竞态数据丢失问题 (Fix #754)
         */
        fun saveBatch(block: () -> Unit) {
            val editor = prefs.edit()
            _batchEditor = editor
            block()
            _batchEditor = null
            editor.apply()
        }
    }
}

class BooleanSetting(
    key: String,
    defValue: Boolean = false,
    needReboot: Boolean = false,
    dependency: BooleanSetting? = null,
    onChange: ((value: Boolean, async: Boolean) -> Unit)? = null
) : Setting<Boolean>(key, defValue, needReboot, dependency, onChange) {
    override fun load() {
        value = prefs.getBoolean(key, defValue)
    }

    override fun saveInternal(newValue: Boolean) {
        val editor = _batchEditor
        if (editor != null) {
            saveToEditor(newValue, editor)
        } else {
            value = newValue
            prefs.edit { putBoolean(key, newValue) }
        }
    }

    override fun saveToEditor(newValue: Boolean, editor: SharedPreferences.Editor) {
        value = newValue
        editor.putBoolean(key, newValue)
    }
}

class IntSetting(
    key: String,
    defValue: Int = 0,
    needReboot: Boolean = false,
    dependency: BooleanSetting? = null,
    onChange: ((value: Int, async: Boolean) -> Unit)? = null
) : Setting<Int>(key, defValue, needReboot, dependency, onChange) {
    override fun load() {
        value = prefs.getInt(key, defValue)
    }

    override fun saveInternal(newValue: Int) {
        val editor = _batchEditor
        if (editor != null) {
            saveToEditor(newValue, editor)
        } else {
            value = newValue
            prefs.edit { putInt(key, newValue) }
        }
    }

    override fun saveToEditor(newValue: Int, editor: SharedPreferences.Editor) {
        value = newValue
        editor.putInt(key, newValue)
    }
}

class LongSetting(
    key: String,
    defValue: Long = 0L,
    needReboot: Boolean = false,
    dependency: BooleanSetting? = null,
    onChange: ((value: Long, async: Boolean) -> Unit)? = null
) : Setting<Long>(key, defValue, needReboot, dependency, onChange) {
    override fun load() {
        value = prefs.getLong(key, defValue)
    }

    override fun saveInternal(newValue: Long) {
        val editor = _batchEditor
        if (editor != null) {
            saveToEditor(newValue, editor)
        } else {
            value = newValue
            prefs.edit { putLong(key, newValue) }
        }
    }

    override fun saveToEditor(newValue: Long, editor: SharedPreferences.Editor) {
        value = newValue
        editor.putLong(key, newValue)
    }
}

class FloatSetting(
    key: String,
    defValue: Float = 0.0f,
    needReboot: Boolean = false,
    dependency: BooleanSetting? = null,
    onChange: ((value: Float, async: Boolean) -> Unit)? = null
) : Setting<Float>(key, defValue, needReboot, dependency, onChange) {
    override fun load() {
        value = prefs.getFloat(key, defValue)
    }

    override fun saveInternal(newValue: Float) {
        val editor = _batchEditor
        if (editor != null) {
            saveToEditor(newValue, editor)
        } else {
            value = newValue
            prefs.edit { putFloat(key, newValue) }
        }
    }

    override fun saveToEditor(newValue: Float, editor: SharedPreferences.Editor) {
        value = newValue
        editor.putFloat(key, newValue)
    }
}

class StringSetting(
    key: String,
    defValue: String = "",
    needReboot: Boolean = false,
    dependency: BooleanSetting? = null,
    onChange: ((value: String, async: Boolean) -> Unit)? = null
) : Setting<String>(key, defValue, needReboot, dependency, onChange) {
    override fun load() {
        value = prefs.getString(key, defValue).orEmpty()
    }

    override fun saveInternal(newValue: String) {
        val editor = _batchEditor
        if (editor != null) {
            saveToEditor(newValue, editor)
        } else {
            value = newValue
            prefs.edit { putString(key, newValue) }
        }
    }

    override fun saveToEditor(newValue: String, editor: SharedPreferences.Editor) {
        value = newValue
        editor.putString(key, newValue)
    }
}

class StringSetSetting(
    key: String,
    defValue: Set<String> = setOf(),
    needReboot: Boolean = false,
    dependency: BooleanSetting? = null,
    onChange: ((value: Set<String>, async: Boolean) -> Unit)? = null
) : Setting<Set<String>>(key, defValue, needReboot, dependency, onChange) {
    override fun load() {
        value = prefs.getStringSet(key, defValue).orEmpty()
    }

    override fun saveInternal(newValue: Set<String>) {
        val editor = _batchEditor
        if (editor != null) {
            saveToEditor(newValue, editor)
        } else {
            value = newValue
            prefs.edit { putStringSet(key, newValue) }
        }
    }

    override fun saveToEditor(newValue: Set<String>, editor: SharedPreferences.Editor) {
        value = newValue
        editor.putStringSet(key, newValue)
    }

    fun append(newValue: String) {
        save(value + newValue)
    }
}
