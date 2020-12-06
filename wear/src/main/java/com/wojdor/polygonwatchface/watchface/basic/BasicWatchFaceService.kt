package com.wojdor.polygonwatchface.watchface.basic

import com.wojdor.commonandroid.watchface.BasicWatchFace
import com.wojdor.polygonwatchface.R
import com.wojdor.polygonwatchface.base.BaseWatchFaceService
import org.koin.android.ext.android.inject
import java.util.concurrent.TimeUnit

class BasicWatchFaceService : BaseWatchFaceService() {

    private val configurationRepository: BasicConfigurationRepository by inject()
    private var timeInteractiveColor = 0
    private var timeAmbientColor = 0
    private var dialInteractiveColor = 0
    private var dialAmbientColor = 0

    override val interactiveUpdateIntervalInMilliseconds = TimeUnit.MINUTES.toMillis(1)
    override val watchFace: BasicWatchFace by inject()

    override fun onEngineInit() {
        dialInteractiveColor = getColor(R.color.black)
        dialAmbientColor = getColor(R.color.black)
        timeAmbientColor = getColor(R.color.white)
        timeInteractiveColor = configurationRepository.timeColor
        with(watchFace) {
            timeColor = timeInteractiveColor
            isOutline = configurationRepository.isAlwaysOutline
            dialColor = dialInteractiveColor
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

    private fun updatePaints() {
        when {
            isAmbient -> setupAmbientPaints()
            else -> setupInteractivePaints()
        }
    }

    private fun setupAmbientPaints() {
        with(watchFace) {
            timeColor =
                if (configurationRepository.isGrayscaleInAmbient) timeAmbientColor else timeInteractiveColor
            dialColor = dialAmbientColor
            isOutline = true
            isAntiAlias = false
        }
    }

    private fun setupInteractivePaints() {
        with(watchFace) {
            timeColor = timeInteractiveColor
            dialColor = dialInteractiveColor
            isOutline = configurationRepository.isAlwaysOutline
            isAntiAlias = true
        }
    }
}