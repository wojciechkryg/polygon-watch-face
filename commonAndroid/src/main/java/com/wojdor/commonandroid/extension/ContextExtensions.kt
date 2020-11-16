package com.wojdor.commonandroid.extension

import android.content.Context

fun Context.getColor(colorId: Int) = resources.getColor(colorId, theme)

fun Context.getIntList(arrayId: Int) = resources.getIntArray(arrayId).toList()