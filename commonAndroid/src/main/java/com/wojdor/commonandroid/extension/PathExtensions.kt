package com.wojdor.commonandroid.extension

import android.graphics.Path

fun Path.lineTo(x: Double, y: Double) {
    lineTo(x.toFloat(), y.toFloat())
}

fun Path.moveTo(x: Double, y: Double) {
    moveTo(x.toFloat(), y.toFloat())
}