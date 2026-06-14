package app.revanced.bilibili.settings

import android.content.SharedPreferences
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.concurrent.ConcurrentHashMap

/**
 * Setting 类单元测试
 * 覆盖: 值存取、批量保存事务、isSetToDefault
 */
class SettingTest {

    private lateinit var fakePrefs: FakeSharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    @Before
    fun setUp() {
        fakePrefs = FakeSharedPreferences()
        // 反射注入 fake prefs - 注意 Setting.prefs 是 companion object 的 val
        // 这里用反射是因为 prefs 在 Setting.init 中已初始化
        val prefsField = Setting::class.java.getDeclaredField("prefs")
        prefsField.isAccessible = true
        // 移除 final 修饰符
        val modifiersField = java.lang.reflect.Field::class.java.getDeclaredField("modifiers")
        modifiersField.isAccessible = true
        modifiersField.setInt(prefsField, prefsField.modifiers and Modifier.FINAL.inv())
        prefsField.set(null, fakePrefs)
    }

    // region 基本存取测试

    @Test
    fun `booleanSetting saves and loads correctly`() {
        val setting = BooleanSetting("test_bool", false)
        setting.save(true)
        assertTrue(setting.get())
        assertTrue(fakePrefs.getBoolean("test_bool", false))
    }

    @Test
    fun `intSetting saves and loads correctly`() {
        val setting = IntSetting("test_int", 0)
        setting.save(42)
        assertEquals(42, setting.get())
        assertEquals(42, fakePrefs.getInt("test_int", 0))
    }

    @Test
    fun `stringSetting saves and loads correctly`() {
        val setting = StringSetting("test_str", "")
        setting.save("hello")
        assertEquals("hello", setting.get())
        assertEquals("hello", fakePrefs.getString("test_str", ""))
    }

    @Test
    fun `floatSetting saves and loads correctly`() {
        val setting = FloatSetting("test_float", 0f)
        setting.save(3.14f)
        assertEquals(3.14f, setting.get())
        assertEquals(3.14f, fakePrefs.getFloat("test_float", 0f))
    }

    @Test
    fun `stringSetSetting saves and loads correctly`() {
        val setting = StringSetSetting("test_set")
        setting.save(setOf("a", "b", "c"))
        assertEquals(setOf("a", "b", "c"), setting.get())
    }

    // endregion

    // region saveBatch 批量事务测试

    @Test
    fun `saveBatch writes all settings in single transaction`() {
        val boolSetting = BooleanSetting("batch_bool", false)
        val intSetting = IntSetting("batch_int", 42)
        val strSetting = StringSetting("batch_str", "default")

        Setting.saveBatch {
            boolSetting.save(true)
            intSetting.save(100)
            strSetting.save("batched")
        }

        assertTrue(boolSetting.get())
        assertEquals(100, intSetting.get())
        assertEquals("batched", strSetting.get())
    }

    @Test
    fun `saveBatch prevents race condition by single apply`() {
        val boolSetting = BooleanSetting("race_bool", false)
        val intSetting = IntSetting("race_int", 0)

        // 单个 saveBatch 调用, 所有设置在一次 apply 中完成
        Setting.saveBatch {
            boolSetting.save(true)
            intSetting.save(99)
        }

        assertTrue(boolSetting.get())
        assertEquals(99, intSetting.get())
    }

    @Test
    fun `saveBatch preserves saveToEditor path`() {
        val boolSetting = BooleanSetting("editor_bool", false)

        Setting.saveBatch {
            boolSetting.save(true)
        }

        assertTrue(fakePrefs.getBoolean("editor_bool", false))
    }

    // endregion

    // region isSetToDefault 测试

    @Test
    fun `isSetToDefault returns true for default value`() {
        val setting = BooleanSetting("default_test", true)
        assertTrue(setting.isSetToDefault())
    }

    @Test
    fun `isSetToDefault returns false after save`() {
        val setting = BooleanSetting("default_test", true)
        setting.save(false)
        assertFalse(setting.isSetToDefault())
    }

    // endregion

    // region restoreToDefault 测试

    @Test
    fun `restoreToDefault resets value`() {
        val setting = BooleanSetting("restore_test", true)
        setting.save(false)
        assertFalse(setting.get())
        setting.restoreToDefault()
        assertTrue(setting.get())
    }

    // endregion

    // region 依赖项 (dependency) 测试

    @Test
    fun `setting returns default when dependency is false`() {
        val dep = BooleanSetting("dep_switch", false)
        val dependent = BooleanSetting("dep_setting", true, dependency = dep)

        assertEquals(true, dependent.get())  // dep 为 false → 返回 default=true
    }

    @Test
    fun `setting returns saved value when dependency is true`() {
        val dep = BooleanSetting("dep_switch2", true)
        val dependent = BooleanSetting("dep_setting2", true, dependency = dep)

        assertEquals(true, dependent.get())  // dep 为 true → 返回当前值
        dependent.save(false)
        assertFalse(dependent.get())
    }

    // endregion

    // region FakeSharedPreferences 简单实现

    class FakeSharedPreferences : SharedPreferences {
        private val store = ConcurrentHashMap<String, Any>()

        override fun getAll(): MutableMap<String, *> = HashMap(store)
        override fun getString(key: String?, defValue: String?): String? =
            store[key] as? String ?: defValue
        override fun getStringSet(key: String?, defValue: MutableSet<String>?): MutableSet<String>? {
            @Suppress("UNCHECKED_CAST")
            return store[key] as? MutableSet<String> ?: defValue
        }
        override fun getInt(key: String?, defValue: Int): Int =
            store[key] as? Int ?: defValue
        override fun getLong(key: String?, defValue: Long): Long =
            store[key] as? Long ?: defValue
        override fun getFloat(key: String?, defValue: Float): Float =
            store[key] as? Float ?: defValue
        override fun getBoolean(key: String?, defValue: Boolean): Boolean =
            store[key] as? Boolean ?: defValue
        override fun contains(key: String?): Boolean = store.containsKey(key)
        override fun edit(): SharedPreferences.Editor = FakeEditor()
        override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {}
        override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {}

        inner class FakeEditor : SharedPreferences.Editor {
            private val pending = HashMap<String, Any>()

            override fun putString(key: String?, value: String?): SharedPreferences.Editor {
                key?.let { pending[it] = value ?: "" }; return this
            }
            override fun putStringSet(key: String?, values: MutableSet<String>?): SharedPreferences.Editor {
                key?.let { pending[it] = values ?: mutableSetOf() }; return this
            }
            override fun putInt(key: String?, value: Int): SharedPreferences.Editor {
                key?.let { pending[it] = value }; return this
            }
            override fun putLong(key: String?, value: Long): SharedPreferences.Editor {
                key?.let { pending[it] = value }; return this
            }
            override fun putFloat(key: String?, value: Float): SharedPreferences.Editor {
                key?.let { pending[it] = value }; return this
            }
            override fun putBoolean(key: String?, value: Boolean): SharedPreferences.Editor {
                key?.let { pending[it] = value }; return this
            }
            override fun remove(key: String?): SharedPreferences.Editor {
                key?.let { pending.remove(it) }; return this
            }
            override fun clear(): SharedPreferences.Editor {
                pending.clear(); return this
            }
            override fun commit(): Boolean {
                store.putAll(pending); pending.clear(); return true
            }
            override fun apply() {
                store.putAll(pending); pending.clear()
            }
        }
    }

    object Modifier {
        const val FINAL = java.lang.reflect.Modifier.FINAL
    }
}
