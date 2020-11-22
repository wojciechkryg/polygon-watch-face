package com.wojdor.commonandroid.watchface

import android.graphics.Canvas
import com.wojdor.common.domain.Point
import com.wojdor.common.domain.WatchFaceTime

class BasicWatchFace : BaseWatchFace() {

    private var dotRadius = 0F
    private var digitRadius = 0F
    private lateinit var topLeftQuarterCenter: Point
    private lateinit var topRightQuarterCenter: Point
    private lateinit var bottomLeftQuarterCenter: Point
    private lateinit var bottomRightQuarterCenter: Point

    override fun init(width: Int, height: Int) {
        dotRadius = width / DOT_RADIUS_RATIO
        digitRadius = width / DIGIT_RADIUS_RATIO
        initQuarterCenters(width, height)
    }

    override fun drawWatchFace(canvas: Canvas, time: WatchFaceTime) {
        with(time) {
            drawDigit(canvas, topLeftQuarterCenter, dotRadius, digitRadius, hoursFirstDigit)
            drawDigit(canvas, topRightQuarterCenter, dotRadius, digitRadius, hoursSecondDigit)
            drawDigit(canvas, bottomLeftQuarterCenter, dotRadius, digitRadius, minutesFirstDigit)
            drawDigit(canvas, bottomRightQuarterCenter, dotRadius, digitRadius, minutesSecondDigit)
        }
    }

    private fun initQuarterCenters(width: Int, height: Int) {
        topLeftQuarterCenter = Point(width / 10F * 3, height / 10F * 3)
        topRightQuarterCenter = Point(width / 10F * 7, height / 10F * 3)
        bottomLeftQuarterCenter = Point(width / 10F * 3, height / 10F * 7)
        bottomRightQuarterCenter = Point(width / 10F * 7, height / 10F * 7)
    }

    companion object {
        private const val DIGIT_RADIUS_RATIO = 7F
        private const val DOT_RADIUS_RATIO = 42F
    }
}