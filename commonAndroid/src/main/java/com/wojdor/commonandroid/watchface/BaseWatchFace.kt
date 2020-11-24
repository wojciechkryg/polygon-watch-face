package com.wojdor.commonandroid.watchface

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import com.wojdor.common.domain.Point
import com.wojdor.common.domain.WatchFaceTime
import com.wojdor.commonandroid.extension.drawCircle
import com.wojdor.commonandroid.extension.lineTo
import com.wojdor.commonandroid.extension.moveTo
import kotlin.math.cos
import kotlin.math.sin

open class BaseWatchFace(private val path: Path = Path()) {

    protected val timePaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        isAntiAlias = true
    }
    var timeColor
        get() = timePaint.color
        set(value) {
            timePaint.color = value
        }
    var backgroundColor = 0
    var isAntiAlias
        get() = timePaint.isAntiAlias
        set(value) {
            timePaint.isAntiAlias = value
        }
    var isOutline
        get() = timePaint.style == Paint.Style.STROKE
        set(value) {
            timePaint.style = if (value) Paint.Style.STROKE else Paint.Style.FILL_AND_STROKE
        }

    open fun init(width: Int, height: Int) {}
    open fun drawWatchFace(canvas: Canvas, time: WatchFaceTime) {}

    fun drawBackground(canvas: Canvas) {
        canvas.drawColor(backgroundColor)
    }

    fun drawDigit(
        canvas: Canvas,
        centerDigitPoint: Point,
        dotRadius: Float,
        digitRadius: Float,
        digit: Int
    ) {
        when (digit) {
            1 -> drawDot(canvas, centerDigitPoint, dotRadius)
            2 -> drawVerticalRoundedLine(canvas, centerDigitPoint, dotRadius, digitRadius)
            in 3..9 -> drawRegularPolygon(canvas, digit, centerDigitPoint, digitRadius)
        }
    }

    private fun drawDot(canvas: Canvas, point: Point, dotRadius: Float) {
        canvas.drawCircle(point, dotRadius, timePaint)
    }

    private fun drawVerticalRoundedLine(
        canvas: Canvas,
        point: Point,
        dotRadius: Float,
        digitRadius: Float
    ) {
        canvas.drawPath(createVerticalRoundedLinePath(point, dotRadius, digitRadius), timePaint)
        path.reset()
    }

    private fun createVerticalRoundedLinePath(
        point: Point,
        dotRadius: Float,
        digitRadius: Float
    ) = path.apply {
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

    private fun drawRegularPolygon(
        canvas: Canvas,
        digit: Int,
        point: Point,
        digitRadius: Float
    ) {
        canvas.drawPath(createRegularPolygonPath(digit, point, digitRadius), timePaint)
        path.reset()
    }

    private fun createRegularPolygonPath(
        digit: Int,
        point: Point,
        digitRadius: Float
    ): Path {
        val startAngle = 1.5
        var angle = startAngle * Math.PI
        val angleIncrement = 2 * Math.PI / digit
        var x = point.x + digitRadius * cos(angle)
        var y = point.y + digitRadius * sin(angle)
        return path.apply {
            moveTo(x, y)
            angle += angleIncrement
            for (i in 1 until digit) {
                x = point.x + digitRadius * cos(angle)
                y = point.y + digitRadius * sin(angle)
                lineTo(x, y)
                angle += angleIncrement
            }
            close()
        }
    }
}