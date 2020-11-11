package com.wojdor.polygonwatchface.watchface.basic

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import com.wojdor.commonandroid.extension.drawCircle
import com.wojdor.commonandroid.extension.lineTo
import com.wojdor.commonandroid.extension.moveTo
import com.wojdor.polygonwatchface.R
import com.wojdor.polygonwatchface.base.BaseDigitalWatchFaceService
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit
import kotlin.math.cos
import kotlin.math.sin

class BasicWatchFaceService : BaseDigitalWatchFaceService() {

    private val configurationRepository: BasicConfigurationRepository by inject()
    private var dotRadius = 0F
    private var digitRadius = 0F
    private var paintWidth = 0F
    private val timePaint = Paint()
    private var backgroundColor = 0
    private var timeInteractiveColor = 0
    private var timeAmbientColor = 0
    private var backgroundInteractiveColor = 0
    private var backgroundAmbientColor = 0

    override val interactiveUpdateIntervalInMilliseconds = TimeUnit.MINUTES.toMillis(1)

    override fun onEngineInit() {
        dotRadius = width / DOT_RADIUS_RATIO
        digitRadius = width / DIGIT_RADIUS_RATIO
        paintWidth = width / PAINT_WIDTH_RATIO
        with(resources) {
            backgroundInteractiveColor = getColor(R.color.black, theme)
            backgroundAmbientColor = getColor(R.color.black, theme)
            timeInteractiveColor = getColor(R.color.red_shade_400, theme)
            timeAmbientColor = getColor(R.color.white, theme)
        }
        with(timePaint) {
            color = timeInteractiveColor
            strokeWidth = paintWidth
            isAntiAlias = true
            style =
                if (configurationRepository.isAlwaysOutline) Paint.Style.STROKE else Paint.Style.FILL_AND_STROKE
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
        updatePaints()
    }

    override fun onVisibilityChanged(isVisible: Boolean) {
        if (isVisible) {
            updatePaints()
        }
    }

    private fun updatePaints() {
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

    private fun setupAmbientPaints() {
        backgroundColor = backgroundAmbientColor
        with(timePaint) {
            color = if (configurationRepository.isGrayscaleInAmbient) timeAmbientColor else timeInteractiveColor
            style = Paint.Style.STROKE
            isAntiAlias = false
        }
    }

    private fun setupInteractivePaints() {
        backgroundColor = backgroundInteractiveColor
        with(timePaint) {
            color = timeInteractiveColor
            style =
                if (configurationRepository.isAlwaysOutline) Paint.Style.STROKE else Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
        }
    }

    companion object {
        private const val PAINT_WIDTH_RATIO = 139F
        private const val DIGIT_RADIUS_RATIO = 7F
        private const val DOT_RADIUS_RATIO = 42F
    }
}