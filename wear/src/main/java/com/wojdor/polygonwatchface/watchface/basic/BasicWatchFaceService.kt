package com.wojdor.polygonwatchface.watchface.basic

import android.graphics.Paint
import com.wojdor.commonandroid.watchface.BasicWatchFace
import com.wojdor.polygonwatchface.R
import com.wojdor.polygonwatchface.base.BaseWatchFaceService
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class BasicWatchFaceService : BaseWatchFaceService() {

    private val configurationRepository: BasicConfigurationRepository by inject()

    private var paintWidth = 0F
    private val timePaint = Paint()
    private var backgroundColor = 0
    private var timeInteractiveColor = 0
    private var timeAmbientColor = 0
    private var backgroundInteractiveColor = 0
    private var backgroundAmbientColor = 0

    override val interactiveUpdateIntervalInMilliseconds = TimeUnit.MINUTES.toMillis(1)
    override val watchFace: BasicWatchFace by inject()

    override fun onEngineInit() {
        backgroundInteractiveColor = getColor(R.color.black)
        backgroundAmbientColor = getColor(R.color.black)
        timeAmbientColor = getColor(R.color.white)
        timeInteractiveColor = configurationRepository.timeColor
        paintWidth = width / PAINT_WIDTH_RATIO
        with(watchFace) {
            timePaint = initTimePaint()
            backgroundColor = initBackgroundColor()
        }
    }

    override fun onAmbientModeChanged() {
        updatePaints()
    }

    override fun onVisibilityChanged(isVisible: Boolean) {
        if (isVisible) {
            timeInteractiveColor = configurationRepository.timeColor
            updatePaints()
        }
    }

    private fun initTimePaint() = timePaint.apply {
        color = timeInteractiveColor
        strokeWidth = paintWidth
        isAntiAlias = true
        style = if (configurationRepository.isAlwaysOutline) {
            Paint.Style.STROKE
        } else {
            Paint.Style.FILL_AND_STROKE
        }
    }

    private fun initBackgroundColor() = backgroundInteractiveColor.also { backgroundColor = it }

    private fun updatePaints() {
        when {
            isAmbient -> setupAmbientPaints()
            else -> setupInteractivePaints()
        }
        watchFace.timePaint = timePaint
        watchFace.backgroundColor = backgroundColor
    }

    private fun setupAmbientPaints() {
        backgroundColor = backgroundAmbientColor
        with(timePaint) {
            color =
                if (configurationRepository.isGrayscaleInAmbient) timeAmbientColor else timeInteractiveColor
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
    }
}