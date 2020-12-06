package com.wojdor.polygonwatchface.watchface.basic

import android.content.Context
import androidx.core.content.edit
import com.wojdor.polygonwatchface.R
import com.wojdor.polygonwatchface.base.BaseConfigurationRepository

class BasicConfigurationRepository(private val context: Context) :
    BaseConfigurationRepository(context) {

    override val name = "polygon_basic_watch_face"

    var timeColor
        get() = sharedPrefs.getInt(KEY_TIME_COLOR, context.getColor(R.color.red_400))
        set(value) = sharedPrefs.edit { putInt(KEY_TIME_COLOR, value) }

    var isAlwaysOutline
        get() = sharedPrefs.getBoolean(KEY_IS_ALWAYS_OUTLINE, false)
        set(value) = sharedPrefs.edit { putBoolean(KEY_IS_ALWAYS_OUTLINE, value) }

    var isGrayscaleInAmbient
        get() = sharedPrefs.getBoolean(KEY_IS_GRAYSCALE_IN_AMBIENT, false)
        set(value) = sharedPrefs.edit { putBoolean(KEY_IS_GRAYSCALE_IN_AMBIENT, value) }

    companion object {
        private const val KEY_TIME_COLOR = "KEY_TIME_COLOR"
        private const val KEY_IS_ALWAYS_OUTLINE = "KEY_IS_ALWAYS_OUTLINE"
        private const val KEY_IS_GRAYSCALE_IN_AMBIENT = "KEY_IS_GRAYSCALE_IN_AMBIENT"
    }
}