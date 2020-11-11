package com.wojdor.polygonwatchface.watchface.basic

import com.wojdor.polygonwatchface.R
import com.wojdor.polygonwatchface.base.BaseConfigurationActivity
import com.wojdor.polygonwatchface.domain.SwitchConfigurationItem
import org.koin.android.ext.android.inject

class BasicConfigurationActivity : BaseConfigurationActivity() {

    override val configurationRepository: BasicConfigurationRepository by inject()
    override val configurationItems = listOf(
        SwitchConfigurationItem(
            R.string.configuration_always_outline,
            configurationRepository.isAlwaysOutline
        ) { configurationRepository.isAlwaysOutline = it },
        SwitchConfigurationItem(
            R.string.configuration_grayscale_in_ambient,
            configurationRepository.isGrayscaleInAmbient
        ) { configurationRepository.isGrayscaleInAmbient = it }
    )
}