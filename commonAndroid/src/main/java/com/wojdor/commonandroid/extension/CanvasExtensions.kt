package com.wojdor.commonandroid.extension

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF

fun Canvas.drawLine(start: PointF, end: PointF, paint: Paint) {
    drawLine(start.x, start.y, end.x, end.y, paint)
}

fun Canvas.drawCircle(center: PointF, radius: Float, paint: Paint) {
    drawCircle(center.x, center.y, radius, paint)
}