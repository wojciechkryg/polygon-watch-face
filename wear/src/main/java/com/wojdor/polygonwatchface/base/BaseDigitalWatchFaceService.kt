package com.wojdor.polygonwatchface.base

import android.graphics.PointF

abstract class BaseDigitalWatchFaceService : BaseWatchFaceService() {

    val hoursFirstDigit get() = digitalHours / 10
    val hoursSecondDigit get() = digitalHours % 10
    val minutesFirstDigit get() = minutes / 10
    val minutesSecondDigit get() = minutes % 10
    val secondsFirstDigit get() = seconds / 10
    val secondsSecondDigit get() = seconds % 10
    val topLeftQuarterCenter = PointF()
    val topRightQuarterCenter = PointF()
    val bottomLeftQuarterCenter = PointF()
    val bottomRightQuarterCenter = PointF()

    override fun onSurfaceChanged() {
        with(topLeftQuarterCenter) {
            x = width / 10F * 3
            y = height / 10F * 3
        }
        with(topRightQuarterCenter) {
            x = width / 10F * 7
            y = height / 10F * 3
        }
        with(bottomLeftQuarterCenter) {
            x = width / 10F * 3
            y = height / 10F * 7
        }
        with(bottomRightQuarterCenter) {
            x = width / 10F * 7
            y = height / 10F * 7
        }
    }
}