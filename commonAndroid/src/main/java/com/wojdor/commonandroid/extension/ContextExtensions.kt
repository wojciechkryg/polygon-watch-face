package com.wojdor.commonandroid.extension

import android.content.Context

fun Context.getIntList(arrayId: Int) = resources.getIntArray(arrayId).toList()