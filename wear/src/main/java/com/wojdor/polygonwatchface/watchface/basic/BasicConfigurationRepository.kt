package com.wojdor.polygonwatchface.watchface.basic

import android.content.Context
import androidx.core.content.edit
import com.wojdor.polygonwatchface.base.BaseConfigurationRepository

class BasicConfigurationRepository(context: Context) : BaseConfigurationRepository(context) {

    override val name = "polygon_basic_watch_face"

    var isAlwaysOutline
        get() = sharedPrefs.getBoolean(KEY_IS_ALWAYS_OUTLINE, false)
        set(value) = sharedPrefs.edit { putBoolean(KEY_IS_ALWAYS_OUTLINE, value) }

    var isGrayscaleInAmbient
        get() = sharedPrefs.getBoolean(KEY_IS_GRAYSCALE_IN_AMBIENT, false)
        set(value) = sharedPrefs.edit { putBoolean(KEY_IS_GRAYSCALE_IN_AMBIENT, value) }

    companion object {
        private const val KEY_IS_ALWAYS_OUTLINE = "KEY_IS_ALWAYS_OUTLINE"
        private const val KEY_IS_GRAYSCALE_IN_AMBIENT = "KEY_IS_GRAYSCALE_IN_AMBIENT"
    }
}