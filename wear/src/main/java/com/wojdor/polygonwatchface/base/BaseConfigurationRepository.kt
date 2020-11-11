package com.wojdor.polygonwatchface.base

import android.content.Context
import android.content.SharedPreferences

abstract class BaseConfigurationRepository(context: Context) {

    abstract val name: String

    protected val sharedPrefs: SharedPreferences by lazy {
        context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
}