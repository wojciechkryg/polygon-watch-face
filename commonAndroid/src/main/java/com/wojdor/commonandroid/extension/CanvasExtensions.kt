package com.wojdor.commonandroid.extension

import android.graphics.Canvas
import android.graphics.Paint
import com.wojdor.common.domain.Point

fun Canvas.drawLine(start: Point, end: Point, paint: Paint) {
    drawLine(start.x, start.y, end.x, end.y, paint)
}

fun Canvas.drawCircle(center: Point, radius: Float, paint: Paint) {
    drawCircle(center.x, center.y, radius, paint)
}