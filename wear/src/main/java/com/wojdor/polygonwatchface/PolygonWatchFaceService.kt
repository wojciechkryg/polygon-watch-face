package com.wojdor.polygonwatchface

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import com.wojdor.polygonwatchface.base.BaseWatchFaceService
import java.util.concurrent.TimeUnit

class PolygonWatchFaceService : BaseWatchFaceService() {

    private val timePaint = Paint().apply {
        color = TIME_INTERACTIVE_COLOR
        strokeWidth = PAINT_STROKE_WIDTH
        isAntiAlias = true
        style = Paint.Style.STROKE
        typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
    }
    private var backgroundColor = BACKGROUND_INTERACTIVE_COLOR
    private var xOffset = 0F
    private var yOffset = 0F

    override val interactiveUpdateIntervalInMilliseconds = TimeUnit.SECONDS.toMillis(1)

    override fun onEngineInit() {
        xOffset = resources.getDimension(if (isRound) R.dimen.x_offset_round else R.dimen.x_offset)
        yOffset = resources.getDimension(R.dimen.y_offset)
    }

    override fun drawBackground(canvas: Canvas) {
        canvas.drawColor(backgroundColor)
    }

    override fun drawWatchFace(canvas: Canvas) {
        canvas.drawText(
            "${getDigitalHoursText()} : ${getMinutesText()} : ${getSecondsText()}",
            xOffset,
            yOffset,
            timePaint
        )
    }

    override fun onAmbientModeChanged() {
        when {
            isAmbient -> setupAmbientPaints()
            else -> setupInteractivePaints()
        }
    }

    private fun setupAmbientPaints() {
        backgroundColor = BACKGROUND_AMBIENT_COLOR
        with(timePaint) {
            color = TIME_AMBIENT_COLOR
            isAntiAlias = false
        }
    }

    private fun setupInteractivePaints() {
        backgroundColor = BACKGROUND_INTERACTIVE_COLOR
        with(timePaint) {
            color = TIME_INTERACTIVE_COLOR
            isAntiAlias = true
        }
    }

    companion object {
        private const val PAINT_STROKE_WIDTH = 2f
        private const val TIME_INTERACTIVE_COLOR = Color.RED
        private const val TIME_AMBIENT_COLOR = Color.GREEN
        private const val BACKGROUND_INTERACTIVE_COLOR = Color.DKGRAY
        private const val BACKGROUND_AMBIENT_COLOR = Color.BLACK
    }
}