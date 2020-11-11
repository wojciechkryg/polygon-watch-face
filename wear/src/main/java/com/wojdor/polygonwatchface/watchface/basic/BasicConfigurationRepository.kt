package com.wojdor.polygonwatchface.watchface.basic

import android.content.Context
import androidx.core.content.edit
import com.wojdor.polygonwatchface.base.BaseConfigurationRepository

class BasicConfigurationRepository(context: Context) : BaseConfigurationRepository(context) {

    override val name = "polygon_basic_watch_face"

    var isAlwaysOutline
        get() = sharedPrefs.getBoolean(KEY_IS_ALWAYS_OUTLINE, false)
        set(value) = sharedPrefs.edit { putBoolean(KEY_IS_ALWAYS_OUTLINE, value) }

    companion object {
        private const val KEY_IS_ALWAYS_OUTLINE = "KEY_IS_ALWAYS_OUTLINE"
    }
}