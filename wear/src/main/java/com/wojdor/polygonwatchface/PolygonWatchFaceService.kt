package com.wojdor.polygonwatchface

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import com.wojdor.commonandroid.extension.drawCircle
import com.wojdor.commonandroid.extension.lineTo
import com.wojdor.commonandroid.extension.moveTo
import com.wojdor.polygonwatchface.base.BaseDigitalWatchFaceService
import java.util.concurrent.TimeUnit
import kotlin.math.cos
import kotlin.math.sin

class PolygonWatchFaceService : BaseDigitalWatchFaceService() {

    private val timePaint = Paint()
    private var backgroundColor = 0
    private var timeInteractiveColor = 0
    private var timeAmbientColor = 0
    private var backgroundInteractiveColor = 0
    private var backgroundAmbientColor = 0

    override val interactiveUpdateIntervalInMilliseconds = TimeUnit.SECONDS.toMillis(1)

    override fun onEngineInit() {
        with(resources) {
            backgroundInteractiveColor = getColor(R.color.black, theme)
            backgroundAmbientColor = getColor(R.color.black, theme)
            timeInteractiveColor = getColor(R.color.red_shade_400, theme)
            timeAmbientColor = getColor(R.color.red_shade_400, theme)
        }
        with(timePaint) {
            color = timeInteractiveColor
            strokeWidth = PAINT_WIDTH
            isAntiAlias = true
            style = Paint.Style.FILL
        }
        backgroundColor = backgroundInteractiveColor
    }

    override fun drawBackground(canvas: Canvas) {
        canvas.drawColor(backgroundColor)
    }

    override fun drawWatchFace(canvas: Canvas) {
        drawDigit(canvas, topLeftQuarterCenter, hoursFirstDigit)
        drawDigit(canvas, topRightQuarterCenter, hoursSecondDigit)
        drawDigit(canvas, bottomLeftQuarterCenter, minutesFirstDigit)
        drawDigit(canvas, bottomRightQuarterCenter, minutesSecondDigit)
    }

    override fun onAmbientModeChanged() {
        when {
            isAmbient -> setupAmbientPaints()
            else -> setupInteractivePaints()
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
        canvas.drawCircle(point, DOT_WIDTH, timePaint)
    }

    private fun drawVerticalRoundedLine(canvas: Canvas, point: PointF) {
        canvas.drawPath(createVerticalRoundedLinePath(point), timePaint)
    }

    private fun createVerticalRoundedLinePath(point: PointF) = Path().apply {
        addRoundRect(
            point.x - DOT_WIDTH / 2,
            point.y + PAINT_RADIUS - DOT_WIDTH / 2,
            point.x + DOT_WIDTH / 2,
            point.y - PAINT_RADIUS + DOT_WIDTH / 2,
            DOT_WIDTH / 2,
            DOT_WIDTH / 2,
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
        var x = point.x + PAINT_RADIUS * cos(angle)
        var y = point.y + PAINT_RADIUS * sin(angle)
        return Path().apply {
            moveTo(x, y)
            for (i in 0..digit) {
                x = point.x + PAINT_RADIUS * cos(angle)
                y = point.y + PAINT_RADIUS * sin(angle)
                lineTo(x, y)
                angle += angleIncrement
            }
        }
    }

    private fun setupAmbientPaints() {
        backgroundColor = backgroundAmbientColor
        with(timePaint) {
            color = timeAmbientColor
            style = Paint.Style.STROKE
            isAntiAlias = false
        }
    }

    private fun setupInteractivePaints() {
        backgroundColor = backgroundInteractiveColor
        with(timePaint) {
            color = timeInteractiveColor
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
        }
    }

    companion object {
        private const val PAINT_RADIUS = 60F // TODO: Use deviceWidth / 7
        private const val PAINT_WIDTH = 3F // TODO: Use deviceWidth / 139
        private const val DOT_WIDTH = 10F // TODO: Use deviceWidth / 42
    }
}