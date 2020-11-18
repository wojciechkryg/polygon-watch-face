package com.wojdor.commonandroid.watchface

import android.graphics.Canvas
import android.graphics.Path
import android.graphics.PointF
import com.wojdor.common.domain.WatchFaceTime
import com.wojdor.commonandroid.extension.drawCircle
import com.wojdor.commonandroid.extension.lineTo
import com.wojdor.commonandroid.extension.moveTo
import kotlin.math.cos
import kotlin.math.sin

class BasicWatchFace : BaseWatchFace() {

    private var dotRadius = 0F
    private var digitRadius = 0F
    private val topLeftQuarterCenter = PointF()
    private val topRightQuarterCenter = PointF()
    private val bottomLeftQuarterCenter = PointF()
    private val bottomRightQuarterCenter = PointF()

    override fun init(width: Int, height: Int) {
        dotRadius = width / DOT_RADIUS_RATIO
        digitRadius = width / DIGIT_RADIUS_RATIO
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

    override fun drawBackground(canvas: Canvas) {
        canvas.drawColor(backgroundColor)
    }

    override fun drawWatchFace(canvas: Canvas, time: WatchFaceTime) {
        with(time) {
            drawDigit(canvas, topLeftQuarterCenter, hoursFirstDigit)
            drawDigit(canvas, topRightQuarterCenter, hoursSecondDigit)
            drawDigit(canvas, bottomLeftQuarterCenter, minutesFirstDigit)
            drawDigit(canvas, bottomRightQuarterCenter, minutesSecondDigit)
        }
    }

    private fun drawDigit(canvas: Canvas, centerDigitPoint: PointF, digit: Int) {
        when (digit) {
            1 -> drawDot(canvas, centerDigitPoint)
            2 -> drawVerticalRoundedLine(canvas, centerDigitPoint)
            in 3..9 -> drawRegularPolygon(canvas, digit, centerDigitPoint)
        }
    }

    private fun drawDot(canvas: Canvas, point: PointF) {
        canvas.drawCircle(point, dotRadius, timePaint)
    }

    private fun drawVerticalRoundedLine(canvas: Canvas, point: PointF) {
        canvas.drawPath(createVerticalRoundedLinePath(point), timePaint)
    }

    private fun createVerticalRoundedLinePath(point: PointF) = Path().apply {
        addRoundRect(
            point.x - dotRadius / 2,
            point.y + digitRadius - dotRadius / 2,
            point.x + dotRadius / 2,
            point.y - digitRadius + dotRadius / 2,
            dotRadius / 2,
            dotRadius / 2,
            Path.Direction.CW
        )
    }

    private fun drawRegularPolygon(canvas: Canvas, digit: Int, point: PointF) {
        canvas.drawPath(createRegularPolygonPath(digit, point), timePaint)
    }

    private fun createRegularPolygonPath(digit: Int, point: PointF): Path {
        val startAngle = 1.5
        var angle = startAngle * Math.PI
        val angleIncrement = 2 * Math.PI / digit
        var x = point.x + digitRadius * cos(angle)
        var y = point.y + digitRadius * sin(angle)
        return Path().apply {
            moveTo(x, y)
            for (i in 0..digit) {
                x = point.x + digitRadius * cos(angle)
                y = point.y + digitRadius * sin(angle)
                lineTo(x, y)
                angle += angleIncrement
            }
        }
    }

    companion object {
        private const val DIGIT_RADIUS_RATIO = 7F
        private const val DOT_RADIUS_RATIO = 42F
    }
}